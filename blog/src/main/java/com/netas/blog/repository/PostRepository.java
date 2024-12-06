package com.netas.blog.repository;

import com.netas.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Long>, JpaRepository<Post, Long> {
    List<Post> findAllByAuthorId(Long userId);
    @Query("SELECT p FROM Post p JOIN p.categories c WHERE c.id IN :categoryIds")
    List<Post> findAllByCategoryIds(@Param("categoryIds") List<Long> categoryIds);

}
