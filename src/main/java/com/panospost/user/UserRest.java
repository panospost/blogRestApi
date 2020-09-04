package com.panospost.user;

import com.panospost.config.SecureAuth;
import com.panospost.config.SecurityFilter;
import com.panospost.service.SecurityUtil;
import com.panospost.user.control.UserPersistenceService;
import com.panospost.user.control.UserQueryService;
import com.panospost.user.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;

@Path("nuser")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserRest {


    @Inject
    private UserPersistenceService persistenceService;

    @Inject
    private UserQueryService userQueryService;

    @Inject
    private SecurityUtil securityUtil;

    @Inject
    private Logger logger;

    @Context
    private UriInfo uriInfo;

    @Path("create")
    @POST
    public Response createUser(@NotNull @Valid User user) {


        persistenceService.saverUser(user);

        return Response.ok(user).build();
    }

    @PUT
    @Path("update")
    @SecureAuth
    public Response updateUser(@NotNull @Valid User user) {
        persistenceService.updateUser(user);

        return Response.ok(user).build();
    }

    @GET
    @Path("find/{email}")
    @SecureAuth
    public User findUserByEmail(@NotNull @PathParam("email") String email) {

        return userQueryService.findUserByEmail(email);
    }

    @GET
    @Path("query")
    @SecureAuth
    public User findUserByEmailQueryParam(@NotNull @QueryParam("email") String email) {

        return userQueryService.findUserByEmail(email);
    }


    @GET
    @Path("search")
    @SecureAuth
    public Response searchUserByName(@NotNull @QueryParam("name") String name) {

        return Response.ok(userQueryService.findTodoUsersByName(name)).build();
    }

    @GET
    @Path("count")
    @SecureAuth
    public Response countUserByEmail(@QueryParam("email") @NotNull String email) {

        return Response.ok(userQueryService.countUserByEmail(email)).build();
    }

    @GET
    @Path("list")
    public Response listAllUsers() {

        return Response.ok(userQueryService.findAllUsers()).build();
    }

    @PUT
    @Path("update-email")
    @SecureAuth
    public Response updateEmail(@NotNull @QueryParam("id") Long id, @NotNull @QueryParam("email") String email) {
        User user = persistenceService.updateUserEmail(id, email);

        return Response.ok(user).build();
    }


    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response login(@NotEmpty(message = "Email field must be set") @FormParam("email") String email,
                          @NotEmpty(message = "Password field must be set") @FormParam("password") String password) {

        if (!securityUtil.authenticateUser(email, password)) {
            throw new SecurityException("Email or password is invalid");
        }

        String token = getToken(email);

        return Response.ok().header(AUTHORIZATION, SecurityFilter.BEARER + " " + token).build();
    }

    private String getToken(String email) {
        Key key = securityUtil.generateKey();


        String token = Jwts.builder().setSubject(email).setIssuer(uriInfo.getAbsolutePath().toString())

                .setIssuedAt(new Date()).setExpiration(securityUtil.toDate(LocalDateTime.now().plusMinutes(15)))

                .signWith(SignatureAlgorithm.HS512, key).setAudience(uriInfo.getBaseUri().toString())

                .compact();

        logger.log(Level.INFO, "Generated token is {0}", token);


        return token;
    }

}
