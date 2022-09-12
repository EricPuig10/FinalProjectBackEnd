package com.app.finalproject.services;


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

    public ProcessService(IProcessStateRepository processStateRepository) {
        this.processStateRepository = processStateRepository;
    }

    @Override
    public List<ProcessRes> getAll(User auth) {
        List <ProcessState> process = processStateRepository.findAll();
        List <ProcessRes> processList = new ArrayList<>();
        process.forEach(Process -> {
            ProcessRes processRes = new ProcessMapper().mapProcessToRes(Process, auth);
            processList.add(processRes);
        });
        return processList;
    }
}
