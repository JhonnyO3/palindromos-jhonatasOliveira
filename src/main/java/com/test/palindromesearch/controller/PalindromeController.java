package com.test.palindromesearch.controller;

import com.test.palindromesearch.config.PalindromeException;
import com.test.palindromesearch.dto.MatrizDTO;
import com.test.palindromesearch.dto.PalindromeDTO;
import com.test.palindromesearch.dto.PalindromeDTOResponse;
import com.test.palindromesearch.service.PalindromeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class PalindromeController<T> {

    @ExceptionHandler(PalindromeException.class)
    public ResponseEntity<String> handlePalindromeException(PalindromeException ex) {
        return new ResponseEntity<>(ex.getMessage(), ex.getHttpStatus());
    }

    private static final Logger log = LoggerFactory.getLogger(PalindromeController.class);
    @Autowired
    private PalindromeService palindromeService;

    @Operation(summary = "API get palindromes", description = "consult registered palindromes")
    @ApiResponse(responseCode = "200", description = "Palindromes found")
    @ApiResponse(responseCode = "400", description = "Validation Error")
    @ApiResponse(responseCode = "404", description = "Palindrome not found")
    @GetMapping("get/palindromes")
    public ResponseEntity<List<PalindromeDTO>> getAllPalindromes() throws Exception {
        try {

            List<PalindromeDTO> palindromeResult = palindromeService.getAllPalindromes();

            return new ResponseEntity<>(palindromeResult, HttpStatus.OK);

        } catch (PalindromeException palindromeException) {
            throw new PalindromeException(palindromeException.getHttpStatus(), palindromeException.getMessage());

        }

    }

    @Operation(summary = "API Palindrome in Matriz Search", description = "performs palindrome search in an array")
    @ApiResponse(responseCode = "200", description = "Palindromes found", content = @Content(schema = @Schema(implementation = MatrizDTO.class)))
    @ApiResponse(responseCode = "400", description = "Validation Error")
    @ApiResponse(responseCode = "404", description = "Palindrome not found")
    @PostMapping("/insert/palindrome")
    public ResponseEntity<List<PalindromeDTO>> insertPalindrome(@RequestBody MatrizDTO matrizDto) throws Exception {
        try {

            List<PalindromeDTO> palindromeResult = palindromeService.findPalindromeInMatriz(matrizDto);

            return new ResponseEntity<>(palindromeResult, HttpStatus.OK);

        } catch (PalindromeException palindromeException) {
            throw new PalindromeException(palindromeException.getHttpStatus(), palindromeException.getMessage());

        }

    }
}
