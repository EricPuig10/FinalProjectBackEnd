package com.app.finalproject.mappers;

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




}
