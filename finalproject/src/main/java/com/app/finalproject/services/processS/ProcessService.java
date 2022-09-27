package com.app.finalproject.services.processS;


import com.app.finalproject.auth.facade.IAuthenticationFacade;
import com.app.finalproject.dtos.process.ProcessRes;
import com.app.finalproject.exceptions.NotFoundException;
import com.app.finalproject.mappers.ProcessMapper;
import com.app.finalproject.models.ProcessState;
import com.app.finalproject.models.User;
import com.app.finalproject.repositories.IProcessStateRepository;
import com.app.finalproject.services.processS.IProcessService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProcessService implements IProcessService {

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

    @Override
    public ProcessRes findById(Long id, User auth) {
        Optional<ProcessState> foundProcess = processStateRepository.findById(id);
        if (foundProcess.isEmpty()) throw new NotFoundException("Proceso no encontrado", "P-404");
        ProcessRes processRes = new ProcessMapper().mapProcessToRes(foundProcess.get(), auth);
        return processRes;
    }
}
