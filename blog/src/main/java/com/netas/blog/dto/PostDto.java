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
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private String imageUrl;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private UserDto userDto;
    private List<CommentDto> commentDtoList;
    private List<CategoryDto> categoryDtoList;
}
