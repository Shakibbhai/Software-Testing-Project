package com.example.junitrestapiapplication;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "book_record")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;


    @NonNull
    private String name;

    @NonNull
    private String summary;

    @NonNull
    private String rating;

}
