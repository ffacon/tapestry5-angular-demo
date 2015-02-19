(function () {
    'use strict';
    require.config({
	    paths: {
	    	
	        'angular': 'angularApp/bower_components/angular/angular',
	        'angular-ui-router': 'angularApp/bower_components/angular-ui-router/release/angular-ui-router',
	        'angular-resource': 'angularApp/bower_components/angular-resource/angular-resource',
	        'angular-cookies': 'angularApp/bower_components/angular-cookies/angular-cookies',
	        'angularTranslate': 'angularApp/bower_components/angular-translate/angular-translate',
	        'angular-translate-storage-cookie': 'angularApp/bower_components/angular-translate-storage-cookie/angular-translate-storage-cookie',
	        'angularTranslateLoader': 'angularApp/bower_components/angular-translate-loader-static-files/angular-translate-loader-static-files',
	        'index':'angularApp/index',
	        'auth-utils': 'angularApp/services/auth-utils',
	        'commentService': 'angularApp/services/CommentService',
	        'controllers':'angularApp/controllers/controllers-index'


	    },
	    shim: {
	        //'bootstrap': {deps: ['angular']}, //already provided by Tapestry 5.4
	    	'angular' : {'exports' : 'angular'},
	        'angularTranslate' : {deps : ['angular']},
	        'angular-translate-storage-cookie' : {deps : ['angularTranslate']},
	        'angularTranslateLoader' : {deps : ['angular-translate-storage-cookie']},
	        'angular-ui-router' : {deps : ['angular']},
	        'angular-resource' : {deps : ['angular']},
	        'index':{'exports' : 'phonecat' ,deps : ['angular-resource','angular-ui-router','angularTranslateLoader' ]},
	        'auth-utils':{deps : ['index' ]},
	        'commentService':{deps : ['index' ]},
	        'controllers':{deps : ['auth-utils','commentService']}
		      
	        
	    }
	});
    define(['angular','controllers'],function(angular){
        angular.bootstrap(angular.element("html"), ['phonecat']);
    });
}());