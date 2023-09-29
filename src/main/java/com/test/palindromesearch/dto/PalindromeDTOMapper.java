package com.test.palindromesearch.dto;

import com.test.palindromesearch.model.Palindrome;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PalindromeDTOMapper implements Function<Palindrome, PalindromeDTOResponse> {

    @Override
    public PalindromeDTOResponse apply(Palindrome palindrome) {
        return new PalindromeDTOResponse(
                palindrome.getPalindrome().chars().mapToObj(ch -> String.valueOf((char) ch)).toString()
        );
    }
}
