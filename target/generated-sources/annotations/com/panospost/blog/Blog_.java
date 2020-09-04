package com.panospost.blog;

import com.panospost.common.AbstractEntity_;
import com.panospost.user.entity.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-04T12:12:54")
@StaticMetamodel(Blog.class)
public class Blog_ extends AbstractEntity_ {

    public static volatile SingularAttribute<Blog, User> blogOwner;
    public static volatile SingularAttribute<Blog, String> blogTitle;

}