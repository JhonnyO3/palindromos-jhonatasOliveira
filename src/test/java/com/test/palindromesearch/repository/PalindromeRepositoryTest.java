package com.test.palindromesearch.repository;

import com.test.palindromesearch.mapper.PalindromeDTOResponseMapper;
import com.test.palindromesearch.mapper.PalindromeMapper;
import com.test.palindromesearch.service.PalindromeService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



@ExtendWith(MockitoExtension.class)
class PalindromeRepositoryTest {
    @InjectMocks
    PalindromeService palindromeServiceTest;
    @Mock
    PalindromeRepository dao;

    @InjectMocks
    private PalindromeMapper palindromeMapper;

    @Mock
    private PalindromeDTOResponseMapper palindromeDTOResponseMapper;












}