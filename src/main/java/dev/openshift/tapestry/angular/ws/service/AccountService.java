package dev.openshift.tapestry.angular.ws.service;

import dev.openshift.tapestry.angular.entity.User;
import dev.openshift.tapestry.angular.services.UserDAO;
import org.apache.tapestry5.ioc.annotations.Inject;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;



@Path("/app/account")
public class AccountService
{

    @Inject
    UserDAO userDatabase;


    @POST
    @Path("/register")
    @Consumes("application/json")
    @PermitAll
    public Response addUser(User newUser) {

        User user = userDatabase.getUserByLogin(newUser.getLogin());
        if (user != null) {
            //NOT_MODIFIED
            return Response.status(304).build();
        }
        else {

            userDatabase.add(newUser);
            //CREATED
            return Response.status(201).build();
        }
    }

}