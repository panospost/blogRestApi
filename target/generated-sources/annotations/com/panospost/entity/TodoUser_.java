package com.panospost.entity;

import com.panospost.common.AbstractEntity_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-04T12:12:54")
@StaticMetamodel(TodoUser.class)
public class TodoUser_ extends AbstractEntity_ {

    public static volatile SingularAttribute<TodoUser, String> password;
    public static volatile SingularAttribute<TodoUser, String> salt;
    public static volatile SingularAttribute<TodoUser, String> fullName;
    public static volatile SingularAttribute<TodoUser, String> email;

}