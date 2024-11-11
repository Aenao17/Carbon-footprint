package org.example.Domain;


import jakarta.persistence.*;
import jakarta.persistence.Entity;

@jakarta.persistence.Entity
@Table(name = "Questions")
public class Question implements org.example.Domain.Entity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Use IDENTITY or AUTO generation
    @Column(name = "id")
    private Integer id;

    @Column(name = "text")
    private String text;

    public Question(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    public Question(String text) {
        this.text = text;
    }

    public Question() {
        // Default constructor for JPA and JSON serialization/deserialization
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
