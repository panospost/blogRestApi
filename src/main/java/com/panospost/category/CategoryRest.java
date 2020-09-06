package com.panospost.category;

import com.panospost.blog.control.BlogQueryService;
import com.panospost.blog.entity.Blog;
import com.panospost.category.control.CategoryPersistenceService;
import com.panospost.category.control.CategoryQueryService;
import com.panospost.category.entity.Category;
import com.panospost.config.SecureAuth;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("category")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoryRest {

    @Inject
    CategoryPersistenceService categoryPersistenceService;

    @Inject
    CategoryQueryService queryService;

    @Inject
    BlogQueryService blogQueryService;

    @Path("create")
    @POST
    @SecureAuth
    public Response createCategory(@NotNull @Valid Category category) {

        categoryPersistenceService.saveCategory(category);

        return Response.ok(category).build();
    }

    @Path("/{id}/{blogId}")
    @POST
    @SecureAuth
    public Response createCategory(@NotNull @PathParam("id") Long id, @PathParam("blogId") Long blogId) {


        Category cat = queryService.findCategoryById(id);
        Blog blog = blogQueryService.findBlogById(blogId);

        if (cat == null || blog == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.ok(categoryPersistenceService.updateCategory(cat, blog)).build();
    }

    @GET
    @Path("list")
    public Response listAllCategories() {

        return Response.ok(queryService.findAllCategories()).build();
    }


}
