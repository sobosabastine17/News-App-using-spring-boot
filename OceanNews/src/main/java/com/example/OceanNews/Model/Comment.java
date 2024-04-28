package com.example.OceanNews.Model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDate;

@Entity
@Setter
@Getter
public class Comment extends Model{
    @Column
    private String title,content,user;
    private LocalDate date=LocalDate.now();
    private Long status=0L;
    private Long postId;

    
}
