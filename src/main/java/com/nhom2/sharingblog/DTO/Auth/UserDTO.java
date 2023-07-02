package com.nhom2.sharingblog.DTO.Auth;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
    private String name;
    private Integer roleId;
    private String username;
    private String address;
    private String avatar;
    private Date dob;
    private String email;
    private Integer follower;
    private Integer following;
    private Integer gender;

}