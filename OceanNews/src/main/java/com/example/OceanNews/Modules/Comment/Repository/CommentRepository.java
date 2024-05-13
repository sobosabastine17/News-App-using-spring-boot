package com.example.OceanNews.Modules.Comment.Repository;

import com.example.OceanNews.Modules.Comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select count(c) from Comment c where c.status = ?1")
    long countByStatus(Long status);
    @Query("select c from Comment c where c.status = ?1")
    List<Comment> findAllByStatus(Long status);

    List<Comment> findAllByPostId(Long id);

    @Query("select count(c) from Comment c where c.post = ?1")
    Long countByPostId(Long id);
}
