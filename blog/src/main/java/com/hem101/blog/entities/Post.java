package com.hem101.blog.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    @Column(name = "title",nullable = false,length = 100)
    private String postTitle;
    @Column(length = 10000)
    private String postContent;
    private String imageName;
    private Date addedDate;

    @ManyToOne
   @JoinColumn(name = "categoryId")
    private Category category;


    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    Set<Comment>comments=new HashSet<>();


}
