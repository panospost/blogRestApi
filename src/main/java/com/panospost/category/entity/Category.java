package com.panospost.category.entity;

import com.panospost.blog.entity.Blog;
import com.panospost.common.AbstractEntity;
import com.panospost.post.entity.Post;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
@NamedQueries({
        @NamedQuery(name = Category.FIND_ALL_CATEGORIES, query = "select cat from Category  cat"),
        @NamedQuery(name = Category.FIND_CATEGORY_BY_ID, query = "select cat from Category  cat where cat.id = :id"),
})
public class Category extends AbstractEntity {

    public static final String FIND_ALL_CATEGORIES = "Category.findAll";
    public static final String FIND_CATEGORY_BY_ID = "Category.findById";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Blog_Id")
    private Blog categoryOwner;

    @NotEmpty(message = "A Category name must be set")
    @Size(min = 3, max = 50, message = "The minimum category title length should be 3 and max 50.")
    private String categoryTitle; //varchar 255

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.PERSIST})
    @JoinTable(name = "posts_categories", joinColumns =
    @JoinColumn(name = "catId", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "postid", referencedColumnName = "ID"))
    private Set<Post> posts = new HashSet<>();

    public Blog getCategoryOwner() {
        return categoryOwner;
    }

    public void setCategoryOwner(Blog categoryOwner) {
        this.categoryOwner = categoryOwner;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
