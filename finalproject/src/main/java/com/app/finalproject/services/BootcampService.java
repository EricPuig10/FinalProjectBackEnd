package com.app.finalproject.services;

import com.app.finalproject.repositories.IBootcampRepository;
import org.springframework.stereotype.Service;

@Service
public class BootcampService implements IBootcampService {

    private IBootcampRepository bootcampRepository;

    public BootcampService(IBootcampRepository bootcampRepository) {
        this.bootcampRepository = bootcampRepository;
    }
}
