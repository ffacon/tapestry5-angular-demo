'use strict';

/* App Module */

angular.module('phonecat', []).
  config(['$routeProvider', function($routeProvider) {
  $routeProvider.
      when('/phones', {templateUrl: './step8:partial/phones',   controller: PhoneListCtrl}).
      when('/phones/:phoneId', {templateUrl: './step8:partial/phone-details-step8', controller: PhoneDetailCtrl}).
      otherwise({redirectTo: '/phones'});
}]);