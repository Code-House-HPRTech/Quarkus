package com.hprtech.restclient;

import com.hprtech.dto.Post;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("")
@RegisterRestClient(baseUri = "https://jsonplaceholder.typicode.com")
public interface JsonPlaceholderRestClient {

    @GET
    @Path("/posts")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> getAllPost();
}
