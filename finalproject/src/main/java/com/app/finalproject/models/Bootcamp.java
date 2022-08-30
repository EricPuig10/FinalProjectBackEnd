package com.app.finalproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bootcamps")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Bootcamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String bootcampname;

    private boolean isPresencial;


    @OneToMany(mappedBy = "bootcamp")
    @JsonIgnore
    private List<Candidat> candidatsList = new ArrayList<>();

    public Bootcamp(Long id, String bootcampname, boolean isPresencial) {
        this.id = id;
        this.bootcampname = bootcampname;
        this.isPresencial = isPresencial;
    }
}
