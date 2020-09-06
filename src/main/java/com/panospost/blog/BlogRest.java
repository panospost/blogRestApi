package com.panospost.blog;

import com.panospost.blog.control.BlogQueryService;
import com.panospost.blog.entity.Blog;
import com.panospost.config.SecureAuth;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("blog")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BlogRest {

    @Inject
    private BlogQueryService queryService;

    @GET
    @Path("list")
    public Response listAllBlogs() {

        return Response.ok(queryService.findAllBlogs()).build();
    }

    @GET
    @Path("find/{id}")
    @SecureAuth
    public Blog findBlogById(@NotNull @PathParam("id") Long id) {

        return queryService.findBlogById(id);
    }

    @GET
    @Path("user/{id}")
    @SecureAuth
    public Blog findBlogOfUser(@NotNull @PathParam("id") Long id) {

        return queryService.findBlogOfUser(id);
    }
}
