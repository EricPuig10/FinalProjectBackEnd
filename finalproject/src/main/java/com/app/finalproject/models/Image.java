package com.app.finalproject.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String imgUrl;
    private String imgId;

    public Image() {

    }

    public Image(String name, String imgUrl, String imgId) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.imgId = imgId;
    }

}
