package com.app.finalproject.controllers;

import com.app.finalproject.services.IBootcampService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
public class BootcampController {

    private IBootcampService bootcampService;

    public BootcampController(IBootcampService bootcampService) {
        this.bootcampService = bootcampService;
    }
}
