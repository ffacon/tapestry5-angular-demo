package dev.openshift.tapestry.angular.ws.security;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import dev.openshift.tapestry.angular.entity.User;
import dev.openshift.tapestry.angular.services.UserDAO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.util.Base64;
import org.tynamo.security.services.SecurityService;

/**
 * This interceptor verify the access permissions for a user 
 * based on username and password provided in request
 * */
@Provider
public class SecurityInterceptor implements javax.ws.rs.container.ContainerRequestFilter
{
	private static final String AUTHORIZATION_PROPERTY = "Authorization";
	private static final String AUTHENTICATION_SCHEME = "Basic";
    //ngResource request a valid JSONObject otherwise $http.interceptor  failed due a parse error
    private static final JSONObject AccessDeniedMessage = new JSONObject().put("message","Access denied for this resource");
	private static final ServerResponse ACCESS_DENIED = new ServerResponse(AccessDeniedMessage.toString(), 401, new Headers<Object>());;
    private static final JSONObject AccessForbiddenMessage = new JSONObject().put("message","Nobody can access this resource");
    private static final ServerResponse ACCESS_FORBIDDEN = new ServerResponse(AccessForbiddenMessage.toString(), 403, new Headers<Object>());;
    private static final JSONObject ServerErrorMessage = new JSONObject().put("message","INTERNAL SERVER ERROR");
    private static final ServerResponse SERVER_ERROR = new ServerResponse(ServerErrorMessage.toString(), 500, new Headers<Object>());;

    @Inject
    private SecurityService securityService;


    public void filter(ContainerRequestContext requestContext) throws IOException   {

        ResourceMethodInvoker methodInvoker = (ResourceMethodInvoker)
                requestContext.getProperty("org.jboss.resteasy.core.ResourceMethodInvoker");
        Method method = methodInvoker.getMethod();

        //Access allowed for all
        if( ! method.isAnnotationPresent(PermitAll.class))
        {
            //Access denied for all
            if(method.isAnnotationPresent(DenyAll.class))
            {
                requestContext.abortWith(ACCESS_FORBIDDEN);
                return;
            }

            //Get request headers
            final MultivaluedMap<String, String> headers = requestContext.getHeaders();

            //Fetch authorization header
            final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

            //If no authorization information present; block access
            if(authorization == null || authorization.isEmpty())
            {
                requestContext.abortWith(ACCESS_DENIED);
                return;
            }


            Subject subject = securityService.getSubject();
            if (!subject.isAuthenticated()) {
                requestContext.abortWith(SERVER_ERROR);
                return;
             }


            //Verify user access
            if(method.isAnnotationPresent(RolesAllowed.class))
            {
                boolean isAllowed = false;

                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));

                //Is user allowed?
                for(String s : rolesSet)
                    if(subject.hasRole(s))
                    {
                        isAllowed = true;
                    }

                if(!isAllowed)
                {
                    requestContext.abortWith(ACCESS_DENIED);
                }
            }

        }
    }


}
