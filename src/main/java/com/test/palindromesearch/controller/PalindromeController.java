package com.test.palindromesearch.controller;

import com.test.palindromesearch.model.MatrizModel;
import com.test.palindromesearch.service.PalindromeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class PalindromeController {

    private static final Logger log = LoggerFactory.getLogger(PalindromeController.class);
    @Autowired
    private PalindromeService palindromeService;

    @PostMapping("/insert/palindrome")
    public ResponseEntity<List<String>> insertPalindrome(@RequestBody MatrizModel matrizModel) throws Exception {

       List<String> palindromeResult =  palindromeService.findPalindromeInMatriz(matrizModel);

        return new ResponseEntity<>(palindromeResult, HttpStatus.CREATED);
    }
}
