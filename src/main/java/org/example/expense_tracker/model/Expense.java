package org.example.expense_tracker.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String category;
    private Double amount;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    public Expense() {
    }

    public Expense(String title, String category, Double amount, LocalDate date, String description) {
        this.title = title;
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public void setId(Long id){
        this.id = id;
    }

    public  void setTitle(String title){
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public Long getId(){
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
