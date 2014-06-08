package dev.openshift.tapestry.angular.ws;

import dev.openshift.tapestry.angular.data.Product;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/json/product")
public class ProductResource {

    @GET
    @Path("/get")
    @Produces("application/json")
    public Product getProductInJSON() {

        Product product = new Product(1,"IPhone 5S",700.00,"/Assets/1");


        return product;

    }

    @POST
    @Path("/post")
    @Consumes("application/json")
    public Response createProductInJSON(Product product) {

        String result = "Product created : " + product;
        return Response.status(201).entity(result).build();

    }

}