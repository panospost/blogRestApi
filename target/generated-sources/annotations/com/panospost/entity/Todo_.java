package com.panospost.entity;

import com.panospost.common.AbstractEntity_;
import com.panospost.entity.TodoUser;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-04T12:12:54")
@StaticMetamodel(Todo.class)
public class Todo_ extends AbstractEntity_ {

    public static volatile SingularAttribute<Todo, Boolean> archived;
    public static volatile SingularAttribute<Todo, String> task;
    public static volatile SingularAttribute<Todo, LocalDate> dateCreated;
    public static volatile SingularAttribute<Todo, TodoUser> todoOwner;
    public static volatile SingularAttribute<Todo, LocalDate> dueDate;
    public static volatile SingularAttribute<Todo, Boolean> completed;
    public static volatile SingularAttribute<Todo, Boolean> remind;

}