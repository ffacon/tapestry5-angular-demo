package dev.openshift.tapestry.angular.pages.admin;

import dev.openshift.tapestry.angular.services.AppModule;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.Log;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.Secure;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.slf4j.Logger;
import org.tynamo.security.internal.services.LoginContextService;
import org.tynamo.security.services.SecurityService;

@Secure
@RequiresPermissions(AppModule.PERMISSION_ADMIN)
public class Index
{
    @Inject
    private Logger LOG;

    @Inject
    private SecurityService securityService;

    @Inject
    private LoginContextService loginContextService;

    @Property
    private String principal;

    @Inject
    private PageRenderLinkSource linkSource;

    /*public Link getLogoutLink()
    {
        Link link = linkSource.createPageRenderLink(Logout.class);
        return link;
    }*/

    @Log
    void onActivate()
    {
        Subject currentUser = securityService.getSubject();
        principal = currentUser.getPrincipal().toString();
        //print their identifying principal (in this case, a username):
        LOG.debug( "User [" + principal + "] logged in successfully." );

    }

    Object onActionFromLink()
    {
        securityService.getSubject().logout();
        return  loginContextService.getLoginPage();
    }
}
