//
// Copyright 2014-2015 
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

import dev.openshift.tapestry.angular.data.PhoneDetails;
import dev.openshift.tapestry.angular.services.PhoneCatalog;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.AfterRender;
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
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

@Import(stylesheet={"context:/css/phonecat/app.css"})
@Events({ "phones","phoneDetails"})
public class Index
{

    @Inject
    PhoneCatalog catalog;

    @Inject
    private JavaScriptSupport javaScriptSupport;

    @OnEvent(value="phones")
    JSONArray onReturnStreamResponse() {
        String phones= catalog.getPhonesAsString();
        return new JSONArray(phones);
    }

    @OnEvent(value="phoneDetails")
    JSONObject onReturnPhoneDetails(String name) {
        PhoneDetails details= catalog.getPhonesDetails(name);
        return details.getJSONObject();
    }

}
