package com.hem101.blog.services.impl;

import com.hem101.blog.entities.Category;
import com.hem101.blog.exceptions.ResourceNotFoundException;
import com.hem101.blog.payloads.CategoryDto;
import com.hem101.blog.repositories.CategoryRepo;
import com.hem101.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category=this.modelMapper.map(categoryDto,Category.class);
        Category createdCategory=this.categoryRepo.save(category);
        CategoryDto createdCategoryDto=this.modelMapper.map(createdCategory,CategoryDto.class);
        return createdCategoryDto;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedCat=this.categoryRepo.save(cat);
        CategoryDto categoryDto1=this.modelMapper.map(updatedCat,CategoryDto.class);
        return categoryDto1;
    }

    @Override
    public void deleteCategory(Integer categoryId) {
Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
this.categoryRepo.delete(category);

    }

    @Override
    public List<CategoryDto> getAllCategory() {

      List<Category>categories=  this.categoryRepo.findAll();
      List<CategoryDto>categoryDtos=new ArrayList<>();
        for (Category category :
                categories) {
            categoryDtos.add(this.modelMapper.map(category,CategoryDto.class));
        }
        return categoryDtos;
    }

    @Override
    public CategoryDto getCategoryById(Integer cateGoryId) {
        Category category=this.categoryRepo.findById(cateGoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",cateGoryId));
        CategoryDto categoryDto=this.modelMapper.map(category,CategoryDto.class);
        return categoryDto;
    }


}
