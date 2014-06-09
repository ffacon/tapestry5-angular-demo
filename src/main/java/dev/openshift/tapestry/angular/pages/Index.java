//
// Copyright 2014
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS\"BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//

package dev.openshift.tapestry.angular.pages;

import java.io.IOException;
import java.io.InputStream;

import dev.openshift.tapestry.angular.services.PhoneCatalog;
import org.apache.tapestry5.Asset;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.Events;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.internal.services.PageMarkupRenderer;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.Response;

@Import(stylesheet={"context:/css/phonecat/app.css","context:/css/phonecat/bootstrap.css"},
		library={"context:js/index.js","context:js/controllers-index.js"})
@Events({ "partial","phones"})
public class Index
{
	private final String path = "dev/openshift/tapestry/angular/pages/";

    @Inject
    PhoneCatalog catalog;

    @OnEvent(value="phones")
    JSONArray onReturnStreamResponse() {
        String phones= catalog.getPhonesAsString();
        return new JSONArray(phones);
    }
	 
	 @OnEvent(value="partial")
	 StreamResponse onReturnPartials(String name) {
	        final String fileName = path + "partials/"+name+".html";
	        return createStreamResponseFromFile(fileName);
	    }
	
	 @OnEvent(value="phoneDetails")
	 StreamResponse onReturnPhoneDetails(String name) {
	        String fileName = path + "phones/"+name+".json";
	        return createStreamResponseFromFile(fileName);
	    }
	 
	 private StreamResponse createStreamResponseFromFile(final String fileName){
           
	        return new StreamResponse() {
	            InputStream inputStream;
	           
	            public void prepareResponse(Response response) {
	                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	                inputStream = classLoader.getResourceAsStream(fileName);

	                // Set content length to prevent chunking - see
	                // http://tapestry-users.832.n2.nabble.com/Disable-Transfer-Encoding-chunked-from-StreamResponse-td5269662.html#a5269662

	                try {
	                    response.setHeader("Content-Length", "" + inputStream.available());
	                }
	                catch (IOException e) {
	                    // Ignore the exception in this simple example.
	                }
	            }
	            public String getContentType() {
	                return "text/plain";
	            }

	            public InputStream getStream() throws IOException {
	                return inputStream;
	            }
	        };
	    }
	 
}
