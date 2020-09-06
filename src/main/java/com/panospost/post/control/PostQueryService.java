package com.panospost.post.control;

import com.panospost.category.entity.Category;
import com.panospost.post.entity.Post;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PostQueryService {

    @PersistenceContext
    EntityManager entityManager;

    public List<Post> findAllPosts() {
        return entityManager.createNamedQuery(Post.FIND_ALL_POSTS, Post.class).getResultList();
    }

    public Post findPostById(Long id) {
        Post post = entityManager.find(Post.class, id);
        if(post != null) {
            return post;
        }

        return null;
    }

    public List<Category> getAllCategoriesOfPost(Long id) {
        return entityManager.createQuery(
                "select cat from Category cat  join fetch cat.posts p where p.id = :id", Category.class)
                .setParameter("id", id).getResultList();
    }


}
