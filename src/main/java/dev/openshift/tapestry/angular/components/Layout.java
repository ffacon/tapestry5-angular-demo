//
// Copyright 2010 GOT5 (GO Tapestry 5)
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// 	http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//

package dev.openshift.tapestry.angular.components;
//
//Copyright 2010 GOT5 (GO Tapestry 5)
//
//Licensed under the Apache License, Version 2.0 (the "License");
//you may not use this file except in compliance with the License.
//You may obtain a copy of the License at
//
//	http://www.apache.org/licenses/LICENSE-2.0
//
//Unless required by applicable law or agreed to in writing, software
//distributed under the License is distributed on an "AS IS" BASIS,
//WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//See the License for the specific language governing permissions and
//limitations under the License.
//

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import dev.openshift.tapestry.angular.services.javascript.AngularJavaScriptStack;


@Import(stack = AngularJavaScriptStack.STACK_ID)
public class Layout
{
	@SuppressWarnings("unused")
	@Property
    private String pageName;
	
	@Inject
	private Request request;
	
	/*
	 * If true, then the AngularJavaScriptStack will be loaded each time
	 * If false (default), the stack won't be loaded if the request is an AJAX request and the page 
	 * is located in the pages.jquerymobilepages subpackage
	 */
	@Parameter(value="false")
	private boolean forceJsStackLoad;
	
	@Inject
	private JavaScriptSupport support;

    @SuppressWarnings("unused")
    @Property
    @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
    private String pageTitle;

    @Inject
    private ComponentResources resources;
    
   
    @SetupRender
    void init(final MarkupWriter writer)
    {
    	writer.getDocument().raw("<!DOCTYPE html>");
        this.pageName = resources.getPageName();
    }
    
    /*@AfterRender
    public void afterRender(){
		if(forceJsStackLoad)
			support.importStack(AngularJavaScriptStack.STACK_ID);
		else 
			if(!jQMobilePageIdentifier.isJQMobilePage())
				support.importStack(AngularJavaScriptStack.STACK_ID);
				
    	//ATTENTION A L'ANNOTATION IMPORT !!!
    }*/

}
