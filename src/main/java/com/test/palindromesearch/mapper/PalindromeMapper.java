package com.test.palindromesearch.mapper;

import com.test.palindromesearch.dto.PalindromeDTO;
import com.test.palindromesearch.model.Palindrome;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
@Service
public class PalindromeMapper implements Function<PalindromeDTO, Palindrome > {
    @Override
    public Palindrome apply(PalindromeDTO palindromeDTO) {
        return new Palindrome(palindromeDTO.palindrome());
    }

    public List<Palindrome> mapToList(List<PalindromeDTO> palindromeList) {
        return palindromeList.stream()
                .map(this::apply)
                .collect(Collectors.toList());
    }
}
