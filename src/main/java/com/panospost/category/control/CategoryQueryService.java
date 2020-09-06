package com.panospost.category.control;

import com.panospost.category.entity.Category;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CategoryQueryService {
    @PersistenceContext
    EntityManager entityManager;

    public List<Category> findAllCategories() {
        return entityManager.createNamedQuery(Category.FIND_ALL_CATEGORIES, Category.class).getResultList();
    }

    public Category findCategoryById(Long id) {
       Category category  = entityManager.createNamedQuery(Category.FIND_CATEGORY_BY_ID, Category.class)
                .setParameter("id", id).getSingleResult();

       if(category != null) {
           return  category;
       }

       return null;
    }

}
