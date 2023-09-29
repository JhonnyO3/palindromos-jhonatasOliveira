package com.test.palindromesearch.mapper;

import com.test.palindromesearch.dto.PalindromeDTO;
import com.test.palindromesearch.dto.PalindromeDTOResponse;
import com.test.palindromesearch.model.Palindrome;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PalindromeDTOResponseMapper implements Function<PalindromeDTO, PalindromeDTOResponse> {


    @Override
    public PalindromeDTOResponse apply(PalindromeDTO palindromeDTO) {
        return new PalindromeDTOResponse(palindromeDTO.palindrome());
    }

    public List<PalindromeDTOResponse> mapToList(List<PalindromeDTO> palindromeList) {
        return palindromeList.stream()
                .map(this::apply)
                .collect(Collectors.toList());
    }
}
