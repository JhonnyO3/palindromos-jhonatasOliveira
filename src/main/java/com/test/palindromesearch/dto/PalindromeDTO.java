package com.test.palindromesearch.dto;

import com.test.palindromesearch.config.PalindromeException;
import org.springframework.http.HttpStatus;

public record PalindromeDTO(
        Long id,
        String palindrome
) {

    public PalindromeDTO {

        if (palindrome == null || palindrome.isEmpty()) {
            throw new PalindromeException(HttpStatus.BAD_REQUEST,"O campo 'palindrome' não pode ser nulo ou vazio.");
        }
    }
}
