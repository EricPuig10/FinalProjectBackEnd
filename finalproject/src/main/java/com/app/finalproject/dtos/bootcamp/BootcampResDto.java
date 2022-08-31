package com.app.finalproject.dtos.bootcamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BootcampResDto {
    private Long id;
    private String bootcampName;
    private boolean isPresential;
}
