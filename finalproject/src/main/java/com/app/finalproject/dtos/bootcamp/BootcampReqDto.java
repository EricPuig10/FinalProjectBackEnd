package com.app.finalproject.dtos.bootcamp;


import com.app.finalproject.models.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+1")
    private Date initialDate;
    private Date finalDate;
}
