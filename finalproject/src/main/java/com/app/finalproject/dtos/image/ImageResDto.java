package com.app.finalproject.dtos.image;

import lombok.Data;

@Data
public class ImageResDto {
    private String message;
    private String url;

    private Long id;

    public ImageResDto(String message, String url, Long id) {
        this.message = message;
        this.url = url;
        this.id = id;
    }
}
