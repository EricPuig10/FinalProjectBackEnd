package com.app.finalproject.dtos.bootcamp;

import com.app.finalproject.models.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BootcampResDto {
    private Long id;
    private String bootcampName;
    private Category category;
    private String duration;
    private String characteristics;
    private boolean isPresential;
}
