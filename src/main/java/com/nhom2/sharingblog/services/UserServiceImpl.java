package com.nhom2.sharingblog.services;

import com.nhom2.sharingblog.DTO.Auth.UserDTO;
import com.nhom2.sharingblog.DTO.UpdateProfileDTO;
import com.nhom2.sharingblog.entities.User;
import com.nhom2.sharingblog.repositories.UserRepository;
import com.nhom2.sharingblog.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AmazonClient amazonClient;
    @Override
    public User getUserById(int id) {
        return userRepository.findById(id);
    }
    @Override
    public User getUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateProfile(UpdateProfileDTO updateProfileDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = this.getUserByEmail(currentPrincipalName);
        modelMapper.map(updateProfileDTO, user);
        userRepository.save(user);
        return user;
    }

    @Override
    public User updateAvatar(MultipartFile multipartFile) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = this.getUserByEmail(currentPrincipalName);
        user.setAvatar(this.amazonClient.uploadFile(multipartFile, "avatar/" + user.getId() + "/"));
        userRepository.save(user);
        return user;
    }
}
