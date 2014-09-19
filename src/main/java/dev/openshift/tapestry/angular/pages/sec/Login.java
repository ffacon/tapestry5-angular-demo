package dev.openshift.tapestry.angular.pages.sec;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Response;
import org.slf4j.Logger;
import org.tynamo.security.internal.services.LoginContextService;

import java.io.IOException;
import java.util.Date;

/**
 * Application log in page
 *
 *
 */

@Secure
public class Login
{
    @Inject
    private Logger LOG;

    @Property
    private String username, password;

    @Component(id = "loginForm")
    private Form loginForm;

    @Inject
    private LoginContextService loginContextService;

    // http://blog.tapestry5.de/index.php/2010/07/20/advanced-service-builders-in-tapestry-ioc-chain-of-responsibility-pattern/
    // http://en.wikipedia.org/wiki/Basic_access_authentication
    // http://security.stackexchange.com/questions/19525/help-understanding-basic-user-authentication-with-salts-and-hashing
    // http://security.stackexchange.com/questions/24730/is-my-session-less-authentication-system-secure
    // todo register specific ResponseAutoLogout
    @Inject
    private Response response;

    private static final String EMPTY_STRING = "";

    @Log
    String onActivate()
    {
        // http://stackoverflow.com/questions/3450604/why-is-there-no-string-empty-in-java
        this.password = EMPTY_STRING;
        this.username = EMPTY_STRING;

        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated() || subject.isRemembered()) {
            response.setHeader("auto-logout", "logout");
            return loginContextService.getSuccessPage();
        }
        return null;
    }

    @Log
    @OnEvent(value = EventConstants.VALIDATE, component = "loginForm")
    public void validation()
    {
       if (!loginForm.isValid()) {
            return;
        }
        //LOG.debug("onValidateLoginForm [" + username  + "] ");
        try {
            Subject subject = SecurityUtils.getSubject();
            if (!subject.isAuthenticated()) {
                // http://shiro.apache.org/static/1.2.2/apidocs/org/apache/shiro/authc/UsernamePasswordToken.html
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                //token.setRememberMe(rememberMe);
                subject.login(token);
                token.clear();
                LOG.debug("User [" + subject.getPrincipal() + "] logged in successfully.");
            } else {
                LOG.debug("User [" + subject.getPrincipal() + "] failed to log.");
            }
        } catch (Exception e) {
            loginForm.recordError("Error " + e.getMessage());
        }
    }


    @Log
    @OnEvent(value = EventConstants.SUCCESS, component = "loginForm")
    public void loggingSuccess()
    {
        String successPage = loginContextService.getSuccessPage();
        try{

        loginContextService.redirectToSavedRequest(successPage);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Date getCurrentTime()
    {
        return new Date();
    }
}
