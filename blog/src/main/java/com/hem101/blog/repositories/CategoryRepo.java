package com.hem101.blog.repositories;

import com.hem101.blog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository <Category,Integer> {

}
