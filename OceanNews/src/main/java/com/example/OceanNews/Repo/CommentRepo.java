package com.example.OceanNews.Repo;

import com.example.OceanNews.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {
}
