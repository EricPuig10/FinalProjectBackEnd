package com.app.finalproject.controllers;


import com.app.finalproject.auth.facade.IAuthenticationFacade;
import com.app.finalproject.dtos.candidats.CandidatRes;
import com.app.finalproject.models.Candidat;
import com.app.finalproject.models.User;
import com.app.finalproject.services.ICandidatService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class CandidatController {

    private ICandidatService candidatService;
    private IAuthenticationFacade authenticationFacade;

    public CandidatController(ICandidatService candidatService, IAuthenticationFacade authenticationFacade) {
        this.candidatService = candidatService;
        this.authenticationFacade = authenticationFacade;
    }



    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/candidats")
    List<CandidatRes> getAll(){
        User auth = authenticationFacade.getAuthUser();
        return candidatService.getAll(auth);
    }
}
