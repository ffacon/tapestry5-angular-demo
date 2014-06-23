package dev.openshift.tapestry.angular.ws.service;
 
import dev.openshift.tapestry.angular.data.user.Login;
import dev.openshift.tapestry.angular.services.UserDatabase;
import dev.openshift.tapestry.angular.data.user.User;
import org.apache.tapestry5.ioc.annotations.Inject;

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
    @Consumes("application/json")
    @PermitAll
    public Response authentication(Login login) {


        Response.ResponseBuilder rb = Response.ok();
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

        Response.ResponseBuilder rb = Response.ok(user);
        return rb.build();
    }
}