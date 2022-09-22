package com.app.finalproject.mappers;

import com.app.finalproject.dtos.bootcamp.BootcampReqDto;
import com.app.finalproject.dtos.bootcamp.BootcampResDto;
import com.app.finalproject.models.Bootcamp;
import com.app.finalproject.models.Category;
import com.app.finalproject.models.User;

import java.util.ArrayList;
import java.util.List;

public class BootcampMapper {


    public BootcampResDto mapBootcampToBootcampResponseDto(Bootcamp bootcamp, User auth){
        var response = new BootcampResDto();
        response.setId(bootcamp.getId());
        response.setBootcampName(bootcamp.getBootcampName());
        response.setCategory(bootcamp.getCategory());
        response.setDuration(bootcamp.getDuration());
        response.setCharacteristics(bootcamp.getCharacteristics());
        response.setFormer(bootcamp.getFormer());
        response.setCoformer(bootcamp.getCoformer());
        response.setInitialDate(bootcamp.getInitialDate());
        response.setFinalDate(bootcamp.getFinalDate());
        return response;
    }



    public List<BootcampResDto> mapMultipleBootcampToListResponse(List<Bootcamp> bootcampList, User auth){
        List<BootcampResDto> responseList = new ArrayList<>();
        bootcampList.forEach(Bootcamp -> responseList.add(this.mapBootcampToBootcampResponseDto(Bootcamp, auth)));
        return responseList;
    }


    public Bootcamp mapRequestToBootcampToEdit(BootcampReqDto bootcampReqDto,Bootcamp bootcamp, Category category){
        bootcamp.setBootcampName(bootcampReqDto.getBootcampName());
        bootcamp.setCategory(category);
        bootcamp.setDuration(bootcampReqDto.getDuration());
        bootcamp.setCharacteristics(bootcampReqDto.getCharacteristics());
        bootcamp.setFormer(bootcampReqDto.getFormer());
        bootcamp.setCoformer(bootcampReqDto.getCoformer());
        bootcamp.setInitialDate(bootcampReqDto.getInitialDate());
        bootcamp.setFinalDate(bootcampReqDto.getFinalDate());
        return bootcamp;
    }


    public Bootcamp mapRequestToBootcampToCreate(BootcampReqDto bootcampReqDto, Category category){
        var bootcamp = new Bootcamp();
        bootcamp.setBootcampName(bootcampReqDto.getBootcampName());
        bootcamp.setCategory(category);
        bootcamp.setDuration(bootcampReqDto.getDuration());
        bootcamp.setCharacteristics(bootcampReqDto.getCharacteristics());
        bootcamp.setFormer(bootcampReqDto.getFormer());
        bootcamp.setCoformer(bootcampReqDto.getCoformer());
        bootcamp.setInitialDate(bootcampReqDto.getInitialDate());
        bootcamp.setFinalDate(bootcampReqDto.getFinalDate());
        return bootcamp;
    }
}
