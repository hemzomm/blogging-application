package com.hem101.blog.controller;

import com.hem101.blog.entities.Comment;
import com.hem101.blog.payloads.ApiResponse;
import com.hem101.blog.payloads.CommentDto;
import com.hem101.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;

@RestController
@RequestMapping("/api")
public class CommentController {
@Autowired
    private CommentService commentService;


    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto>createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId ){
        CommentDto createComment=this.commentService.createComment(commentDto,postId);
        return new ResponseEntity<>(createComment, HttpStatus.OK);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<ApiResponse>deleteComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment Deleted ",true),HttpStatus.OK);
    }
}
