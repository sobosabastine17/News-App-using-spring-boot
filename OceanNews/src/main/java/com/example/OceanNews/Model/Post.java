package com.example.OceanNews.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;

@Setter
@Getter
@Entity
@ToString
@NoArgsConstructor
public class Post extends Model{
    @Column
    private String title;
    @ManyToOne
    private Category category;
    @ManyToOne
    private SubCategory subCategory;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Column
    private Long click=0L;
    @Column
    private Long shared=0L;
    @Column
    private String creator;
    @Column
    private String url;
    @Column
    private Long status=0L;

}
