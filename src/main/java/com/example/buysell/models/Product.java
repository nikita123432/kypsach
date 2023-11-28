package com.example.buysell.models;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jdbc.repository.query.Query;

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
    private String photoUrl;
}
