package com.app.finalproject.controllers;

import com.app.finalproject.auth.facade.IAuthenticationFacade;
import com.app.finalproject.dtos.bootcamp.BootcampResDto;
import com.app.finalproject.services.IBootcampService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:3000/")
public class BootcampController {

    private IBootcampService bootcampService;

    private IAuthenticationFacade authenticationFacade;

    public BootcampController(IBootcampService bootcampService, IAuthenticationFacade authenticationFacade) {
        this.bootcampService = bootcampService;
        this.authenticationFacade = authenticationFacade;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/bootcamps")
    ResponseEntity<List<BootcampResDto>> getAll(){
        var bootcamps = bootcampService.getAll();
        return new ResponseEntity<>(bootcamps, HttpStatus.OK);
    }




}
