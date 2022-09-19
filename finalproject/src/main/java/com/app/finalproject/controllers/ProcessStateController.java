package com.app.finalproject.controllers;


import com.app.finalproject.auth.facade.IAuthenticationFacade;
import com.app.finalproject.dtos.candidats.CandidatRes;
import com.app.finalproject.dtos.process.ProcessRes;
import com.app.finalproject.models.User;
import com.app.finalproject.services.ICandidatService;
import com.app.finalproject.services.IProcessService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class ProcessStateController {

    private IProcessService processService;
    private IAuthenticationFacade authenticationFacade;

    public ProcessStateController(IProcessService processService, IAuthenticationFacade authenticationFacade) {
        this.processService = processService;
        this.authenticationFacade = authenticationFacade;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/process")
    List<ProcessRes> getAll(){
        User authUser = authenticationFacade.getAuthUser();
        return processService.getAll(authUser);
    }
}
