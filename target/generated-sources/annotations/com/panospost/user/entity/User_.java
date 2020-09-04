package com.panospost.user.entity;

import com.panospost.common.AbstractEntity_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-04T12:12:54")
@StaticMetamodel(User.class)
public class User_ extends AbstractEntity_ {

    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> salt;
    public static volatile SingularAttribute<User, String> fullName;
    public static volatile SingularAttribute<User, String> email;

}