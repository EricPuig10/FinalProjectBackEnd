package com.app.finalproject.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Size(min=2, max=40)
    private String name;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Bootcamp> bootcampsList = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }
}
