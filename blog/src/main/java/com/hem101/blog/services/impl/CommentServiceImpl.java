package com.hem101.blog.services.impl;

import com.hem101.blog.entities.Comment;
import com.hem101.blog.entities.Post;
import com.hem101.blog.exceptions.ResourceNotFoundException;
import com.hem101.blog.payloads.CommentDto;
import com.hem101.blog.repositories.CommentRepo;
import com.hem101.blog.repositories.PostRepo;
import com.hem101.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
private CommentRepo commentRepo;
    @Autowired
private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post Not Found","postID=",postId));
       Comment comment=this.modelMapper.map(commentDto, Comment.class);
       comment.setPost(post);
       Comment savedComment=this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
    Comment comment=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment ","Comment Id",commentId));
    this.commentRepo.delete(comment);
    }
}
