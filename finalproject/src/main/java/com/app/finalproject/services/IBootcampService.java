package com.app.finalproject.services;

import com.app.finalproject.dtos.bootcamp.BootcampResDto;
import com.app.finalproject.models.User;

import java.util.List;

public interface IBootcampService {
    List<BootcampResDto> getAll();

    BootcampResDto findById(Long id, User auth);
}
