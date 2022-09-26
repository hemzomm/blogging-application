package com.hem101.blog.services.impl;
import com.hem101.blog.entities.Category;
import com.hem101.blog.entities.Post;
import com.hem101.blog.entities.User;
import com.hem101.blog.exceptions.ResourceNotFoundException;
import com.hem101.blog.payloads.PostDto;
import com.hem101.blog.payloads.PostResponse;
import com.hem101.blog.repositories.CategoryRepo;
import com.hem101.blog.repositories.PostRepo;
import com.hem101.blog.repositories.UserRepo;
import com.hem101.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@RestController
@Service
public class PostServiceImpl implements PostService {
   @Autowired
    private PostRepo postRepo;
   @Autowired
   private ModelMapper modelMapper;
   @Autowired
   private UserRepo userRepo;
   @Autowired
   private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto,Integer userId, Integer categoryId) {
        Post post=this.modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());

        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User ","Id",userId));
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
      post.setUser(user);
      post.setCategory(category);
        Post createdPost=this.postRepo.save(post);
        return this.modelMapper.map(createdPost,PostDto.class);

    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {

Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post"," Id",postId));

post.setPostContent(postDto.getPostContent());
     post.setPostTitle(postDto.getPostTitle());
PostDto savedPost= this.modelMapper.map(post,PostDto.class);

     Post post1= this.postRepo.save(this.modelMapper.map(savedPost,Post.class));
        return this.modelMapper.map(post1,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post"," Id",postId));
       this.postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy) {
        Pageable pageable= PageRequest.of(pageNumber,pageSize, Sort.by(sortBy).descending());

       Page<Post>pagePost=this.postRepo.findAll(pageable);

       List<Post>posts=pagePost.getContent();
        List<PostDto>postDtos=new ArrayList<>();

        for (Post p:posts
             ) {
            postDtos.add(this.modelMapper.map(p,PostDto.class));
        }
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElement(pagePost.getNumberOfElements());
        postResponse.setTotalPage(pagePost.getTotalPages());
        postResponse.setIsLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post"," Id",postId));
        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {

        List<PostDto>postDtos=new ArrayList<>();
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
        List<Post>posts=this.postRepo.findAllByCategory(category);
        for (Post p:posts
        ) {
            postDtos.add(this.modelMapper.map(p,PostDto.class));
        }

        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        List<PostDto>postDtos=new ArrayList<>();
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("Category","Id",userId));
        List<Post>posts=this.postRepo.findByUser(user);
        for (Post p:posts
        ) {
            postDtos.add(this.modelMapper.map(p,PostDto.class));
        }

        return postDtos;
    }
    //search
    public List<PostDto>searchByKeyword(String keyword){
        List<Post> posts=this.postRepo.findByPostTitleContaining(keyword);
        List<PostDto>postDtos=new ArrayList<>();
        for (Post p:posts
        ) {
            postDtos.add(this.modelMapper.map(p,PostDto.class));
        }
        return postDtos;

    }

}
