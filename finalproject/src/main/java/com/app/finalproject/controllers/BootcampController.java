package com.app.finalproject.controllers;

import com.app.finalproject.auth.facade.IAuthenticationFacade;
import com.app.finalproject.dtos.bootcamp.BootcampReqDto;
import com.app.finalproject.dtos.bootcamp.BootcampResDto;
import com.app.finalproject.models.Bootcamp;
import com.app.finalproject.models.User;
import com.app.finalproject.services.boocampS.IBootcampService;
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

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/bootcamps")
    ResponseEntity<List<BootcampResDto>> getAll(){
        var bootcamps = bootcampService.getAll();
        return new ResponseEntity<>(bootcamps, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/bootcamps/{id}")
    BootcampResDto getBootcampById(@PathVariable Long id){
        User auth = authenticationFacade.getAuthUser();
        var bootcamp = this.bootcampService.findById(id, auth);
        return bootcamp;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/bootcamps")
    Bootcamp createBootcamp(@RequestBody BootcampReqDto bootcampReqDto){
        var authUser = authenticationFacade.getAuthUser();
        return bootcampService.createBootcamp(bootcampReqDto, authUser);
    }



    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/bootcamps/{id}")
    ResponseEntity<Boolean> deleteBootcamp(@PathVariable Long id){
        User authUser = authenticationFacade.getAuthUser();
        var bootcampToDelete = bootcampService.deleteBootcamp(id, authUser);
        return new ResponseEntity<>(bootcampToDelete, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/bootcamps/{id}")
    BootcampResDto updateBootcamp(@PathVariable Long id, @RequestBody BootcampReqDto bootcampReqDto){
        User authUser = authenticationFacade.getAuthUser();
        return bootcampService.updateBootcamp(bootcampReqDto, id, authUser);
    }



}
