package com.hem101.blog.controller;

import com.hem101.blog.entities.Category;
import com.hem101.blog.payloads.ApiResponse;
import com.hem101.blog.payloads.CategoryDto;
import com.hem101.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.print.DocFlavor;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //create
    @PostMapping("/")
    public ResponseEntity<CategoryDto>createCategory( @RequestBody CategoryDto categoryDto){
CategoryDto categoryDto1=this.categoryService.createCategory(categoryDto);
return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
    }

    //update
@PutMapping("/{catId}")
    public ResponseEntity<CategoryDto>updateCategory( @RequestBody CategoryDto categoryDto,@PathVariable Integer catId){
       CategoryDto categoryDto1= this.categoryService.updateCategory(categoryDto,catId);
    return new ResponseEntity<CategoryDto>(categoryDto1,HttpStatus.OK);
}
    //delete
@DeleteMapping("/{catId}")
    public ResponseEntity<Category>deleteCategory(@PathVariable Integer catId){
        this.categoryService.deleteCategory(catId);
    return new ResponseEntity(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
}
    //get By Id
@GetMapping("/{categoryId}")
    public ResponseEntity<?>getCategoryById(@PathVariable Integer categoryId){
        CategoryDto categoryDto=this.categoryService.getCategoryById(categoryId);
    return new ResponseEntity<>(categoryDto,HttpStatus.OK);
}
    //get All
    @GetMapping("/")
    public ResponseEntity<?>getAllCategory(){
       List<CategoryDto> categoryList=this.categoryService.getAllCategory();
       return new ResponseEntity<>(categoryList,HttpStatus.OK);
    }

}
