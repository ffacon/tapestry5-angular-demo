tapestry5-angular-demo
======================

In this demo, a Tapestry page is used to "provision" the AngularJS application.
The same page is also used to answer to the first AngularJS GET request.


Most of the REST-ful API calls to the AngularJS application, is managed by RESTEasy thanks to Tapestry-RESTEasy (see http://tynamo.org/tapestry-resteasy+guide for more details).

Persistence is managed by Tapestry-Hibernate

For Security, this application uses JAX-RS Interceptor (see SecurityInterceptor.java) and Apache Shiro thanks to [tapestry-security](http://tynamo.org/tapestry-security+guide).

see this demo in action at [http://tapestryangular-frafac.rhcloud.com/#/phones](http://tapestryangular-frafac.rhcloud.com/#/phones)

or use mvn jetty:run and hit  [http://localhost:8080/#/phones](http://localhost:8080/#/phones)

see also official AngularJS tutorial  [https://docs.angularjs.org/tutorial/step_00](https://docs.angularjs.org/tutorial/step_00)

# Changelog 
* 1.4.1.0 : [Use RequireJS module provided by Tapestry 5.4 to load AngularApp #4](https://github.com/ffacon/tapestry5-angular-demo/issues/4)
* 1.4.0.0 : [Update to Tapestry 5.4 #3](https://github.com/ffacon/tapestry5-angular-demo/issues/3)
* 1.0.0 : [Use angular-ui-router instead of angular-route #2](https://github.com/ffacon/tapestry5-angular-demo/issues/2)
* 0.4 : update to Tapestry 5.3.8 (Java 1.8) and AngularJS 1.3.9
* 0.3 : add Shiro thanks to tapestry-security 
* 0.2 : add RESTEasy thanks to Tapestry-RESTEasy
* 0.1 : basic version
