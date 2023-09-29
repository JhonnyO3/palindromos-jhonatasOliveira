package com.test.palindromesearch.repository;

import com.test.palindromesearch.config.PalindromeException;
import com.test.palindromesearch.dto.ColumnDTO;
import com.test.palindromesearch.dto.MatrizDTO;
import com.test.palindromesearch.dto.PalindromeDTO;
import com.test.palindromesearch.dto.PalindromeDTOResponse;
import com.test.palindromesearch.mapper.PalindromeDTOMapperr;
import com.test.palindromesearch.mapper.PalindromeDTOResponseMapper;
import com.test.palindromesearch.mapper.PalindromeMapper;
import com.test.palindromesearch.model.Palindrome;
import com.test.palindromesearch.service.PalindromeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PalindromeRepositoryTest {
    @InjectMocks
    PalindromeService palindromeService;
    @Mock
    PalindromeRepository dao;

    @InjectMocks
    private PalindromeMapper palindromeMapper;

    @Mock
    private PalindromeDTOResponseMapper palindromeDTOResponseMapper;


    List<Palindrome> palindromeList() {
        Palindrome palindrome1 = new Palindrome(1L, "OSSO");
        Palindrome palindrome2 = new Palindrome(2L, "OVO");
        List<Palindrome> list = new ArrayList<>(Arrays.asList(palindrome1, palindrome2));
        return list;
    }


    @Test
    void testFindAllPalindromes() {
        List<Palindrome> list = palindromeList();
        when(dao.findAll()).thenReturn(list);

        List<Palindrome> palindromeList = dao.findAll();
        assertEquals(list.size(), palindromeList.size());

        assertTrue(list.containsAll(palindromeList));
        assertTrue(palindromeList.containsAll(list));

    }

    @Test
    void testNotFoundAnyPalindrome() {
        List<Palindrome> emptyList = new ArrayList<>();

        when(dao.findAll()).thenReturn(emptyList);

        PalindromeException exception = assertThrows(PalindromeException.class, () -> palindromeService.getAllPalindromes());

        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertEquals("Not found any palindrome", exception.getMessage());
    }

    @Test
    public void testFindPalindromeInMatriz() throws Exception {
        ColumnDTO column1 = new ColumnDTO(Arrays.asList("A", "O"));
        ColumnDTO column2 = new ColumnDTO(Arrays.asList("A", "O"));
        List<ColumnDTO> columnDTOS = new ArrayList<>(Arrays.asList(column1, column2));

        assertDoesNotThrow(() -> new MatrizDTO(columnDTOS));

    }

    @Test
    public void testInvalidMatriz() throws Exception {
        ColumnDTO column1 = new ColumnDTO(Arrays.asList("A", "O", "O"));
        ColumnDTO column2 = new ColumnDTO(Arrays.asList("A", "O", "B" ));
        List<ColumnDTO> columnDTOS = new ArrayList<>(Arrays.asList(column1, column2));

        PalindromeException exception = assertThrows(PalindromeException.class, () -> new MatrizDTO(columnDTOS));

        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertEquals("The columns and rows must be the same size, forming a square matrix.", exception.getMessage());
    }








}