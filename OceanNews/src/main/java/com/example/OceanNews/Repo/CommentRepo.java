package com.example.OceanNews.Repo;

import com.example.OceanNews.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findAllByStatus(Long status);
}
