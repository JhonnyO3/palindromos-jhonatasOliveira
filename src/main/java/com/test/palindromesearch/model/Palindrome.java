package com.test.palindromesearch.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
@Entity
public class Palindrome {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String palindrome;

    public Palindrome(Long id, String palindrome) {
        this.id = id;
        this.palindrome = palindrome;
    }

    public Palindrome( String palindrome) {
        this.palindrome = palindrome;
    }

    public Palindrome() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPalindrome() {
        return palindrome;
    }

    public void setPalindrome(String palindrome) {
        this.palindrome = palindrome;
    }
}
