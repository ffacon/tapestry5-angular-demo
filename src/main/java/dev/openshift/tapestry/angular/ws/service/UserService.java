package dev.openshift.tapestry.angular.ws.service;
 
import dev.openshift.tapestry.angular.data.Comment;
import dev.openshift.tapestry.angular.data.user.Login;
import dev.openshift.tapestry.angular.ws.data.UserDatabase;
import dev.openshift.tapestry.angular.ws.model.User;

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

    @GET
    @Path("/users/{id}")
    @PermitAll
    public Response getUserById(@PathParam("id") int id, @Context Request req)
    {
        Response.ResponseBuilder rb = Response.ok(UserDatabase.getUserById(id));
        return rb.build();
    }
     

    @PUT
    @Path("/users/{id}")
    @RolesAllowed("ADMIN")
    public Response updateUserById(@PathParam("id") int id)
    {
        //Update the User resource
        UserDatabase.updateUser(id);
        return Response.status(200).build();
    }

    @GET
    @Path("/validate")
    @PermitAll
    public Response validate( @Context Request req)
    {
        Response.ResponseBuilder rb = Response.ok();//UserDatabase.getUserById(1));
        return rb.build();
    }

    @POST
    @Path("/authentication")
    @Consumes("application/json")
    @PermitAll
    public Response authentication(Login login) {


        Response.ResponseBuilder rb = Response.ok();//UserDatabase.getUserById(1));
        return rb.build();

    }

    @GET
    @Path("/account")
    @PermitAll
    public Response account( @Context Request req)
    {
        User user = new User();
        user.setId(1);
        user.setFirstName("demo");
        user.setLastName("user");
        user.setUri("/user-management/users/1");
        user.setLastModified(new Date());
        user.setEmail("demo@demo.com");

        Response.ResponseBuilder rb = Response.ok(user);//UserDatabase.getUserById(1));
        return rb.build();
    }
}