package com.app.finalproject.dtos.candidats;

import com.app.finalproject.models.Document;
import com.app.finalproject.models.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class CandidatReq {
    private String name;
    private String lastname;
    private String secondlastname;
    private String email;
    private Long phone;
    private Long age;
    private String degree;
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
    private String bootcamp;
    private String processState;
    private String img;
    private String location;
    private String document;
    private String numberdocument;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String sololearnprogress;
    private String codeacademyprogress;
    private boolean assistedtoinformativesession;
}
