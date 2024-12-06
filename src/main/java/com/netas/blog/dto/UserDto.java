package com.netas.blog.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private LocalDate birthday;
    private String role;
    private List<PostDto> postDtoList;
}
