package com.app.finalproject.dtos.candidats;


import com.app.finalproject.models.Bootcamp;
import com.app.finalproject.models.ProcessState;
import lombok.Data;

import java.util.Date;

@Data
public class CandidatRes {

    private Long id;
    private String name;
    private String lastname;
    private String secondlastname;
    private String email;
    private Long phone;
    private Long age;
    private String degree;
    private String date;
    private String superpower;
    private String direction;
    private String english;
    private String formation;
    private String reached;
    private String spirit;
    private String motivation;
    private String gender;
    private String nationality;
    private String laboralsituation;
    private String sololearnprogress;
    private String codeacademyprogress;
    private boolean assistedtoinformativesession;
    private Bootcamp bootcamp;
    private ProcessState processState;
    private String img;


}
