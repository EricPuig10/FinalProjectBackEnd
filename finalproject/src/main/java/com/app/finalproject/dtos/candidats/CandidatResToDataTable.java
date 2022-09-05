package com.app.finalproject.dtos.candidats;

import com.app.finalproject.models.Bootcamp;
import com.app.finalproject.models.ProcessState;
import lombok.Data;

@Data
public class CandidatResToDataTable {
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
/*    private String sololearnprogress;
    private String codeacademyprogress;
    private boolean assistedtoinformativesession;*/
    private String bootcamp;
    private String processState;
}
