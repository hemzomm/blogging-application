package com.hem101.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@NoArgsConstructor
@Getter
public class CommentDto {
    private Integer commentId;
    private String commentContent;

}
