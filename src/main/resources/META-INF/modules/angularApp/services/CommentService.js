(function () {
    'use strict';
    define(['index'], function() {
    angular.module('phonecat')
        .factory('CommentService',['$resource', function ($resource) {
            return $resource(baseUrl + 'api/json/phone/comments/:op/:id',{id:'@id'},{
                query: { method: 'GET',params:{id:'@id'}, isArray: true },
                like: {method:'GET',params: {id:'@id',op:'like'}},
                delete: {method:'DELETE',params: {id:'@id'}}
            });
        }]);
    });
 }());   