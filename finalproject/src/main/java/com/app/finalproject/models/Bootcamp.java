package com.app.finalproject.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bootcamps")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bootcamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String bootcampName;
    private String duration;
    private String characteristics;
    private String former;
    private String coformer;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+1")
    private Date initialDate;
    private Date finalDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "bootcamp")
    @JsonIgnore
    private List<Candidat> candidatsList = new ArrayList<>();




    public Bootcamp(Long id, String bootcampName, Category category, String duration, String characteristics, String former, String coformer, Date initialDate, Date finalDate) {
        this.id = id;
        this.bootcampName = bootcampName;
        this.category = category;
        this.duration = duration;
        this.characteristics = characteristics;
        this.former = former;
        this.coformer = coformer;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

}
