package com.nhom2.sharingblog.repositories;

import com.nhom2.sharingblog.entities.User;
import com.nhom2.sharingblog.repositories.interfaces.UserRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    EntityManager entityManager;
    @Override
    public User getUserNameById(int id) {
        User user = entityManager.find(User.class, id);
        return user;
    }
}
