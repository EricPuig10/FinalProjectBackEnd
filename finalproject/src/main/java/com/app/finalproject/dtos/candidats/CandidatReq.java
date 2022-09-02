package com.app.finalproject.dtos.candidats;

import com.app.finalproject.models.Bootcamp;
import com.app.finalproject.models.ProcessState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CandidatReq {
    private String name;
    private String lastname;
    private String secondlastname;
    private String email;
    private Long phone;
    private Long age;
    private String gender;
    private String nationality;
    private String laboralsituation;
    private Bootcamp bootcamp;
    private ProcessState processState;
}
