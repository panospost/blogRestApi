package com.panospost.post.control;

import com.panospost.category.control.CategoryQueryService;
import com.panospost.category.entity.Category;
import com.panospost.post.entity.Post;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@DataSourceDefinition(
        name = "java:app/mydatabase",
        className = "com.mysql.cj.jdbc.Driver",
        url = "jdbc:mysql://localhost:3306/sga_schema",
        user = "root",
        password = "root"
)
@Stateless
public class PostPersistenceService {
    @PersistenceContext
    EntityManager entityManager;

    @Inject
    CategoryQueryService categoryQueryService;

    @Inject
    PostQueryService postQueryService;

    public Post create(Post post) {
        entityManager.persist(post);
        return post;
    }

    public Post updatePost(Post post) {

        entityManager.merge(post);

        return post;
    }


    public Post addNewCategoryToPost(Long id, Long categoryId) {
        Post post = postQueryService.findPostById(id);
        List<Category> categories = postQueryService.getAllCategoriesOfPost(id);
        Category category = categoryQueryService.findCategoryById(categoryId);
        if(!categories.contains(category)) {
            categories.add(category);
        }

        post.setCategories(new HashSet<>(categories));

        entityManager.merge(post);
        return post;
    }

    public Post removeCategoryFromPost(Long id, Long categoryId) {
        Post post = postQueryService.findPostById(id);
        Category cat = categoryQueryService.findCategoryById(categoryId);
        post.removeCategory(cat);

        entityManager.persist(post);
        return post;
    }

    public void deletePost(Long id) {
        postQueryService.findPostById(id);
        entityManager.remove(postQueryService.findPostById(id));
    }

    public Post getPost(Long id) {
        return postQueryService.findPostById(id);
    }

    public List<Post> listPosts() {
        return postQueryService.findAllPosts();
    }
}
