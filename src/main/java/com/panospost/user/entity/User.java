package com.panospost.user.entity;


import com.panospost.common.AbstractEntity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
@NamedQueries({
        @NamedQuery(name = User.FIND_USER_BY_EMAIL, query = "select tU from User  tU where tU.email = :email"),
        @NamedQuery(name = User.FIND_ALL_USERS, query = "select user from User  user order by user.fullName"),
        @NamedQuery(name = User.FIND_USER_BY_ID, query = "select t from User t where t.id = :id and t.email = :email"),
        @NamedQuery(name = User.FIND_USER_BY_NAME, query = "select t from User t where t.fullName like :name ")
})
public class User extends AbstractEntity {
    public static final String FIND_USER_BY_EMAIL = "User.findByEmail";
    public static final String FIND_ALL_USERS = "User.findAll";
    public static final String FIND_USER_BY_ID = "User.findByIdAndEmail";
    public static final String FIND_USER_BY_NAME = "User.findByName";


    @JsonbTransient
    private String salt;


    @Column(length = 100, unique = true)
    @NotEmpty(message = "An email must be set")
    @Email(message = "Email must be in the format user@somedomain.com")
    private String email;

    @NotNull(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be a min of 8 and max of 100 characters")
    private String password;


    @NotEmpty(message = "Name must be set")
    @Size(min = 2, max = 100, message = "Name must be a min of 2 and max of 100 characters")
    private String fullName;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonbTransient
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

}
