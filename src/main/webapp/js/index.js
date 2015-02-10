'use strict';

var httpHeaders;
var baseUrl;

var phonecat = angular.module('phonecat', ['ngResource','ngRoute','pascalprecht.translate','ngCookies']).
  config(['$routeProvider','$httpProvider','USER_ROLES','$translateProvider', function($routeProvider,$httpProvider,USER_ROLES,$translateProvider) {
  $routeProvider.
      when('/phones',
            {
                templateUrl:function()
                    {return baseUrl + 'partials/phones.html';},
                controller: 'PhoneListCtrl',
                access: {
                    authorizedRoles: [USER_ROLES.all]
                }
            }).
      when('/login',
      {
          templateUrl:function()
          {return baseUrl + 'partials/login.html';},
          controller: 'LoginCtrl',
          access: {
              authorizedRoles: [USER_ROLES.all]
          }
      }).
      when('/phones/:phoneId',
            {
                templateUrl:function()
                    {return baseUrl + 'partials/phone-details.html';},
                controller: 'PhoneDetailCtrl',
                access: {
                    authorizedRoles: [USER_ROLES.all]
                }
            }).
      when('/logout', {
                templateUrl:function()
                    {return baseUrl + 'partials/phones.html';},
                controller: 'LogoutController',
                access: {
                    authorizedRoles: [USER_ROLES.all]
                }
      }).
      when('/register', {
          templateUrl: function()
          {return baseUrl + 'partials/register.html';},
          controller: 'RegisterController',
          access: {
              authorizedRoles: [USER_ROLES.all]
          }
      }).
      otherwise({redirectTo: '/phones',
          templateUrl:function()
          {return baseUrl + 'partials/phones.html';},
          controller: 'PhoneListCtrl',
                access: {
                    authorizedRoles: [USER_ROLES.all]
                }
      });

      httpHeaders = $httpProvider.defaults.headers;
      baseUrl = window.location.origin + window.location.pathname;

      $translateProvider.useStaticFilesLoader({
            prefix: 'i18n/',
            suffix: '.json'
      });

      $translateProvider.preferredLanguage('fr');

      $translateProvider.useCookieStorage();
    }])
.config(['$httpProvider', function($httpProvider)
    {
    //add header to allow tapestry to serve data
    //see https://github.com/angular/angular.js/issues/1004
    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    $httpProvider.interceptors.push(function($rootScope,$q){
        //see http://www.webdeveasy.com/interceptors-in-angularjs-and-useful-examples/
        //see http://onehungrymind.com/winning-http-interceptors-angularjs/
        return {
            request : function(config) {
                return config;
            },

            response: function (response) {
                if (response.status === 403 || response.status === 401) {
                    // insert code to redirect to custom unauthorized page

                }
                return response || $q.when(response);
            },

            responseError : function(response) {
                if(response.status ===undefined) {
                    //watch out error message could be parse error message from ngresource
                    $rootScope.$broadcast('responseError',response.message);
                };
                if (response.status === 401) {
                    $rootScope.$broadcast('unauthorized');
                }
                return $q.reject(response);
            }
    }});

    }])
.run(['$rootScope', '$location', '$http', 'AuthenticationSharedService',  'Session', 'USER_ROLES',
        function($rootScope, $location, $http, AuthenticationSharedService, Session, USER_ROLES) {
            $rootScope.$on('$routeChangeStart', function (event, next) {
                $rootScope.isAuthorized = AuthenticationSharedService.isAuthorized;
                $rootScope.userRoles = USER_ROLES;
                if( !$rootScope.isAuthorized( next.access.authorizedRoles) )
                {
                    $location.path('/login');
                }

            });

            // Call when the 401 response is returned by the server
            $rootScope.$on('unauthorized', function() {
               $location.path('/login');
            });

        }]);;

