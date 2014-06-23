package dev.openshift.tapestry.angular.ws.service;
 
import dev.openshift.tapestry.angular.data.user.Login;
import dev.openshift.tapestry.angular.services.UserDatabase;
import dev.openshift.tapestry.angular.data.user.User;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.jboss.resteasy.util.Base64;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.util.Date;


@Path("/app/user")
public class UserService
{
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
            String basicAuth = "Basic "+ new String(Base64.encodeBytes(userPassword.getBytes()));
            rb = Response.ok().header("Authorization",basicAuth);
            return rb.build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).entity("invalid login or password").build();
        }
    }




    @GET
    @Path("/account")
    @PermitAll
    public Response account( @Context Request req)
    {
        User user = userDatabase.getUserById(1);
        Response.ResponseBuilder rb = Response.ok(user);
        return rb.build();
    }
}