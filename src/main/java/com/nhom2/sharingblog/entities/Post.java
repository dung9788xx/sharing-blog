package com.nhom2.sharingblog.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "posts")
public class Post extends BaseEntity {
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private Boolean censored;
    @Column
    private Boolean isPublic;
    @Column
    private Integer vote;
    @Column
    private Integer typeId;
    @Column
    private Integer userId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCensored() {
        return censored;
    }

    public void setCensored(Boolean censored) {
        this.censored = censored;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
