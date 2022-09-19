package com.app.finalproject.services;


import com.app.finalproject.auth.facade.IAuthenticationFacade;
import com.app.finalproject.dtos.candidats.CandidatRes;
import com.app.finalproject.dtos.process.ProcessRes;
import com.app.finalproject.mappers.CandidatMapper;
import com.app.finalproject.mappers.ProcessMapper;
import com.app.finalproject.models.Candidat;
import com.app.finalproject.models.ProcessState;
import com.app.finalproject.models.User;
import com.app.finalproject.repositories.IProcessStateRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;




@Service
public class ProcessService implements IProcessService{

    private IProcessStateRepository processStateRepository;
    private IAuthenticationFacade authenticationFacade;

    public ProcessService(IProcessStateRepository processStateRepository, IAuthenticationFacade authenticationFacade) {
        this.processStateRepository = processStateRepository;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public List<ProcessRes> getAll(User authUser) {
        List <ProcessState> process = processStateRepository.findAll();
        List <ProcessRes> processList = new ArrayList<>();
        process.forEach(Process -> {
            ProcessRes processRes = new ProcessMapper().mapProcessToRes(Process, authUser);
            processList.add(processRes);
        });
        return processList;
    }
}
