package com.hem101.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {
    private Integer categoryId;
    @NotEmpty
    private String categoryTitle;
    @NotEmpty
    @Size(min = 4,message = "you must enter size greater than 4")
    private String categoryDescription;
}
