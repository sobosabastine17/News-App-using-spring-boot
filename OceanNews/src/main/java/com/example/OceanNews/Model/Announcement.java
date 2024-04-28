package com.example.OceanNews.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "announcement")
public class Announcement extends Model{
    private String title;
    private String content;
    private String image;
    private LocalDate date=LocalDate.now();
    private String createdBy;
    private String recipient;
    private Long status=0L;

}