package com.hem101.blog.services;

import com.hem101.blog.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
    //create
     CategoryDto createCategory(CategoryDto categoryDto);

    //update
     CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);

    //delete
     void deleteCategory(Integer categoryId);

    //get All
     List<CategoryDto> getAllCategory();
     //view Category By Id
    CategoryDto getCategoryById(Integer categoryId);

}
