package com.hem101.blog.services;

import com.hem101.blog.payloads.UserDto;

import java.util.List;

public interface UserService {
UserDto createUser(UserDto userDto);
UserDto updateUser(UserDto userDto,Integer userId);
List<UserDto> getAllUsers();
void deleteUser(Integer userId);
UserDto getUserById(Integer userId);
}
