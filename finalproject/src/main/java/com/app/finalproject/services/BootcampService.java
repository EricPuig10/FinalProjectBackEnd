package com.app.finalproject.services;

import com.app.finalproject.auth.facade.IAuthenticationFacade;
import com.app.finalproject.dtos.bootcamp.BootcampReqDto;
import com.app.finalproject.dtos.bootcamp.BootcampResDto;
import com.app.finalproject.exceptions.BadRequestException;
import com.app.finalproject.exceptions.NotFoundException;
import com.app.finalproject.mappers.BootcampMapper;
import com.app.finalproject.models.Bootcamp;
import com.app.finalproject.models.User;
import com.app.finalproject.repositories.IBootcampRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public BootcampResDto findById(Long id, User auth) {
        Optional<Bootcamp> foundBootcamp = bootcampRepository.findById(id);
        if (foundBootcamp.isEmpty()) throw new NotFoundException("Bootcamp Not Found", "B-404");
        BootcampResDto bootcampResDto = new BootcampMapper().mapBootcampToBootcampResponseDto(foundBootcamp.get(), auth);
        return bootcampResDto;
    }

    @Override
    public Bootcamp createBootcamp(BootcampReqDto bootcampReqDto, User authUser) {
        Bootcamp bootcamp = new Bootcamp();
        bootcamp.setBootcampName(bootcampReqDto.getBootcampName());
        bootcamp.setType(bootcamp.getType());
        bootcamp.setDuration(bootcampReqDto.getDuration());
        bootcamp.setCharacteristics(bootcampReqDto.getCharacteristics());
        bootcamp.setPresential(bootcampReqDto.isPresential());

        return bootcampRepository.save(bootcamp);
    }

    @Override
    public BootcampResDto updateBootcamp(BootcampReqDto bootcampReqDto, Long id, User authUser) {
        var bootcamp = bootcampRepository.findById(id);
        if(bootcamp.isEmpty()) throw new NotFoundException("Bootcamp Not Found", "B-404");
        Bootcamp updatedBootcamp = new BootcampMapper().mapRequestToBootcampToEdit(bootcampReqDto, bootcamp.get());
        bootcampRepository.save(updatedBootcamp);
        BootcampResDto bootcampResDto = new BootcampMapper().mapBootcampToBootcampResponseDto(updatedBootcamp,authUser);
        return bootcampResDto;
    }

    @Override
    public BootcampResDto deleteBootcamp(Long id, User auth){
        Bootcamp bootcamp = this.bootcampRepository.findById(id).get();
        BootcampResDto BootcampRes = new BootcampMapper().mapBootcampToBootcampResponseDto(bootcamp, auth);
        this.bootcampRepository.delete(bootcamp);
        return BootcampRes;
    }




}
