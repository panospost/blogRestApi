package com.panospost.post;

import com.panospost.category.Category;
import com.panospost.common.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "post")
public class Post extends AbstractEntity {

    @NotEmpty(message = "A Post title must be set")
    @Size(min = 3, max = 140, message = "The minimum character length should be 3 and max 140.")
    private String postTitle; //varchar 255

    @NotEmpty(message = "A Post body must be set")
    @Size(min = 3, max = 1000, message = "The minimum character length should be 3 and max 1000.")
    private String body; //varchar 255

    @ManyToMany(mappedBy = "posts")
    private Set<Category> categories = new HashSet<>();

    private LocalDate dateCreated;

    @PrePersist
    private void init() {
        setDateCreated(LocalDate.now());
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
