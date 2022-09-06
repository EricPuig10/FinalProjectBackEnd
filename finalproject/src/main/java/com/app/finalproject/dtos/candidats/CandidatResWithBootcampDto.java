package com.app.finalproject.dtos.candidats;

import com.app.finalproject.dtos.bootcamp.BootcampResDtoCandidat;
import com.app.finalproject.models.Bootcamp;
import com.app.finalproject.models.ProcessState;
import lombok.Data;

@Data
public class CandidatResWithBootcampDto {
    private Long id;
    private String name;
    private String lastname;
    private String secondlastname;
    private String email;
    private Long phone;
    private Long age;
    private String gender;
    private String nationality;
    private String laboralsituation;
    private BootcampResDtoCandidat bootcamp;
    //private ProcessState processState;
}
