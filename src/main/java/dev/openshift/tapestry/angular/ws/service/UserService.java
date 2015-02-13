package dev.openshift.tapestry.angular.ws.service;

import dev.openshift.tapestry.angular.data.user.RoleConsts;
import dev.openshift.tapestry.angular.data.user.RoleEnum;
import dev.openshift.tapestry.angular.data.user.Token;
import dev.openshift.tapestry.angular.services.UserDAO;
import dev.openshift.tapestry.angular.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.jboss.resteasy.util.Base64;
import org.slf4j.Logger;
import org.tynamo.security.services.SecurityService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.*;


@Path("/app/user")
public class UserService
{
    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";

    @Inject
    private UserDAO userDatabase;

    @Inject
    private SecurityService securityService;

    @Inject
    private Logger LOG;


    @GET
    @Path("/users/{login}")
    @PermitAll
    public Response getUserByLogin(@PathParam("login") String login, @Context Request req)
    {
        Response.ResponseBuilder rb = Response.ok(userDatabase.getUserByLogin(login));
        return rb.build();
    }
     


    @PUT
    @Path("/users/{login}")
    @RolesAllowed(RoleConsts.ADMIN_ROLE)
    public Response updateUserById(@PathParam("login") String login)
    {
        //Update the User resource
        User user=userDatabase.getUserByLogin(login);
        return Response.status(200).build();
    }

    @GET
    @Path("/validate")
    @PermitAll
    public Response validate( @Context Request req)
    {
        Subject subject;
        try {
            subject = securityService.getSubject();
            if (subject.isAuthenticated()) {
                return Response.ok().build();
            }
        }
        catch (Exception e) {
            LOG.debug("User failed to log.");
        }
        JSONObject JSONEntity = new JSONObject();
        JSONEntity.put("message","not Authenticated");
        return Response.status(Response.Status.BAD_REQUEST).entity(JSONEntity.toString()).build();
    }

    @POST
    @Path("/authentication")
    @Consumes("application/x-www-form-urlencoded")
    @PermitAll
    public Response postLogin(@FormParam("j_username") String username,@FormParam("j_password") String password) {
        Response.ResponseBuilder rb;

        Subject subject;
        try {
            subject = securityService.getSubject();
            if (!subject.isAuthenticated()) {
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                subject.login(token);
                token.clear();
                String userPassword= username + ":" + password;
                String basicAuth = new String(Base64.encodeBytes(userPassword.getBytes()));
                Token cltToken = new Token();
                cltToken.setAccess_token("Basic "+basicAuth);
                cltToken.setExpires_in(1799);
                cltToken.setToken_type("bearer");
                cltToken.setScope("read write");
                rb = Response.ok(cltToken);
                return rb.build();

            } else {
                LOG.debug("User [" + subject.getPrincipal() + "] already authenticated.");
                if(subject.getPrincipal().toString().equals(username))
                {
                    rb = Response.ok();
                    return rb.build();
                }
            }
        } catch (Exception e) {
            LOG.debug("User failed to log.");
        }
        JSONObject JSONEntity = new JSONObject();
        JSONEntity.put("message","invalid user or password");
        return Response.status(Response.Status.BAD_REQUEST).entity(JSONEntity.toString()).build();

    }




    @GET
    @Path("/account")
    @PermitAll
    public Response account( @Context Request req, @Context HttpHeaders headers)
    {
        String auth = headers.getHeaderString(AUTHORIZATION_PROPERTY);
        //Get encoded username and password
        final String encodedUserPassword = auth.replaceFirst(AUTHENTICATION_SCHEME + " ", "");

        JSONObject JSONEntity = new JSONObject();
        //Decode username and password
        String usernameAndPassword;
        try {
            usernameAndPassword = new String(Base64.decode(encodedUserPassword));
        } catch (IOException e) {
            JSONEntity.put("message","invalid token");
            return Response.status(Response.Status.BAD_REQUEST).entity(JSONEntity.toString()).build();
        }

        //Split username and password tokens
        final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
        final String username = tokenizer.nextToken();
        final String password = tokenizer.nextToken();



        Subject subject;
        try {

            subject = securityService.getSubject();
            if (!subject.isAuthenticated()) {
                JSONEntity.put("message","not authenticated");
                return Response.status(Response.Status.BAD_REQUEST).entity(JSONEntity.toString()).build();
            }
            String userNameFromSubject = subject.getPrincipal().toString();
            if(!userNameFromSubject.equals(username)){
                JSONEntity.put("message","invalid user or password");
                return Response.status(Response.Status.BAD_REQUEST).entity(JSONEntity.toString()).build();
            }

            User user = new User();
            user.setLogin(userNameFromSubject);
            List<String> roles =  new ArrayList<String>() ;
            for (RoleEnum r: RoleEnum.values())
            {
                if(subject.hasRole(r.getRole()))
                {
                    roles.add(r.getRole());
                }
            }
            user.setRoles(roles);
            Response.ResponseBuilder rb = Response.ok(user);
            return rb.build();
        } catch (Exception e) {
            LOG.debug("User  failed to log.");
            JSONEntity.put("message","invalid user or password");
            return Response.status(Response.Status.BAD_REQUEST).entity(JSONEntity.toString()).build();

        }

    }

    @GET
    @Path("/logout")
    @PermitAll
    public Response logout( @Context Request req)
    {
        securityService.getSubject().logout();
        Response.ResponseBuilder rb = Response.ok();
        return rb.build();
    }
}