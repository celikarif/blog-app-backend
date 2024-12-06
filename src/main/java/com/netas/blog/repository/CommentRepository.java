package com.netas.blog.repository;

import com.netas.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long>, JpaRepository<Comment, Long> {

    List<Comment> findByPostId(Long postId);
}
