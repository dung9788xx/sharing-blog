package com.nhom2.sharingblog.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "post_types")
public class PostType extends BaseEntity {
    @Column
    private String name;

    public PostType() {
    }

    public PostType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
