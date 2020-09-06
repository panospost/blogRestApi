package com.panospost.common;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    protected Long id;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
