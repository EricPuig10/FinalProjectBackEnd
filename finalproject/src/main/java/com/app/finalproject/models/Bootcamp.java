package com.app.finalproject.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
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
    private String duration;
    private String characteristics;

    private String former;

    private String coformer;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date initialDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date finalDate;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "bootcamp")
    @JsonIgnore
    private List<Candidat> candidatsList = new ArrayList<>();




    public Bootcamp(Long id, String bootcampName, Category category, String duration, String characteristics) {
        this.id = id;
        this.bootcampName = bootcampName;
        this.category = category;
        this.duration = duration;
        this.characteristics = characteristics;
    }
    public Bootcamp(String name) {
        this.bootcampName = name;
    }


}
