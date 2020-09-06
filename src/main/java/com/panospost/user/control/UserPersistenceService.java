package com.panospost.user.control;

import com.panospost.blog.entity.Blog;
import com.panospost.service.SecurityUtil;
import com.panospost.user.entity.User;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import java.util.List;
import java.util.Map;

@DataSourceDefinition(
        name = "java:app/mydatabase",
        className = "com.mysql.cj.jdbc.Driver",
        url = "jdbc:mysql://localhost:3306/sga_schema",
        user = "root",
        password = "root"
)
@Stateless
public class UserPersistenceService {

    @Inject
    private SecurityUtil securityUtil;

    @Inject
    private UserQueryService queryService;


    @Context
    private SecurityContext securityContext;

    @PersistenceContext
    EntityManager entityManager;

    public User saverUser(User user) {
        Map<String, String> credentialMap = securityUtil.hashPassword(user.getPassword());

        if (user.getId() == null) { //If it's a new entity, save it

            user.setPassword(credentialMap.get("hashedPassword"));
            user.setSalt(credentialMap.get("salt"));

            entityManager.persist(user); //#save}
            createBlogForUser(user);
        }
        credentialMap.clear();

        return user;
    }

    public User updateUser(User user) {


        List list = queryService.countUser(user.getId(), user.getEmail());
        Integer count = (Integer) list.get(0);


        if (user.getId() != null && count == 1) {
            entityManager.merge(user);

        }

        return user;
    }

    public User updateUserEmail(Long id, String email) {
        List list = queryService.countUserByEmail(email);
        Integer count = (Integer) list.get(0);

        if (count == 0) {
            User user = queryService.findUser(id); //Substitute for findTodoUserById(Long id)
            if (user != null) {
                user.setEmail(email);
                entityManager.merge(user);

                return user;
            }

        }

        return null;
    }

    private void createBlogForUser(User user) {
        Blog blog = new Blog();
        blog.setBlogOwner(user);
        blog.setBlogTitle(user.getFullName() + "' s blog");

        entityManager.persist(blog);
        return;
    }
}
