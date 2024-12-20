package com.netas.blog.repository;

import com.netas.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>, JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}



