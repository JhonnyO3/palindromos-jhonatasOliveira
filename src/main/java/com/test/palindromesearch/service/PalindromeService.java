package com.test.palindromesearch.service;

import com.test.palindromesearch.config.PalindromeException;
import com.test.palindromesearch.dto.*;
import com.test.palindromesearch.mapper.PalindromeDTOMapperr;
import com.test.palindromesearch.mapper.PalindromeDTOResponseMapper;
import com.test.palindromesearch.mapper.PalindromeMapper;
import com.test.palindromesearch.model.Palindrome;
import com.test.palindromesearch.repository.PalindromeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PalindromeService {

    private AngleEnum angleEnum;
    @Autowired
    private PalindromeDTOMapperr palindromeDTOMapper;
    @Autowired
    private PalindromeMapper palindromeMapper;
    @Autowired
    private PalindromeDTOResponseMapper palindromeDTOResponseMapper;

    @Autowired
    private PalindromeRepository palindromeRepository;



    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public PalindromeService() {
    }

    public List<PalindromeDTO> getAllPalindromes() {

        try {

            List<Palindrome> palindrome = palindromeRepository.findAll();

            List<PalindromeDTO> palindromeDTOList = palindromeDTOMapper.mapToDTOList(palindrome);

            if (palindromeDTOList.isEmpty()) {
                throw new PalindromeException(HttpStatus.NOT_FOUND, "Not found any palindrome");
            }

            return palindromeDTOList;

        } catch (PalindromeException e) {
            throw new PalindromeException(HttpStatus.BAD_REQUEST, e.getMessage());

        }
    }

    public List<PalindromeDTOResponse> findPalindromeInMatriz(MatrizDTO matrizDto) throws Exception {

        try {
            List<PalindromeDTO> palindromeDTOList = switchPalindromes(matrizDto, AngleEnum.ANGLE_HORIZONTAL);

            List<Palindrome> palindromeList = palindromeMapper.mapToList(palindromeDTOList);

            List<PalindromeDTOResponse> palindromeDTOResponses = palindromeDTOResponseMapper.mapToList(palindromeDTOList);

            palindromeRepository.saveAll(palindromeList);

            return palindromeDTOResponses;

        } catch (Exception e) {
            throw new PalindromeException(HttpStatus.BAD_REQUEST,"Error inserting palindromes!");
        }
    }




    public List<PalindromeDTO> switchPalindromes(MatrizDTO matrizDto, AngleEnum angle) throws Exception {

        List<String> palindromeList = new ArrayList();

        //TODO: Criar dto com enum dos palindromos.
        switch (angle) {
            case ANGLE_HORIZONTAL:


            case ANGLE_VERTICAL:

            case ANGLE_DIAGONAL:

        }

        PalindromeDTO palindromeDTO = new PalindromeDTO(null, "CASCA");
        PalindromeDTO palindromeDTO1 = new PalindromeDTO(null, "OVO");

        List<PalindromeDTO> palindromeDTOResponses = new ArrayList<>();
        palindromeDTOResponses.add(palindromeDTO);
        palindromeDTOResponses.add(palindromeDTO1);











//               isPalindrome(matrizDto, palindromeList);


        return palindromeDTOResponses;
    }


    public List<String> isPalindrome(MatrizDTO matrizDto, List<String> palindromeList) throws Exception {

        List<String> palindrome = new ArrayList<>();

        for (ColumnDTO columnDto : matrizDto.columns()) {
            List<String> line = columnDto.lines();

            for (int indexA = 0; indexA < line.size(); indexA++) {
                if (indexA < line.size() - 1) {
                    String wordA = line.get(indexA);
                    String wordB = line.get(indexA + 1);
                    String par = wordA.concat(wordB);

                    for (int indexB = indexA + 1; indexB < line.size(); indexB++) {
                        if (indexB < line.size() - 1) {
                            String wordC = line.get(indexB);
                            String wordD = line.get(indexB + 1);
                            String reversePar = wordD.concat(wordC);
                            String parReverse = new StringBuilder(reversePar).reverse().toString();

                            if (par.equals(reversePar) && indexB == indexA + 2) {
                                System.out.println("Foi encontrado um par: " + par.concat(parReverse));
                                palindrome.add(par.concat(parReverse));
                                return palindrome;
                            }
                        }

                    }
                }
            }

        }
        throw new PalindromeException(HttpStatus.NOT_FOUND,"Not found any palindrome in the matriz");
    }


}
