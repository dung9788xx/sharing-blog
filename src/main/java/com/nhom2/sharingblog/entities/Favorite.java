package com.nhom2.sharingblog.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "favorites")
public class Favorite extends BaseEntity {
    @Column
    private Integer postId;
    @Column
    private Integer userId;

    public Favorite() {
    }

    public Favorite(Integer postId, Integer userId) {
        this.postId = postId;
        this.userId = userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
