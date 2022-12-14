package com.app.finalproject.controllers;


import com.app.finalproject.auth.facade.IAuthenticationFacade;
import com.app.finalproject.dtos.candidats.CandidatReq;
import com.app.finalproject.dtos.candidats.CandidatRes;
import com.app.finalproject.models.Candidat;
import com.app.finalproject.models.User;
import com.app.finalproject.services.candidatS.ICandidatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/candidatos")
    List<CandidatRes> getAll(){
        User auth = authenticationFacade.getAuthUser();
        return candidatService.getAll(auth);
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/procesos/{id}/candidatos")
    List<CandidatRes> getProcessCandidats(@PathVariable Long id){
        User authUser = authenticationFacade.getAuthUser();
        return candidatService.findCandidatesByProcessId(id, authUser);
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/candidatos/{id}")
    CandidatRes getCandidatById(@PathVariable Long id){
        User auth = authenticationFacade.getAuthUser();
        var moment = this.candidatService.findById(id, auth);
        return moment;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/candidatos")
    public Candidat createCandidat(@RequestBody CandidatReq candidatReq){
        var authUser = authenticationFacade.getAuthUser();
        return candidatService.create(candidatReq, authUser);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/candidatos/{id}")
    CandidatRes updateCandidat(@PathVariable Long id, @RequestBody CandidatReq candidatReq) {
        User authUser = authenticationFacade.getAuthUser();

        return candidatService.updateACandidat(candidatReq, id, authUser);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/candidatos/{id}")
    ResponseEntity<CandidatRes> deleteCandidat(@PathVariable Long id) throws IOException {
        User authUser = authenticationFacade.getAuthUser();
        var candidat =  candidatService.deleteCandidat(id, authUser);
        return new ResponseEntity<>(candidat, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/bootcamps/{id}/candidatos")
    List<CandidatRes> getBootcampCandidates(@PathVariable Long id) {
        User authUser = authenticationFacade.getAuthUser();
        return candidatService.findCandidatesByBootcampId(id, authUser);
    }
}
