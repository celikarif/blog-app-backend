package com.netas.blog.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private PostDto postDto;
    private UserDto userDto;

}
