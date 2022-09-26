package com.hem101.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.PrivateKey;
import java.util.List;
import java.util.PrimitiveIterator;
@Getter
@Setter
@NoArgsConstructor
public class PostResponse {
    private List<PostDto> content;
    private Integer pageNumber;
    private Integer pageSize;
   private Integer totalElement;
   private Integer totalPage;
   private Boolean isLastPage;

}
