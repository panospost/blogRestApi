package com.panospost.category;

import com.panospost.blog.Blog;
import com.panospost.common.AbstractEntity;
import com.panospost.post.Post;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "Blog_Id")
    private Blog categoryOwner;

    @NotEmpty(message = "A Category name must be set")
    @Size(min = 3, max = 50, message = "The minimum category title length should be 3 and max 50.")
    private String categoryTitle; //varchar 255

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "post",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
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
}
