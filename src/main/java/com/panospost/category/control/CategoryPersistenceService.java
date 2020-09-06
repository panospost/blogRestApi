package com.panospost.category.control;


import com.panospost.blog.entity.Blog;
import com.panospost.category.entity.Category;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@DataSourceDefinition(
        name = "java:app/mydatabase",
        className = "com.mysql.cj.jdbc.Driver",
        url = "jdbc:mysql://localhost:3306/sga_schema",
        user = "root",
        password = "root"
)
@Stateless
public class CategoryPersistenceService {

    @PersistenceContext
    EntityManager entityManager;

    public Category saveCategory(Category category) {
        entityManager.persist(category);
        return category;
    }

    public Category updateCategory(Category cat, Blog blog) {
        cat.setCategoryOwner(blog);
        entityManager.merge(cat);
        return cat;
    }

}
