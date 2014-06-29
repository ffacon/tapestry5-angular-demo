package dev.openshift.tapestry.angular.ws.service;

import dev.openshift.tapestry.angular.data.Comment;
import dev.openshift.tapestry.angular.data.PhoneDetails;
import dev.openshift.tapestry.angular.data.Product;
import dev.openshift.tapestry.angular.services.PhoneCatalog;
import org.apache.tapestry5.ioc.annotations.Inject;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/json/phone")
public class ProductResource {

    @Inject
    PhoneCatalog phoneCatalog;

    @POST
    @Path("/comments")
    @Consumes("application/json")
    @PermitAll
    public Response postComment(Comment comment) {

        comment.setLikes(0);
        phoneCatalog.addComment(comment);
        return Response.status(201).entity(comment).build();

    }


    @GET
    @Path("/comments/{id}")
    @Consumes("application/json")
    @PermitAll
    public List<Comment> getComments(@PathParam("id")String id) {

        List<Comment> ret = phoneCatalog.getComment(id);
        return ret;

    }


    @GET
    @Path("/comments/like/{id}")
    @Consumes("application/json")
    @PermitAll
    public Comment incLike(@PathParam("id")int id) {

        Comment ret = phoneCatalog.incLike(id);
        return ret;

    }


    @DELETE
    @Path("/comments/{id}")
    @Consumes("application/json")
    @RolesAllowed("ADMIN")
    public Comment deleteComment(@PathParam("id")int id) {

        Comment ret = phoneCatalog.deleteComment(id);
        return ret;

    }

}