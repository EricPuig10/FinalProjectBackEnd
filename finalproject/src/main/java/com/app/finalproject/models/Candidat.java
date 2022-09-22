package com.app.finalproject.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.text.SimpleDateFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String superpower;
    private String direction;
    private String english;
    private String formation;
    private String reached;
    private String spirit;
    private String motivation;
    private Gender gender;
    private String nationality;
    private String laboralsituation;
    private String sololearnprogress;
    private String codeacademyprogress;
    private boolean assistedtoinformativesession;
    private String img;
    private String location;
    private Document document;
    private String numberdocument;



    @ManyToOne
    @JoinColumn(name ="processstate_id")
    private ProcessState processState;



    @ManyToOne
    @JoinColumn(name = "bootcamp_id")
    @OnDelete( action = OnDeleteAction.CASCADE )
    private Bootcamp bootcamp;

}
