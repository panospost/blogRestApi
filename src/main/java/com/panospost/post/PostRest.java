package com.panospost.post;

import com.panospost.common.AbstractEntity;
import com.panospost.post.control.PostPersistenceService;
import com.panospost.post.entity.Post;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("post")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostRest extends AbstractEntity {

    @Inject
    PostPersistenceService persistenceService;

    @Path("create")
    @POST
    public Response createPost(@NotNull @Valid Post post) {

        return Response.ok( persistenceService.create(post)).build();
    }

    @Path("update")
    @POST
    public Response update(@NotNull @Valid Post post) {


        persistenceService.updatePost(post);

        return Response.ok(post).build();
    }

    @Path("update/{id}/{categoryId}")
    @POST
    public Response addNewCategoryToPost(@NotNull @PathParam("id") Long id, @NotNull @PathParam("id") Long categoryId) {

       return Response.ok(persistenceService.addNewCategoryToPost(id, categoryId))
               .build();
    }

    @Path("remove/{id}/{categoryId}")
    @DELETE
    public Response removeCategoryFromPost(@NotNull @PathParam("id") Long id, @NotNull @PathParam("id") Long categoryId) {

        return Response.ok( persistenceService.removeCategoryFromPost(id, categoryId))
                .build();
    }

    @Path("delete/{id}")
    @DELETE
    public Response deletePost(@NotNull @PathParam("id") Long id) {


        persistenceService.deletePost(id);

        return Response.ok().build();
    }

    @Path("read/{id}")
    @GET
    public Response readPost(@NotNull @PathParam("id") Long id) {
        return Response.ok(persistenceService.getPost(id)).build();
    }

    @Path("list")
    @GET
    public Response listOfPosts() {
        return Response.ok(persistenceService.listPosts()).build();
    }
}
