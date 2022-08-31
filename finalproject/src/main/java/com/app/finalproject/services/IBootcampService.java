package com.app.finalproject.services;

import com.app.finalproject.dtos.bootcamp.BootcampResDto;

import java.util.List;

public interface IBootcampService {
    List<BootcampResDto> getAll();
}
