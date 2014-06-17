package dev.openshift.tapestry.angular.ws;

import dev.openshift.tapestry.angular.data.Comment;
import dev.openshift.tapestry.angular.data.PhoneDetails;
import dev.openshift.tapestry.angular.data.Product;
import dev.openshift.tapestry.angular.services.PhoneCatalog;
import org.apache.tapestry5.ioc.annotations.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/json/phone")
public class ProductResource {

    @Inject
    PhoneCatalog phoneCatalog;


    @GET
    @Path("/get")
    @Produces("application/json")
    public Product getProductInJSON() {

        Product product = new Product(1,"IPhone 5S",700.00,"/Assets/1");


        return product;

    }

    @POST
    @Path("/comments")
    @Consumes("application/json")
    public Response postComment(Comment comment) {

        comment.setLikes(0);
        phoneCatalog.addComment(comment);
        return Response.status(201).entity(comment).build();

    }

    @GET
    @Path("/comments/{id}")
    @Consumes("application/json")
    public List<Comment> getComments(@PathParam("id")String id) {

        List<Comment> ret = phoneCatalog.getComment(id);
        return ret;

    }

    @GET
    @Path("/comments/like/{id}")
    @Consumes("application/json")
    public Comment incLike(@PathParam("id")int id) {

        Comment ret = phoneCatalog.incLike(id);
        return ret;

    }

}