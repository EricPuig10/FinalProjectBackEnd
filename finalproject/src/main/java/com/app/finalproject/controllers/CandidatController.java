package com.app.finalproject.controllers;


import com.app.finalproject.auth.facade.IAuthenticationFacade;
import com.app.finalproject.dtos.candidats.CandidatRes;
import com.app.finalproject.models.Candidat;
import com.app.finalproject.models.User;
import com.app.finalproject.services.ICandidatService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/candidats/{id}/bootcamp")
    List<CandidatRes> getCandidatsByBootcamp(@PathVariable Long id){
        User authUser = authenticationFacade.getAuthUser();
        return candidatService.findByBootcampCandidats(id, authUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/candidats/{id}/process")
    List<CandidatRes> getCandidatsByProcess(@PathVariable Long id){
        User authUser = authenticationFacade.getAuthUser();
        return candidatService.findByProcessCandidats(id, authUser);
    }

    // Option to get all candidates from one bootamp
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/bootcamps/{id}/candidats")
    List<CandidatRes> getBootcampCandidates(@PathVariable Long id) {
        User authUser = authenticationFacade.getAuthUser();
        return candidatService.findCandidatesByBootcampId(id, authUser);
    }

}
