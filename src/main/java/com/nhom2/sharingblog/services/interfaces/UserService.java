package com.nhom2.sharingblog.services.interfaces;

import com.nhom2.sharingblog.DTO.Auth.UserDTO;
import com.nhom2.sharingblog.DTO.UpdateProfileDTO;
import com.nhom2.sharingblog.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    public User getUserById(int id);
    public User getUserByUserName(String username);
    public User getUserByEmail(String email);

    public User save(User user);

    public User updateProfile(UpdateProfileDTO updateProfileDTO);
    public User updateAvatar(MultipartFile multipartFile);
}
