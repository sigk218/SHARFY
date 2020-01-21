package com.ssafysns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafysns.model.dto.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
