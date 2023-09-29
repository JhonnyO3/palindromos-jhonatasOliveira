package com.test.palindromesearch.dto;

import com.test.palindromesearch.config.PalindromeException;
import org.springframework.http.HttpStatus;

import java.util.List;

public record MatrizDTO(
        List<ColumnDTO> columns
) {

    public MatrizDTO {
        int columnsize = columns.size();

        if ((columnsize != columns.get(1).lines().size())) {
            throw new PalindromeException(HttpStatus.BAD_REQUEST, "The columns and rows must be the same size, forming a square matrix.");
        }


    }

}

