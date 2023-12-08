package com.example.buysell.models;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.springframework.data.jdbc.repository.query.Query;

import java.util.List;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private int price;
    @ElementCollection
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    private List<String> photoUrl;
    private String sex;
    @Enumerated(EnumType.STRING)
    private Category category;
}
