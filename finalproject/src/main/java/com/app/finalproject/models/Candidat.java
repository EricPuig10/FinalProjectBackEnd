package com.app.finalproject.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

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
    private String img;


    @ManyToOne
    @JoinColumn(name ="processstate_id")
    private ProcessState processState;



    @ManyToOne
    @JoinColumn(name = "bootcamp_id")
    @OnDelete( action = OnDeleteAction.CASCADE )
    private Bootcamp bootcamp;

}
