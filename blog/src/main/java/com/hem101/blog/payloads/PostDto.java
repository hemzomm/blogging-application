package com.hem101.blog.payloads;

import com.hem101.blog.entities.Category;
import com.hem101.blog.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
public class PostDto {

    private Integer postId;
    @NotEmpty
    @Size(min=4,message ="Username must be min of 4 character")
    private String postTitle;
    @NotEmpty
    @Size(min=4,message ="Username must be min of 4 character")
    private String postContent;
    private String imageName;
    private Date addedDate;

    private CategoryDto category;

    private UserDto user;
    private Set<CommentDto> comments=new HashSet<>();

}
