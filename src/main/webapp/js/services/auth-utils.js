'use strict';
/* Services */

phonecat.factory('Register', ['$resource',
    function ($resource) {
        return $resource('app/rest/register', {}, {
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
        return $resource('app/rest/account', {}, {
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

phonecat.constant('USER_ROLES', {
    all: '*',
    admin: 'ROLE_ADMIN',
    user: 'ROLE_USER'
});

phonecat.factory('AuthenticationSharedService', ['$rootScope', '$http', 'authService', 'Session', 'Account',
    function ($rootScope, $http, authService, Session, Account) {
        return {
            login: function (param) {
                var data ="j_username=" + param.username +"&j_password=" + param.password +"&_spring_security_remember_me=" + param.rememberMe +"&submit=Login";
                $http.post('app/authentication', data, {
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded"
                    },
                    ignoreAuthModule: 'ignoreAuthModule'
                }).success(function (data, status, headers, config) {
                        Account.get(function(data) {
                            Session.create(data.login, data.firstName, data.lastName, data.email, data.roles);
                            $rootScope.account = Session;
                            authService.loginConfirmed(data);
                        });
                    }).error(function (data, status, headers, config) {
                        $rootScope.authenticationError = true;
                        Session.invalidate();
                    });
            },
            valid: function (authorizedRoles) {

                $http.get('protected/transparent.gif', {
                    ignoreAuthModule: 'ignoreAuthModule'
                }).success(function (data, status, headers, config) {
                        if (!Session.login) {
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

                $http.get('app/logout');
                Session.invalidate();
                authService.loginCancelled();
            }
        };
    }]);