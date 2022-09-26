package com.hem101.blog.payloads;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.*;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {
    private Integer userId;
 @NotEmpty
 @Size(min=4,message ="Username must be min of 4 character")
    private String userName;
@Email(message = "Email address is not valid")

    private String userEmail;
   @NotEmpty
   @Size(min = 3,max = 10,message = "PAssword myst be min of 3 char and max of 4 char")
   //@Pattern()
    private String userPassword;
   @NotEmpty
    private String userAbout;

}
