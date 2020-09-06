package com.panospost.post.entity;

import com.panospost.category.entity.Category;
import com.panospost.common.AbstractEntity;
import com.panospost.user.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "post")
@NamedQueries({
        @NamedQuery(name = Post.FIND_ALL_POSTS, query = "select post from Post  post order by post.dateCreated"),
        @NamedQuery(name = Post.FIND_POST_BY_ID, query = "select post from Post  post where post.id = :id order by post.dateCreated"),
})
public class Post extends AbstractEntity {

    public static final String FIND_ALL_POSTS = "Post.findAll";
    public static final String FIND_POST_BY_ID = "Post.findById";

    @NotEmpty(message = "A Post title must be set")
    @Size(min = 3, max = 140, message = "The minimum character length should be 3 and max 140.")
    private String postTitle; //varchar 255

    @NotEmpty(message = "A Post body must be set")
    @Size(min = 3, max = 1000, message = "The minimum character length should be 3 and max 1000.")
    private String body; //varchar 255

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "posts")
    private Set<Category> categories = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "User_Id")
    private User postOwner;

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

    public void addACategory(Category category) {
         this.categories.add(category);
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public void removeCategory(Category cat) {
        this.categories.remove(cat);
    }

    public User getPostOwner() {
        return postOwner;
    }

    public void setPostOwner(User postOwner) {
        this.postOwner = postOwner;
    }
}
