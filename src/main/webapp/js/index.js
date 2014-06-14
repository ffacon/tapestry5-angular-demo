'use strict';

/* App Module */

angular.module('phonecat', ['ngRoute','ngResource']).
  config(['$routeProvider', function($routeProvider) {
  $routeProvider.
      when('/phones', {templateUrl: './Index:partial/phones',   controller: PhoneListCtrl}).
      when('/phones/:phoneId', {templateUrl: './Index:partial/phone-details-step8', controller: 'PhoneDetailCtrl'}).
      otherwise({redirectTo: '/phones'});
}]).config(['$httpProvider', function($httpProvider) {
    //add header to allow tapestry to serve data
    //see https://github.com/angular/angular.js/issues/1004
    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';}]);