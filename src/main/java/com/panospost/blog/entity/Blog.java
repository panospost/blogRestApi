package com.panospost.blog.entity;

import com.panospost.common.AbstractEntity;
import com.panospost.user.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "blog")
@NamedQueries({
        @NamedQuery(name = Blog.FIND_BLOG_OF_USER, query = "select blog from Blog blog where blog.blogOwner.id = :email"),
        @NamedQuery(name = Blog.FIND_ALL_BLOGS, query = "select blog from Blog  blog order by blog.blogTitle"),
      })
public class Blog extends AbstractEntity {
    public static final String FIND_BLOG_OF_USER = "Blog.findBlogOfUser";
    public static final String FIND_ALL_BLOGS = "Blog.findAll";

    @NotEmpty(message = "A Blog name must be set")
    @Size(min = 3, max = 140, message = "The minimum character length should be 3 and max 140.")
    private String blogTitle; //varchar 255

    @OneToOne
    private User blogOwner;

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public User getBlogOwner() {
        return blogOwner;
    }

    public void setBlogOwner(User blogOwner) {
        this.blogOwner = blogOwner;
    }
}
