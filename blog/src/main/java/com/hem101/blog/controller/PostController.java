package com.hem101.blog.controller;

import com.hem101.blog.entities.Post;
import com.hem101.blog.payloads.ApiResponse;
import com.hem101.blog.payloads.PostDto;
import com.hem101.blog.payloads.PostResponse;
import com.hem101.blog.payloads.UserDto;
import com.hem101.blog.repositories.PostRepo;
import com.hem101.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.PDLOverrideSupported;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;

    private ModelMapper modelMapper;

    @PostMapping("/user/{userId}/category/{categoryId}/post/")
    public ResponseEntity<PostDto>createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId
    ){
        PostDto createdPost= this.postService.createPost(postDto,userId,categoryId);

        return new ResponseEntity<PostDto>(createdPost,HttpStatus.CREATED);
    }
    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDto>updatePost(@Valid @RequestBody PostDto postDto,@PathVariable Integer postId){
        PostDto updatePost=this.postService.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(updatePost,HttpStatus.CREATED);
    }
    @DeleteMapping("/post/{postId}")
    public ResponseEntity<ApiResponse>deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post Deleted Successfully",true),HttpStatus.OK);
    }
@GetMapping("/posts")
public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNumber",defaultValue = "1",required = false)Integer pageNumber,@RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize,@RequestParam(value = "",defaultValue = "postId",required = false)String sortBy){
        PostResponse postDtos=this.postService.getAllPosts(pageNumber,pageSize, sortBy);
       return new ResponseEntity<>(postDtos,HttpStatus.OK);
}
@GetMapping("/post/{postId}")
public ResponseEntity<PostDto>getById(@PathVariable Integer postId){
       PostDto postDto= this.postService.getPostById(postId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
}
@GetMapping("post/category/{categoryId}")
public ResponseEntity<List<PostDto>>getPostaByCategory(@PathVariable Integer categoryId){
        List<PostDto>postDtos= this.postService.getPostsByCategory(categoryId);
       return new ResponseEntity<>(postDtos,HttpStatus.OK);
}
    @GetMapping("post/user/{userId}")
    public ResponseEntity<List<PostDto>>getPostsByUserId(@PathVariable Integer userId){
        List<PostDto>postDtos= this.postService.getPostByUser(userId);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }
    //search
    @GetMapping("posts/search/{keyword}")
    public ResponseEntity<List<PostDto>>search(@PathVariable String keyword){
        List<PostDto> postDtos=this.postService.searchByKeyword(keyword);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }
}
