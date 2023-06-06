package com.nhom2.sharingblog.repositories.interfaces;

import com.nhom2.sharingblog.entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository {
    public User getUserNameById(int id);
}
