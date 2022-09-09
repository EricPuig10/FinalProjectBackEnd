package com.app.finalproject.dtos.category;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryReq {
    @NotBlank
    @Size(min=2, max= 40)
    String name;
}
