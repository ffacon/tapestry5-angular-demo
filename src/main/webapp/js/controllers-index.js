'use strict';

/* Controllers */

function PhoneListCtrl($scope, $http) {
  $http.get('./Index:phones').success(function(data) {
    $scope.phones = data;
  });

  $scope.orderProp = 'age';
}

//PhoneListCtrl.$inject = ['$scope', '$http'];


function PhoneDetailCtrl($scope, $routeParams, $http) {
  $http.get('./Index:phoneDetails/' + $routeParams.phoneId ).success(function(data) {
    $scope.phone = data;
  });
}

//PhoneDetailCtrl.$inject = ['$scope', '$routeParams', '$http'];