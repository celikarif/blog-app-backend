package com.netas.blog.mapper;

import com.netas.blog.dto.UserDto;
import com.netas.blog.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDtoMapper {
    public UserDto convertToDto(User user) {
        if (user == null) {
            return null;
        }
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .password(user.getPassword())
                .email(user.getEmail())
                .birthday(user.getBirthday())
                .role(user.getRole())
                .build();
    }

    public User convertToEntity(UserDto userDto) {
        User user = new User();

        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setBirthday(userDto.getBirthday());
        user.setRole(userDto.getRole());
        return user;
    }

    public List<UserDto> mapList(List<User> list) {
        return list.stream().map(this::convertToDto).toList();
    }

    public List<User> convertListToEntity(List<UserDto> list) {
        return list.stream().map(this::convertToEntity).toList();
    }
    public UserDto mapWithObjects(User user) {
        if (user == null) {
            return null;
        }
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .password(user.getPassword())
                .email(user.getEmail())
                .birthday(user.getBirthday())
                .role(user.getRole())
                .postDtoList(new PostDtoMapper().mapListWithObjects(user.getPosts()))
                .build();
    }

    public List<UserDto> mapListWithObjects(List<User> list) {
        return list.stream().map(this::mapWithObjects).toList();
    }

}
