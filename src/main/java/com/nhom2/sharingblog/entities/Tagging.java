package com.nhom2.sharingblog.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tagging")
public class Tagging extends BaseEntity {
    @Column
    private Integer postId;
    @Column
    private Integer tagId;

    public Tagging() {
    }

    public Tagging(Integer postId, Integer tagId) {
        this.postId = postId;
        this.tagId = tagId;
    }

    public Integer getPostId() {
        return postId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
}
