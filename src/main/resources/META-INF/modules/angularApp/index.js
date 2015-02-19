'use strict';

var httpHeaders;
var baseUrl;

var phonecat = angular.module('phonecat', ['ngResource','ui.router','pascalprecht.translate','ngCookies']).
  config(['$stateProvider','$urlRouterProvider','$httpProvider','USER_ROLES','$translateProvider', function($stateProvider,$urlRouterProvider,$httpProvider,USER_ROLES,$translateProvider) {
  $stateProvider.
      state('phones',
            {
                url: '/phones',
                templateUrl:function()
                    {return baseUrl + 'partials/phones.html';},
                controller: 'PhoneListCtrl',
                access: {
                    authorizedRoles: [USER_ROLES.all]
                }
            }).
      state('login',
      {
          url: '/login',
          templateUrl:function()
          {return baseUrl + 'partials/login.html';},
          controller: 'LoginCtrl',
          access: {
              authorizedRoles: [USER_ROLES.all]
          }
      }).
      state('phonesDetail',
            {
                url: '/phones/:phoneId',
                templateUrl:function()
                    {return baseUrl + 'partials/phone-details.html';},
                controller: 'PhoneDetailCtrl',
                access: {
                    authorizedRoles: [USER_ROLES.all]
                }
            }).
      state('logout', {
                url : '/logout',
                templateUrl:function()
                    {return baseUrl + 'partials/phones.html';},
                controller: 'LogoutController',
                access: {
                    authorizedRoles: [USER_ROLES.all]
                }
      }).
      state('register', {
          url: '/register',
          templateUrl: function()
          {return baseUrl + 'partials/register.html';},
          controller: 'RegisterController',
          access: {
              authorizedRoles: [USER_ROLES.all]
          }
      });

      $urlRouterProvider.otherwise('/phones');

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
.run(['$rootScope', '$location', '$http','$state', 'AuthenticationSharedService',  'Session', 'USER_ROLES',
        function($rootScope, $location, $http,$state, AuthenticationSharedService, Session, USER_ROLES) {
            $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {
                $rootScope.isAuthorized = AuthenticationSharedService.isAuthorized;
                $rootScope.userRoles = USER_ROLES;
                if( !$rootScope.isAuthorized( toState.access.authorizedRoles) )
                {
                    $state.go('login');
                }

            });

            // Call when the 401 response is returned by the server
            $rootScope.$on('unauthorized', function() {
                $state.go('login');
            });

        }]);;

