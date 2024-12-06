package com.example.buysell.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Enumerated(EnumType.STRING) // Эта аннотация указывает, что поле role должно храниться как строка
    @Column(name = "role")
    private Role role;
    @ManyToMany
    public List<Product> basket;
    public String getRole() {
        return role.name(); // Вернуть строковое представление enum
    }
}