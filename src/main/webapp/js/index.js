'use strict';

/* App Module */

angular.module('phonecat', []).
  config(['$routeProvider', function($routeProvider) {
  $routeProvider.
      when('/phones', {templateUrl: './Index:partial/phones',   controller: PhoneListCtrl}).
      when('/phones/:phoneId', {templateUrl: './Index:partial/phone-details-step8', controller: PhoneDetailCtrl}).
      otherwise({redirectTo: '/phones'});
}]);