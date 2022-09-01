package com.app.finalproject.services;

import com.app.finalproject.auth.facade.IAuthenticationFacade;
import com.app.finalproject.dtos.bootcamp.BootcampResDto;
import com.app.finalproject.mappers.BootcampMapper;
import com.app.finalproject.repositories.IBootcampRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BootcampService implements IBootcampService {


    private IBootcampRepository bootcampRepository;

    private IAuthenticationFacade authenticationFacade;

    public BootcampService(IBootcampRepository bootcampRepository, IAuthenticationFacade authenticationFacade) {
        this.bootcampRepository = bootcampRepository;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public List<BootcampResDto> getAll() {
        var authUser = authenticationFacade.getAuthUser();
        return new BootcampMapper().mapMultipleBootcampToListResponse(bootcampRepository.findAll(), authUser);
    }
}
