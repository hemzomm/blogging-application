package com.hem101.blog.controller;

import com.hem101.blog.payloads.ApiResponse;
import com.hem101.blog.payloads.UserDto;
import com.hem101.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    //POST --> Create User
    @PostMapping("/")
public ResponseEntity<UserDto> createUser( @Valid @RequestBody UserDto userDto){
UserDto createUserDto=this.userService.createUser(userDto);
return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
}
    //PUT  --> Update User
    @PutMapping("/{userId}")
public  ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer userId){
        UserDto updatedUser =this.userService.updateUser(userDto,userId);
        return ResponseEntity.ok(updatedUser);
}
    //GET  --> Get User
    @GetMapping("/")
    public ResponseEntity<List<UserDto>>getAllUSer(){
      return ResponseEntity.ok(this.userService.getAllUsers());
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto>getUserById(@PathVariable("userId")Integer userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }
    //DELETE --> Delete User
    @DeleteMapping("/{userId}")
    public ResponseEntity<?>deleteUser(@PathVariable Integer userId){
        this.userService.deleteUser(userId);
        return new ResponseEntity(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
       // return ResponseEntity.ok(Map.of("Message","User Deleted success"));
    }
}
