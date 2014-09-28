package dev.openshift.tapestry.angular.ws.service;

import dev.openshift.tapestry.angular.data.user.RoleConsts;
import dev.openshift.tapestry.angular.data.user.RoleEnum;
import dev.openshift.tapestry.angular.entity.Comment;
import dev.openshift.tapestry.angular.services.CommentDAO;
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

    @Inject
    CommentDAO comments;

    @POST
    @Path("/comments")
    @Consumes("application/json")
    @RolesAllowed(RoleConsts.USER_ROLE)
    public Response postComment(Comment comment) {

        comment.setLikes(0);
        comments.add(comment);
        return Response.status(201).entity(comment).build();

    }


    @GET
    @Path("/comments/{id}")
    @Consumes("application/json")
    @PermitAll
    public List<Comment> getComments(@PathParam("id")String id) {

        List<Comment> ret = comments.getCommentsByPhoneId(id);
        return ret;

    }


    @GET
    @Path("/comments/like/{id}")
    @Consumes("application/json")
    @PermitAll
    public Comment incLike(@PathParam("id")int id) {

        Comment ret = comments.incLike(id);
        return ret;

    }


    @DELETE
    @Path("/comments/{id}")
    @Consumes("application/json")
    @RolesAllowed(RoleConsts.ADMIN_ROLE)
    public Comment deleteComment(@PathParam("id")int id) {

        Comment ret = comments.delete(id);
        return ret;

    }

}