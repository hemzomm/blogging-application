package com.hem101.blog.services.impl;

import com.hem101.blog.entities.User;
import com.hem101.blog.exceptions.ResourceNotFoundException;
import com.hem101.blog.payloads.UserDto;
import com.hem101.blog.repositories.UserRepo;
import com.hem101.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServicesImpl implements UserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
       User user= this.userRepo.save(userDtoToUser(userDto));
        return this.userToUserDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
       // User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));

        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," Id",userId));
        user.setUserId(userDto.getUserId());
        user.setUserAbout(userDto.getUserAbout());
        user.setUserEmail(userDto.getUserEmail());
        user.setUserName(userDto.getUserName());
        user.setUserPassword(userDto.getUserPassword());
       User updatedUser= this.userRepo.save(user);
       UserDto updatedUserDto= this.userToUserDto(updatedUser);
        return updatedUserDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
       List<User> users=this.userRepo.findAll();
      List<UserDto> userDtos=users.stream().map(user->this.userToUserDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        this.userRepo.delete(user);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","Id",userId));
        return this.userToUserDto(user);
    }

    //In future we use model mapper for the following mapping
    private User userDtoToUser(UserDto userDto) {
        User user=this.modelMapper.map(userDto,User.class);

//        user.setUserId(userDto.getUserId());
//        user.setUserAbout(userDto.getUserAbout());
//        user.setUserEmail(userDto.getUserEmail());
//        user.setUserName(userDto.getUserName());
//        user.setUserPassword(userDto.getUserPassword());

        return user;
    }
    private UserDto userToUserDto(User user){
        UserDto userDto=this.modelMapper.map(user,UserDto.class);
        return  userDto;
    }

}
