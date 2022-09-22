package com.app.finalproject.services;

import com.app.finalproject.models.User;
import com.app.finalproject.repositories.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    private IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return (User) userRepository.findById(id).get();
    }
}
