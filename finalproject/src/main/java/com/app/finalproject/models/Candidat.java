package com.app.finalproject.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "candidats")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Candidat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
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
    private String sololearnprogress;
    private String codeacademyprogress;
    private boolean assistedtoinformativesession;


    @ManyToOne
    @JoinColumn(name ="processstate_id")
    private ProcessState processState;



    @ManyToOne
    @JoinColumn(name = "bootcamp_id")
    private Bootcamp bootcamp;

}
