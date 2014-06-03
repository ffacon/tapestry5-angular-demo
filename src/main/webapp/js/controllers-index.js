'use strict';

/* Controllers */

function PhoneListCtrl($scope, $http) {
  $http.get('./Step8:phones').success(function(data) {
    $scope.phones = data;
  });

  $scope.orderProp = 'age';
}

//PhoneListCtrl.$inject = ['$scope', '$http'];


function PhoneDetailCtrl($scope, $routeParams, $http) {
  $http.get('./Step8:phoneDetails/' + $routeParams.phoneId ).success(function(data) {
    $scope.phone = data;
  });
}

//PhoneDetailCtrl.$inject = ['$scope', '$routeParams', '$http'];