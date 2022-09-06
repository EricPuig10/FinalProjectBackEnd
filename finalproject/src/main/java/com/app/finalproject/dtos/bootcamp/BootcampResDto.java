package com.app.finalproject.dtos.bootcamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BootcampResDto {
    private Long id;
    private String bootcampName;
    private String type;
    private String duration;
    private String characteristics;
    private boolean isPresential;
}
