tapestry5-angular-demo
======================

In this demo, a Tapestry page is used to "provision" the AngularJS application.
The same page is also used to answer to the first AngularJS GET request.

Most of the REST-ful API calls to the AngularJS application, is managed by RESTEasy thanks to Tapestry-RESTEasy (see http://tynamo.org/tapestry-resteasy+guide for more details).

Persistence is managed by Tapestry-Hibernate

This sample use a basic JAX-RS Interceptor (see SecurityInterceptor.java).

see this demo in action at [http://tapestryangular-frafac.rhcloud.com/#/phones](http://tapestryangular-frafac.rhcloud.com/#/phones)

or use mvn jetty:run and hit  [http://localhost:8080/tapestry5-angular-openshift/#/phones](http://localhost:8080/tapestry5-angular-openshift/#/phones)

see also official AngularJS tutorial  [https://docs.angularjs.org/tutorial/step_00](https://docs.angularjs.org/tutorial/step_00)