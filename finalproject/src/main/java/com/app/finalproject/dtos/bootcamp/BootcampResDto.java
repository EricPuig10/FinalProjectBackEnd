package com.app.finalproject.dtos.bootcamp;

import com.app.finalproject.models.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class BootcampResDto {
    private Long id;
    private String bootcampName;
    private Category category;
    private String duration;
    private String characteristics;
    private String former;
    private String coformer;
    private Date initialDate;
    private Date finalDate;
}
