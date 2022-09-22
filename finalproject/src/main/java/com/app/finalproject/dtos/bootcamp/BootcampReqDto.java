package com.app.finalproject.dtos.bootcamp;


import com.app.finalproject.models.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date initialDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date finalDate;
}
