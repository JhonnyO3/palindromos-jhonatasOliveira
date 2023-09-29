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
import java.util.Arrays;
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

            if (palindrome.isEmpty()) {
                throw new PalindromeException(HttpStatus.NOT_FOUND, "Not found any palindrome");
            }

            List<PalindromeDTO> palindromeDTOList = palindromeDTOMapper.mapToDTOList(palindrome);

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
            throw new PalindromeException(HttpStatus.BAD_REQUEST, "Error inserting palindromes!");
        }
    }


    public List<PalindromeDTO> switchPalindromes(MatrizDTO matrizDto, AngleEnum angle) throws Exception {

        //Horizontal list
        List<List<String>> palindromeList = horizontalFormat(matrizDto);

        //Vertical list
        List<List<String>> vertitalList =  verticalFormat(palindromeList);

        //Diagonal
        List<List<String>> diagonalList = diagonalFormat(palindromeList);


        System.out.println("Diagonal List: " + diagonalList);
        System.out.println("Vertical List: " + vertitalList );
        System.out.println("Horizontal List: " + palindromeList);


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
        throw new PalindromeException(HttpStatus.NOT_FOUND, "Not found any palindrome in the matriz");
    }


    public List<List<String>> horizontalFormat(MatrizDTO matrizDTO) {
        List<List<String>> palindromeList = new ArrayList();

        //Horizontal list
        for (ColumnDTO verticalColumn : matrizDTO.columns()) {

            List<String> line = verticalColumn.lines();

            palindromeList.add(line);

        }
        return palindromeList;

    }

    public List<List<String>> verticalFormat(List<List<String>> palindromeList) {
        List<List<String>> columns = new ArrayList<>();

        for (int i = 0; i < palindromeList.size(); i++) {

            List<String> column = new ArrayList<>();

            for (int b = 0; b < palindromeList.get(i).size(); b++) {

                column.add(palindromeList.get(b).get(i));
            }

            columns.add(column);

        }
        return columns;

    }

    public List<List<String>> diagonalFormat(List<List<String>> palindromeList) {
        List<String> diagonal1 = new ArrayList<>();
        List<String> diagonal2 = new ArrayList<>();
        List<List<String>> diagonals = new ArrayList<>();

        for (int b = 0; b < palindromeList.get(1).size(); b++) {
            diagonal1.add(palindromeList.get(b).get(b));
        }
        for (int b = palindromeList.get(1).size() - 1; b >= 0; b--) {
            diagonal2.add(palindromeList.get(b).get(b));

        }

        diagonals.add(diagonal1);
        diagonals.add(diagonal2);

        return diagonals;

    }


}
