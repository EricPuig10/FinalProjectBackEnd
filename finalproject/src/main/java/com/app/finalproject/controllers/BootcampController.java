package com.app.finalproject.controllers;

import com.app.finalproject.auth.facade.IAuthenticationFacade;
import com.app.finalproject.dtos.bootcamp.BootcampReqDto;
import com.app.finalproject.dtos.bootcamp.BootcampResDto;
import com.app.finalproject.models.Bootcamp;
import com.app.finalproject.models.User;
import com.app.finalproject.services.IBootcampService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/bootcamps/{id}")
    BootcampResDto getBootcampById(@PathVariable Long id){
        User auth = authenticationFacade.getAuthUser();
        var bootcamp = this.bootcampService.findById(id, auth);
        return bootcamp;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/bootcamps")
    Bootcamp createBootcamp(@RequestBody BootcampReqDto bootcampReqDto){
        var authUser = authenticationFacade.getAuthUser();

        return bootcampService.createBootcamp(bootcampReqDto, authUser);
    }



    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/bootcamps/{id}")
    ResponseEntity<BootcampResDto> deleteBootcamp(@PathVariable Long id){
        User authUser = authenticationFacade.getAuthUser();
        var bootcamp = bootcampService.deleteBootcamp(id, authUser);
        return new ResponseEntity<>(bootcamp, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/bootcamps/{id}")
    BootcampResDto updateBootcamp(@PathVariable Long id, @RequestBody BootcampReqDto bootcampReqDto){
        User authUser = authenticationFacade.getAuthUser();
        return bootcampService.updateBootcamp(bootcampReqDto, id, authUser);
    }



}
