package com.hem101.blog.repositories;

import com.hem101.blog.entities.Category;
import com.hem101.blog.entities.Post;
import com.hem101.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post , Integer> {
List<Post>findByUser(User user);
List<Post>findAllByCategory(Category category);
List<Post>findByPostTitleContaining(String postTitle);

}
