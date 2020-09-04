package com.panospost.user.control;

import com.panospost.service.SecurityUtil;
import com.panospost.user.entity.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import java.util.Collection;
import java.util.List;

@Stateless
public class UserQueryService {

    @PersistenceContext
    EntityManager entityManager;


    @Inject
    private SecurityUtil securityUtil;


    @Context
    private SecurityContext securityContext;

    public User findUserByEmail(String email) {
        try {
            return entityManager.createNamedQuery(User.FIND_USER_BY_EMAIL, User.class)
                    .setParameter("email", email).getSingleResult();

        } catch (NonUniqueResultException | NoResultException e) {
            return null;
        }
    }

    public List<User> findAllUsers() {

        return entityManager.createNamedQuery(User.FIND_ALL_USERS, User.class).getResultList();
    }

    public User findUserById(Long id) {

        try {
            return entityManager.createNamedQuery(User.FIND_USER_BY_ID, User.class)
                    .setParameter("id", id).setParameter("email", securityContext.getUserPrincipal().getName()).getSingleResult();
        } catch (NonUniqueResultException | NoResultException e) {
            return null;
        }
    }

    public User findUser(Long id) { //Todo- discard for above method
        return entityManager.find(User.class, id);
    }

    public Collection<User> findTodoUsersByName(String name) {
        return entityManager.createNamedQuery(User.FIND_USER_BY_NAME, User.class)
                .setParameter("name", "%" + name + "%").getResultList(); //jo
    }

    public List countUserByEmail(String email) {
        List resultList = entityManager.createNativeQuery("select count (id) from user where  exists (select id from user where email = ?)")
                .setParameter(1, email).getResultList();

        return resultList;
    }

    public List countUser(Long id, String email) {
        return entityManager.createNativeQuery("select count (id) from user where  exists (select id from user where email = ?1 and id = ?2)")
                .setParameter(1, email).setParameter(2, id).getResultList();
    }


    public boolean authenticateUser(String email, String plainTextPassword) {

        User user = findUserByEmail(email);

        if (user != null) {
            return securityUtil.passwordsMatch(user.getPassword(), user.getSalt(), plainTextPassword);
        }
        return false;

    }

}
