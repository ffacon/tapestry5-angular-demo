'use strict';

/* Controllers */

function PhoneListCtrl($scope, $http) {
  $http.get('./Index:phones').success(function(data) {
    $scope.phones = data;
  });

  $scope.orderProp = 'age';
}

PhoneListCtrl.$inject = ['$scope', '$http'];


angular.module('phonecat')
    .controller('PhoneDetailCtrl', ['$scope', '$routeParams', '$http','CommentService', function ($scope, $routeParams, $http,CommentService) {

        $http.get('./Index:phoneDetails/' + $routeParams.phoneId ).success(function(data) {
            $scope.phone = data;
        });

        $scope.comments = CommentService.query({id:$routeParams.phoneId});

        /** Creates a new comment. */
        $scope.addedComment = new CommentService();

        /** Increments the number of likes of a comment, and saves the changes. */
        $scope.addLike = function(comment) {
            comment.$like();
        };

        /** Deletes a comment. */
        $scope.deleteComment = function(comment) {
            news.$delete(function(){
                $scope.comments.splice($scope.comments.indexOf(comment),1);
            });

        };

        /** Adds a comment. */
        $scope.addComment = function() {
            $scope.addedComment.phoneId = $routeParams.phoneId;
            $scope.addedComment.$save(function(){

                $scope.comments.push($scope.addedComment);
                $scope.addedComment = new CommentService();
            });

        };

    }]);
