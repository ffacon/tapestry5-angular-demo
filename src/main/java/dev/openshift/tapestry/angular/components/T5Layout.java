package dev.openshift.tapestry.angular.components;

import dev.openshift.tapestry.angular.services.javascript.AngularJavaScriptStack;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

@Import(stylesheet={"context:/css/phonecat/app.css","context:/css/phonecat/bootstrap.css"})
public class T5Layout
{
	@SuppressWarnings("unused")
	@Property
    private String pageName;
	
	@Inject
	private Request request;
	
	@Inject
	private JavaScriptSupport support;

    @SuppressWarnings("unused")
    @Property
    @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
    private String pageTitle;

    @Inject
    private ComponentResources resources;
    
   
   }
