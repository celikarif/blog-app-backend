package com.netas.blog.controller;


import com.netas.blog.dto.UserDto;
import com.netas.blog.dto.core.ResponsePayload;
import com.netas.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/save")
    public ResponsePayload<UserDto> save(@RequestBody UserDto userDto) {
        return userService.save(userDto);
    }
    @GetMapping(value = "/getAllUsers")
    public ResponsePayload<List<UserDto>> getAllUsers() {
        return userService.getAllUsers();
    }

}
