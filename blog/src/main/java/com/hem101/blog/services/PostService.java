
package com.hem101.blog.services;
import com.hem101.blog.entities.Post;
import com.hem101.blog.payloads.PostDto;
import com.hem101.blog.payloads.PostResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface PostService{

    //create
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    //update
    PostDto updatePost(PostDto postDto,Integer postId);
    //delete
    void deletePost(Integer postId);
    //get all posts
    PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy);
    //get post by id
    PostDto getPostById(Integer postId);
    //get post by category
    List<PostDto>getPostsByCategory(Integer categoryId);
    //get post by user
    List<PostDto>getPostByUser(Integer userId);
    List<PostDto>searchByKeyword(String keyword);

}

