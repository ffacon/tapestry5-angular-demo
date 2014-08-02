package dev.openshift.tapestry.angular.ws.service;
 
import dev.openshift.tapestry.angular.data.user.Token;
import dev.openshift.tapestry.angular.services.UserDatabase;
import dev.openshift.tapestry.angular.entity.User;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.jboss.resteasy.util.Base64;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.StringTokenizer;


@Path("/app/user")
public class UserService
{
    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";

    @Inject
    UserDatabase userDatabase;

    @GET
    @Path("/users/{id}")
    @PermitAll
    public Response getUserById(@PathParam("id") int id, @Context Request req)
    {
        Response.ResponseBuilder rb = Response.ok(userDatabase.getUserById(id));
        return rb.build();
    }
     

    @PUT
    @Path("/users/{id}")
    @RolesAllowed("ADMIN")
    public Response updateUserById(@PathParam("id") int id)
    {
        //Update the User resource
        userDatabase.updateUser(id);
        return Response.status(200).build();
    }

    @GET
    @Path("/validate")
    @PermitAll
    public Response validate( @Context Request req)
    {
        Response.ResponseBuilder rb = Response.ok();
        return rb.build();
    }

    @POST
    @Path("/authentication")
    @Consumes("application/x-www-form-urlencoded")
    @PermitAll
    public Response postLogin(@FormParam("j_username") String username,@FormParam("j_password") String password) {
        Response.ResponseBuilder rb;
        User user = userDatabase.getUserByLogin(username);
        if(user != null && user.getPassword().equals(password)){
            String userPassword= username + ":" + password;
            String basicAuth = new String(Base64.encodeBytes(userPassword.getBytes()));
            Token token = new Token();
            token.setAccess_token("Basic "+basicAuth);
            token.setExpires_in(1799);
            token.setToken_type("bearer");
            token.setScope("read write");
            rb = Response.ok(token);
            return rb.build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).entity("invalid login or password").build();
        }
    }




    @GET
    @Path("/account")
    @PermitAll
    public Response account( @Context Request req, @Context HttpHeaders headers)
    {
        String auth = headers.getHeaderString(AUTHORIZATION_PROPERTY);
        //Get encoded username and password
        final String encodedUserPassword = auth.replaceFirst(AUTHENTICATION_SCHEME + " ", "");

        //Decode username and password
        String usernameAndPassword;
        try {
            usernameAndPassword = new String(Base64.decode(encodedUserPassword));
        } catch (IOException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("invalid token").build();
        }

        //Split username and password tokens
        final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
        final String username = tokenizer.nextToken();
        final String password = tokenizer.nextToken();

        User user = userDatabase.getUserByLogin(username);
        if(user != null && user.getPassword().equals(password)){

            Response.ResponseBuilder rb = Response.ok(user);
            return rb.build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).entity("invalid user or password").build();
        }
    }

    @GET
    @Path("/logout")
    @PermitAll
    public Response logout( @Context Request req)
    {
        Response.ResponseBuilder rb = Response.ok();
        return rb.build();
    }
}