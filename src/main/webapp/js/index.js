'use strict';

/* App Module */


angular.module('phonecat', ['ngRoute','ngResource']).
  config(['$routeProvider', function($routeProvider) {
  $routeProvider.
      when('/phones',
            {
                templateUrl:function()
                {return window.location.origin + window.location.pathname + 'partials/phones.html';}
                , controller: PhoneListCtrl}).
      when('/phones/:phoneId',
            {
                templateUrl:function()
                {return window.location.origin + window.location.pathname + 'partials/phone-details.html';}
                , controller: 'PhoneDetailCtrl'
            }).
      otherwise({redirectTo: '/phones'});
}]).config(['$httpProvider', function($httpProvider) {
    //add header to allow tapestry to serve data
    //see https://github.com/angular/angular.js/issues/1004
    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';}]);