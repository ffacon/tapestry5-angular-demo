'use strict';
/* Services */

phonecat.factory('Register', ['$resource',
    function ($resource) {
        return $resource(baseUrl +'api/app/account/register', {}, {
        });
    }]);

phonecat.factory('Activate', ['$resource',
    function ($resource) {
        return $resource('app/rest/activate', {}, {
            'get': { method: 'GET', params: {}, isArray: false}
        });
    }]);

phonecat.factory('Account', ['$resource',
    function ($resource) {
        return $resource(baseUrl +'api/app/user/account', {}, {
        });
    }]);

phonecat.factory('Password', ['$resource',
    function ($resource) {
        return $resource('app/rest/account/change_password', {}, {
        });
    }]);

phonecat.factory('Sessions', ['$resource',
    function ($resource) {
        return $resource('app/rest/account/sessions/:series', {}, {
            'get': { method: 'GET', isArray: true}
        });
    }]);

phonecat.factory('MetricsService', ['$resource',
    function ($resource) {
        return $resource('metrics/metrics', {}, {
            'get': { method: 'GET'}
        });
    }]);

phonecat.factory('ThreadDumpService', ['$http',
    function ($http) {
        return {
            dump: function() {
                var promise = $http.get('dump').then(function(response){
                    return response.data;
                });
                return promise;
            }
        };
    }]);

phonecat.factory('HealthCheckService', ['$rootScope', '$http',
    function ($rootScope, $http) {
        return {
            check: function() {
                var promise = $http.get('health').then(function(response){
                    return response.data;
                });
                return promise;
            }
        };
    }]);

phonecat.factory('LogsService', ['$resource',
    function ($resource) {
        return $resource('app/rest/logs', {}, {
            'findAll': { method: 'GET', isArray: true},
            'changeLevel':  { method: 'PUT'}
        });
    }]);


phonecat.factory('Session', [
    function () {
        this.create = function (login, firstName, lastName, email, userRoles) {
            this.login = login;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.userRoles = userRoles;
        };
        this.invalidate = function () {
            this.login = null;
            this.firstName = null;
            this.lastName = null;
            this.email = null;
            this.userRoles = null;
        };
        return this;
    }]);

phonecat.factory('StorageService', function ($rootScope) {
    return {

        get: function (key) {
            return JSON.parse(localStorage.getItem(key));
        },

        save: function (key, data) {
            localStorage.setItem(key, JSON.stringify(data));
        },

        remove: function (key) {
            localStorage.removeItem(key);
        },

        clearAll : function () {
            localStorage.clear();
        }
    };
});
phonecat.factory('AccessToken', ['$location', '$http', 'StorageService', '$rootScope',
        function($location, $http, StorageService, $rootScope) {
            var TOKEN = 'token';
            var service = {};
            var token = null;

            service.get = function() {
                // read the token from the localStorage
                if (token == null) {
                    token = StorageService.get(TOKEN);
                }

                if (token != null) {
                    return token.access_token;
                }

                return null;
            };

            service.set = function(oauthResponse) {
                token = {};
                token.access_token = oauthResponse.access_token;
                setExpiresAt(oauthResponse);
                StorageService.save(TOKEN, token);
                return token
            };

            service.remove = function() {
                token = null;
                StorageService.remove(TOKEN);
                return token;
            };

            service.expired = function() {
                return (token && token.expires_at && token.expires_at < new Date().getTime())
            };

            var setExpiresAt = function(oauthResponse) {
                if (token) {
                    var now = new Date();
                    var minutes = parseInt(oauthResponse.expires_in) / 60;
                    token.expires_at = new Date(now.getTime() + minutes*60000).getTime()
                }
            };

            return service;
        }]);


phonecat.constant('USER_ROLES', {
    all: '*',
    admin: 'ADMIN',
    user: 'USER'
});

phonecat.factory('AuthenticationSharedService', ['$rootScope', '$http',  'Session', 'Account','AccessToken',
    function ($rootScope, $http,  Session, Account,AccessToken) {
        return {
            login: function (param) {
                var data ="j_username=" + param.username +"&j_password=" + param.password +"&_spring_security_remember_me=" + param.rememberMe +"&submit=Login";
                return $http.post(baseUrl +'api/app/user/authentication', data, {
                   headers: {
                        "Content-Type": "application/x-www-form-urlencoded"
                    },
                    ignoreAuthModule: 'ignoreAuthModule'
                }).success(function (data, status, headers, config) {
                        httpHeaders.common['Authorization'] = data.access_token;
                        AccessToken.set(data);

                        Account.get(function(data) {
                            Session.create(data.login, data.firstName, data.lastName, data.email, data.roles);
                            $rootScope.account = Session;

                        });
                    }).error(function (data, status, headers, config) {
                        $rootScope.authenticationError = true;
                        Session.invalidate();
                    });
            },
            valid: function (authorizedRoles) {
                if(AccessToken.get() !== null) {
                    httpHeaders.common['Authorization'] =  AccessToken.get();
                }

                $http.get(baseUrl +'api/app/user/validate', {
                    ignoreAuthModule: 'ignoreAuthModule'
                }).success(function (data, status, headers, config) {
                        if (!Session.login || AccessToken.get() != undefined) {
                            if (AccessToken.get() == undefined || AccessToken.expired()) {
                                $rootScope.authenticated = false
                                return;
                            }
                            Account.get(function(data) {
                                Session.create(data.login, data.firstName, data.lastName, data.email, data.roles);
                                $rootScope.account = Session;

                                if (!$rootScope.isAuthorized(authorizedRoles)) {
                                    event.preventDefault();
                                    // user is not allowed
                                    $rootScope.$broadcast("event:auth-notAuthorized");
                                }

                                $rootScope.authenticated = true;
                            });
                        }
                        $rootScope.authenticated = !!Session.login;
                    }).error(function (data, status, headers, config) {
                        $rootScope.authenticated = false;
                    });
            },
            isAuthorized: function (authorizedRoles) {
                if (!angular.isArray(authorizedRoles)) {
                    if (authorizedRoles == '*') {
                        return true;
                    }

                    authorizedRoles = [authorizedRoles];
                }

                var isAuthorized = false;
                angular.forEach(authorizedRoles, function(authorizedRole) {
                    var authorized = (!!Session.login &&
                        Session.userRoles.indexOf(authorizedRole) !== -1);
                    if (authorized || authorizedRole == '*') {
                        isAuthorized = true;
                    }
                });

                return isAuthorized;
            },
            logout: function () {
                $rootScope.authenticationError = false;
                $rootScope.authenticated = false;
                $rootScope.account = null;

                AccessToken.remove();

                $http.get(baseUrl +'api/app/user/logout');
                Session.invalidate();
                delete httpHeaders.common['Authorization'];

            }
        };
    }]);