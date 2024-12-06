package com.netas.blog.service.Impl;

import com.netas.blog.dto.UserDto;
import com.netas.blog.dto.core.ResponsePayload;
import com.netas.blog.entity.User;
import com.netas.blog.enumaration.core.MessageEnum;
import com.netas.blog.enumaration.core.ResponseEnum;
import com.netas.blog.mapper.UserDtoMapper;
import com.netas.blog.repository.UserRepository;
import com.netas.blog.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDtoMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final String secretKey = "mySecretKey";

    @Override
    public ResponsePayload<UserDto> save(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return new ResponsePayload<>(ResponseEnum.OK, MessageEnum.SAVE_SUCCESS.getMessage(),
                mapper.convertToDto(userRepository.save(mapper.convertToEntity(userDto))));
    }

      @Override
    public ResponsePayload<List<UserDto>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = mapper.mapList(users);

        if (userDtos.isEmpty()) {
            return new ResponsePayload<>(ResponseEnum.OK, MessageEnum.EMPTY_LIST.getMessage(), userDtos);
        }

        return new ResponsePayload<>(ResponseEnum.OK, MessageEnum.RETRIEVE_SUCCESS.getMessage(), userDtos);
    }


}
