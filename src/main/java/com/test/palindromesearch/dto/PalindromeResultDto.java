package com.test.palindromesearch.dto;

import lombok.Data;

import java.util.ArrayList;
@Data
public class PalindromeResultDto {

    private String response;
    private ArrayList<String> palindromeList;

    public PalindromeResultDto(String response, ArrayList<String> palindromeList) {
        this.response = response;
        this.palindromeList = palindromeList;
    }
}
