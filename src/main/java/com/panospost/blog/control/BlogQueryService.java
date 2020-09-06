package com.panospost.blog.control;

import com.panospost.blog.entity.Blog;
import com.panospost.user.entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class BlogQueryService {

    @PersistenceContext
    EntityManager entityManager;

    public Blog findBlogById(Long id) {
        return entityManager.find(Blog.class, id);
    }

    public List<Blog> findAllBlogs() {

        return entityManager.createNamedQuery(Blog.FIND_ALL_BLOGS, Blog.class).getResultList();
    }

    public Blog findBlogOfUser(Long id) {
        User user = entityManager.find(User.class, id);
        return entityManager.createNamedQuery(Blog.FIND_BLOG_OF_USER, Blog.class)
                .setParameter("email", user.getEmail()).getSingleResult();
    }

}
