package com.panospost.post;

import com.panospost.category.Category;
import com.panospost.common.AbstractEntity_;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-04T12:12:54")
@StaticMetamodel(Post.class)
public class Post_ extends AbstractEntity_ {

    public static volatile SingularAttribute<Post, LocalDate> dateCreated;
    public static volatile SingularAttribute<Post, String> postTitle;
    public static volatile SetAttribute<Post, Category> categories;
    public static volatile SingularAttribute<Post, String> body;

}