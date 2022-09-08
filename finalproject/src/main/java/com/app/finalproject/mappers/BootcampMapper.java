package com.app.finalproject.mappers;

import com.app.finalproject.dtos.bootcamp.BootcampReqDto;
import com.app.finalproject.dtos.bootcamp.BootcampResDto;
import com.app.finalproject.models.Bootcamp;
import com.app.finalproject.models.User;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServer;

import java.util.ArrayList;
import java.util.List;

public class BootcampMapper {


    public BootcampResDto mapBootcampToBootcampResponseDto(Bootcamp bootcamp, User auth){
        var response = new BootcampResDto();
        response.setId(bootcamp.getId());
        response.setBootcampName(bootcamp.getBootcampName());
        response.setType(bootcamp.getType());
        response.setDuration(bootcamp.getDuration());
        response.setCharacteristics(bootcamp.getCharacteristics());
        response.setPresential(bootcamp.isPresential());
        return response;
    }



    public List<BootcampResDto> mapMultipleBootcampToListResponse(List<Bootcamp> bootcampList, User auth){
        List<BootcampResDto> responseList = new ArrayList<>();
        bootcampList.forEach(Bootcamp -> responseList.add(this.mapBootcampToBootcampResponseDto(Bootcamp, auth)));
        return responseList;
    }


    public Bootcamp mapRequestToBootcampToEdit(BootcampReqDto bootcampReqDto,Bootcamp bootcamp){
        bootcamp.setBootcampName(bootcampReqDto.getBootcampName());
        bootcamp.setType(bootcampReqDto.getType());
        bootcamp.setDuration(bootcampReqDto.getDuration());
        bootcamp.setCharacteristics(bootcampReqDto.getCharacteristics());
        bootcamp.setPresential(bootcampReqDto.isPresential());
        return bootcamp;
    }


    public Bootcamp mapRequestToBootcampToCreate(BootcampReqDto bootcampReqDto){
        var bootcamp = new Bootcamp();
        bootcamp.setBootcampName(bootcampReqDto.getBootcampName());
        bootcamp.setType(bootcamp.getType());
        bootcamp.setDuration(bootcampReqDto.getDuration());
        bootcamp.setCharacteristics(bootcampReqDto.getCharacteristics());
        bootcamp.setPresential(bootcampReqDto.isPresential());
        return bootcamp;
    }
}
