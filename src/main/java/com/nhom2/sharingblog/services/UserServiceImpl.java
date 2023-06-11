package com.nhom2.sharingblog.services;

import com.nhom2.sharingblog.entities.User;
import com.nhom2.sharingblog.repositories.UserRepository;
import com.nhom2.sharingblog.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User getUserById(int id) {
        return userRepository.findById(id);
    }
}
