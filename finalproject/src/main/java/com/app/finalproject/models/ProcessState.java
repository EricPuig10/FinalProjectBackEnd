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
@Table(name = "processstates")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProcessState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "processState")
    @JsonIgnore
    private List<Candidat> candidatsList = new ArrayList<>();

}
