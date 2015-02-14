'use strict';

/* Controllers */
angular.module('phonecat')
    .controller('PhoneListCtrl', ['$location','$scope', '$http', '$log', 'Session','AuthenticationSharedService',
        function ($location,$scope, $http, $log, Session, AuthenticationSharedService )
        {
            $http.get('./Index:phones').success(function(data) {$scope.phones = data;});
            $scope.logout = function() {
                AuthenticationSharedService.logout();
            };
            $scope.orderProp = 'age';
            $scope.login = Session.login;

}]);


angular.module('phonecat')
    .controller('PhoneDetailCtrl', ['$scope','$rootScope', '$routeParams', '$http','CommentService',
        function ($scope,$rootScope, $routeParams, $http,CommentService) {

        $http.get('./Index:phoneDetails/' + $routeParams.phoneId ).success(function(data) {
            $scope.phone = data;
        });

        $scope.comments = CommentService.query({id:$routeParams.phoneId});

        /** Creates a new comment. */

        $scope.addedComment = new CommentService();


        /** Increments the number of likes of a comment, and saves the changes. */
        $scope.addLike = function(comment) {
            comment.$like(comment.id);
        };

        /** Delete a comment. */
        $scope.deleteComment = function(comment) {
            comment.$delete(comment.id).then(function() {
                $scope.comments.splice($scope.comments.indexOf(comment),1);
            }).catch(function() {
                $scope.error = 'ERROR';
            });



        };

        /** Adds a comment. */
        $scope.addComment = function() {
            if($rootScope.account != null)
                $scope.addedComment.author = $rootScope.account.login;
            $scope.addedComment.phoneId = $routeParams.phoneId;
            $scope.addedComment.$save(function(){

                $scope.comments.push($scope.addedComment);
                $scope.addedComment = new CommentService();
            });

        };

        $scope.showButton = function(){
                return $rootScope.authenticated;
        }

        $scope.requestLogin = function(){
            $rootScope.$emit('event:auth-loginRequired');
        }
    }]);

phonecat.controller('LoginCtrl', [ '$rootScope','$scope', '$location','AuthenticationSharedService',
    function ( $rootScope,$scope, $location,AuthenticationSharedService)  {
        $scope.rememberMe = true;
        $scope.login = function () {
            AuthenticationSharedService.login({
                username: $scope.username,
                password: $scope.password,
                rememberMe: $scope.rememberMe
            }).then(function () {
                $scope.authenticationError = false;
                $rootScope.authenticated = true;
                $location.path("/");
            }).catch(function () {
                $scope.authenticationError = true;
                $rootScope.authenticated = false;
            });
        }
    }]);

phonecat.controller('LogoutController', ['$location', 'AuthenticationSharedService',
    function ($location, AuthenticationSharedService) {
        AuthenticationSharedService.logout();
    }]);

phonecat.controller('RegisterController', ['$scope', 'Register',
    function ($scope, Register) {
        $scope.success = null;
        $scope.error = null;
        $scope.doNotMatch = null;
        $scope.errorUserExists = null;
        $scope.register = function () {
            if ($scope.registerAccount.password != $scope.confirmPassword) {
                $scope.doNotMatch = "ERROR";
            } else {
                $scope.doNotMatch = null;
                Register.save($scope.registerAccount,
                    function (value, responseHeaders) {
                        $scope.error = null;
                        $scope.errorUserExists = null;
                        $scope.success = 'OK';
                    },
                    function (httpResponse) {
                        $scope.success = null;
                        if (httpResponse.status === 304 &&
                            httpResponse.data.error && httpResponse.data.error === "Not Modified") {
                            $scope.error = null;
                            $scope.errorUserExists = "ERROR";
                        } else {
                            $scope.error = "ERROR";
                            $scope.errorUserExists = null;
                        }
                    });
            }
        }
    }]);