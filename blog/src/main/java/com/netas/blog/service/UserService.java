package com.netas.blog.service;

import com.netas.blog.dto.UserDto;
import com.netas.blog.dto.core.ResponsePayload;

import java.util.List;

public interface UserService {
    ResponsePayload<UserDto> save(UserDto userDto);
    ResponsePayload<List<UserDto>> getAllUsers();


}
