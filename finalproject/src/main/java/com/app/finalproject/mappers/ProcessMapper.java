package com.app.finalproject.mappers;

import com.app.finalproject.dtos.bootcamp.BootcampResDto;
import com.app.finalproject.dtos.process.ProcessRes;
import com.app.finalproject.models.Bootcamp;
import com.app.finalproject.models.ProcessState;
import com.app.finalproject.models.User;

public class ProcessMapper {

    public ProcessRes mapProcessToRes(ProcessState process, User auth){
        var response = new ProcessRes();
        response.setId(process.getId());
        response.setName(process.getName());
        return response;
    }
}
