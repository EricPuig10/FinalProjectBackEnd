package com.app.finalproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    private String bootcampName;
    private String category;
    private String duration;
    private String characteristics;
    private boolean isPresential;


    @OneToMany(mappedBy = "bootcamp")
    @JsonIgnore
    private List<Candidat> candidatsList = new ArrayList<>();

    public Bootcamp(Long id, String bootcampName, String category, String duration, String characteristics, boolean isPresential) {
        this.id = id;
        this.bootcampName = bootcampName;
        this.category = category;
        this.duration = duration;
        this.characteristics = characteristics;
        this.isPresential = isPresential;
    }

}
