package com.test.palindromesearch.mapper;

import com.test.palindromesearch.dto.PalindromeDTO;
import com.test.palindromesearch.model.Palindrome;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class PalindromeDTOMapperr implements Function<Palindrome, PalindromeDTO> {

    @Override
    public PalindromeDTO apply(Palindrome palindrome) {
        return new PalindromeDTO(palindrome.getId(), palindrome.getPalindrome());
    }

    public List<PalindromeDTO> mapToDTOList(List<Palindrome> palindromeList) {
        return palindromeList.stream()
                .map(this::apply)
                .collect(Collectors.toList());
    }
}
