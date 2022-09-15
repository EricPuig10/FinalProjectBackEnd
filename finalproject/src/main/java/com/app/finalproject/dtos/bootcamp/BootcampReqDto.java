package com.app.finalproject.dtos.bootcamp;


import com.app.finalproject.models.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class BootcampReqDto {
    private String bootcampName;
    private String category;
    private String duration;
    private String characteristics;
    private String former;
    private String coformer;
    private Date initialDate;
    private Date finalDate;
}
