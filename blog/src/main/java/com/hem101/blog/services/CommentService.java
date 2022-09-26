package com.hem101.blog.services;

import com.hem101.blog.payloads.CommentDto;

public interface CommentService {
CommentDto createComment(CommentDto commentDto, Integer postId);
void deleteComment(Integer commentId);


}
