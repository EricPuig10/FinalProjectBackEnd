package com.app.finalproject.dtos.bootcamp;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BootcampReqDto {
    private String bootcampName;
    private String type;
    private String duration;
    private String characteristics;
    private boolean isPresential;
}
