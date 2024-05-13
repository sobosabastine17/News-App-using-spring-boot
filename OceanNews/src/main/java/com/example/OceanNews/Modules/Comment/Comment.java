package com.example.OceanNews.Modules.Comment;

import com.example.OceanNews.Model.Model;
import com.example.OceanNews.Modules.Post.Post;
import com.example.OceanNews.Modules.User.User;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
public class Comment extends Model {
    private String title;
    private String content;
    @ManyToOne
    private User user;
    @ManyToOne
    private Post post;
    private LocalDate date=LocalDate.now();
    private Long status=0L;

    
}
