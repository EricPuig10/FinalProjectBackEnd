package com.app.finalproject.services;

import com.app.finalproject.models.User;

import java.util.List;

public interface IUserService {
    List<User> getAll();

    User getById(Long id);

}
