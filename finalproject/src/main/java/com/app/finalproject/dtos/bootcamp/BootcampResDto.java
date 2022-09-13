package com.app.finalproject.dtos.bootcamp;

import com.app.finalproject.models.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+1")
    private Date initialDate;
    private Date finalDate;
}
