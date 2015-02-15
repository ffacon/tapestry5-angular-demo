tapestry5-angular-demo
======================

In this demo, a Tapestry page is used to "provision" the AngularJS application.
The same page is also used to answer to the first AngularJS GET request.


Most of the REST-ful API calls to the AngularJS application, is managed by RESTEasy thanks to Tapestry-RESTEasy (see http://tynamo.org/tapestry-resteasy+guide for more details).

Persistence is managed by Tapestry-Hibernate

For Security, this application uses JAX-RS Interceptor (see SecurityInterceptor.java) and Apache Shiro thanks to [tapestry-security](http://tynamo.org/tapestry-security+guide).

see this demo in action at [http://tapestryangular-frafac.rhcloud.com/#/phones](http://tapestryangular-frafac.rhcloud.com/#/phones)

or use mvn jetty:run and hit  [http://localhost:8080/tapestry5-angular-openshift/#/phones](http://localhost:8080/tapestry5-angular-openshift/#/phones)

see also official AngularJS tutorial  [https://docs.angularjs.org/tutorial/step_00](https://docs.angularjs.org/tutorial/step_00)

# Changelog 

* 0.4 : Update to Tapestry 5.3.8 (Java 1.8) and AngularJS 1.3.9
* 0.3 : add Shiro thanks to tapestry-security 
* 0.2 : add RESTEasy thanks to Tapestry-RESTEasy
* 0.1 : Basic version
