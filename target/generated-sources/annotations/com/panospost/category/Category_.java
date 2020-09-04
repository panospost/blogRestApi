package com.panospost.category;

import com.panospost.blog.Blog;
import com.panospost.common.AbstractEntity_;
import com.panospost.post.Post;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-04T12:12:54")
@StaticMetamodel(Category.class)
public class Category_ extends AbstractEntity_ {

    public static volatile SingularAttribute<Category, Blog> categoryOwner;
    public static volatile SingularAttribute<Category, String> categoryTitle;
    public static volatile SetAttribute<Category, Post> posts;

}