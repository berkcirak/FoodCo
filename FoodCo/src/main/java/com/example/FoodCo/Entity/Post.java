package com.example.FoodCo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_post")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @Lob
    private String description;
    private String image;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;




}
