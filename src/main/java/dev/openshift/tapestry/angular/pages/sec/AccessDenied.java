package dev.openshift.tapestry.angular.pages.sec;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.tynamo.security.internal.services.LoginContextService;


public class AccessDenied {
	
	@Inject
	private LoginContextService loginContextService;

    @Inject
    private PageRenderLinkSource linkSource;

    public Link getLoginLink()
    {
        Link link = linkSource.createPageRenderLink(Login.class);
        return link;
    }
	
}
