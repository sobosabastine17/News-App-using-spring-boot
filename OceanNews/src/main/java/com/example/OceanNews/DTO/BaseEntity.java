//package com.example.OceanNews.DTO;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Setter
//@Getter
//@ToString
//public class BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    // @JsonIgnore
//    private Long id;
//    private Long status=0L;
//    private String title;
//    private String creator;
//    private String content;
//    @Version
//    private int version;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
//
//    public BaseEntity() {
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }
//}
