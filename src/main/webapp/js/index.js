'use strict';

var httpHeaders;
var baseUrl;

var phonecat = angular.module('phonecat', ['http-auth-interceptor','ngResource','ngRoute','pascalprecht.translate','ngCookies']).
  config(['$routeProvider','$httpProvider','USER_ROLES','$translateProvider', function($routeProvider,$httpProvider,USER_ROLES,$translateProvider) {
  $routeProvider.
      when('/phones',
            {
                templateUrl:function()
                    {return baseUrl + 'partials/phones.html';},
                controller: PhoneListCtrl,
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
    }])
.run(['$rootScope', '$location', '$http', 'AuthenticationSharedService', 'Session', 'USER_ROLES',
        function($rootScope, $location, $http, AuthenticationSharedService, Session, USER_ROLES) {
            $rootScope.$on('$routeChangeStart', function (event, next) {
                $rootScope.isAuthorized = AuthenticationSharedService.isAuthorized;
                $rootScope.userRoles = USER_ROLES;
                AuthenticationSharedService.valid(next.access.authorizedRoles);
            });

            // Call when the the client is confirmed
            $rootScope.$on('event:auth-loginConfirmed', function(data) {
                $rootScope.authenticated = true;
                if ($location.path() === "/login") {
                    $location.path('/').replace();
                }
            });

            // Call when the 401 response is returned by the server
            $rootScope.$on('event:auth-loginRequired', function(rejection) {
                Session.invalidate();
                $rootScope.authenticated = false;
                if ($location.path() !== "/" && $location.path() !== "" && $location.path() !== "/register" &&
                    $location.path() !== "/activate") {
                    $location.path('/login').replace();
                }
            });

            // Call when the 403 response is returned by the server
            $rootScope.$on('event:auth-notAuthorized', function(rejection) {
                $rootScope.errorMessage = 'errors.403';
                $location.path('/error').replace();
            });

            // Call when the user logs out
            $rootScope.$on('event:auth-loginCancelled', function() {
                $location.path('');
            });
        }]);;

