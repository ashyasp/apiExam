package com.tech44.API_Exam.domain;

import com.tech44.API_Exam.domain.enums.Gender;
import jakarta.persistence.*;

@Entity
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String username;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private Gender gender;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
// Getters and Setters
    }



