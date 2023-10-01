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
import java.util.Collections;
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

    public List<List<String>> convertAngleList(List<List<String>> palindromeList, List<List<String>> vertitalList, List<List<String>> diagonalList) {

        System.out.println("Diagonal List: " + diagonalList);
        System.out.println("Vertical List: " + vertitalList);
        System.out.println("Horizontal List: " + palindromeList);

        List<List<String>> angleList = new ArrayList<>();

        angleList.addAll(palindromeList);
        angleList.addAll(vertitalList);
        angleList.addAll(diagonalList);

        System.out.println("Palindrome List: " + angleList);


        return angleList;

    }


    public List<PalindromeDTO> switchPalindromes(MatrizDTO matrizDto, AngleEnum angle) throws Exception {

        //Horizontal list
        List<List<String>> palindromeList = horizontalFormat(matrizDto);

        //Vertical list
        List<List<String>> vertitalList = verticalFormat(palindromeList);

        //Diagonal
        List<List<String>> diagonalList = diagonalFormat(palindromeList);

        List<List<String>> matrizPalindrome = convertAngleList(palindromeList, vertitalList, diagonalList);

        findPalindrome(matrizPalindrome);


        PalindromeDTO palindromeDTO = new PalindromeDTO(null, "CASCA");
        PalindromeDTO palindromeDTO1 = new PalindromeDTO(null, "OVO");

        List<PalindromeDTO> palindromeDTOResponses = new ArrayList<>();
        palindromeDTOResponses.add(palindromeDTO);
        palindromeDTOResponses.add(palindromeDTO1);


        return palindromeDTOResponses;
    }

    public boolean wordIsPalindrome(String word) {
        int firtLetter = 0;

        int wordSize = word.length() - 1;

        while (firtLetter < wordSize) {
            if (word.charAt(firtLetter) != word.charAt(wordSize)) {
                return false;
            }
        }
        return true;
    }

    public boolean compareWords(String word1, String word2) {
        if (word1.equals(word2) || word1.equals(new StringBuilder(word2))) {
            return true;
        }
        return false;
    }

    public String buildWords(int i, List<String> inputList, boolean reverse, int numberOfLetter) {
        String word2 = "";
        String word1 = "";

        word1 = inputList.get(i);

        if (numberOfLetter == 1) {
            return word1;
        }

        if (inputList.size() > i + 1) {
            word2 = inputList.get(i + 1);
        }

        if (reverse) {
            return word2.concat(word1);
        }
        return word1.concat(word2);

    }

    public String palindromeWordController(List<String> inputList,int indexf, int i, String wordIndex, String wordReverse, String word) {
        String palindome = "";
        if (indexf == i + 1) {
             palindome = buildWord3(inputList, indexf, i);
             return  palindome;
        }
        palindome = isPalindrome(wordIndex, wordReverse, word);

        return palindome;
    }

    public String isPalindrome(String word1, String word2, String word3) {


        String combination1 = word1 + word2 + word3;
        String combination2 = word1 + new StringBuilder(word2).reverse().toString();

        List<String> combinationList = new ArrayList<>(Arrays.asList(combination1, combination2));

        for (int i = 0; i < combinationList.size(); i++) {
            String word = combinationList.get(i);
            String reverseWord = new StringBuilder(word).reverse().toString();
            if (word.equals(reverseWord)) {
                return combinationList.get(i);

            }
        }
        return "";
    }

    public String buildWord3(List<String> inputList, int indexf, int i) {
        String wordReverse = "";
        String wordIndex = "";
        String word = "";
        if (indexf == i + 1) {
            wordReverse = buildWords(indexf + 1, inputList, false, 1);
            wordIndex = buildWords(i, inputList, false, 2);

            if (indexf < inputList.size() || i < inputList.size()) {
                for (int nextWordIndex = indexf + 2; nextWordIndex < inputList.size() - 1; nextWordIndex++) {
                    word = buildWords(nextWordIndex, inputList, false, 2);
                    if (!compareWords(wordIndex, word)) {
                        break;
                    }
                    return wordReverse + word;
                }
            }
        }
        return isPalindrome(wordIndex, wordReverse, word);

    }

    //TODO: Limpeza no codigo, finalizar logica
    public List<String> findPalindrome(List<List<String>> matrizPalindrome) throws PalindromeException {

        String word = "";

        String wordFinal = "";

        List<String> palindromes = new ArrayList<>();

        int indexf = 1;

        for (int listIndex = 0; listIndex < matrizPalindrome.size() - 1; listIndex++) {
            List<String> inputList = matrizPalindrome.get(listIndex);

            actualList:
            for (int i = 0; i < inputList.size(); i++) {
                boolean cutWord = true;
                String wordIndex = buildWords(i, inputList, false, 2);

                for (indexf = 1; indexf < inputList.size() - 1; indexf++) {
                    String wordReverse = buildWords(indexf, inputList, true, 2);
                    if (compareWords(wordIndex, wordReverse)) {
//                        if (indexf == i + 1) {
//                            String palindome = buildWord3(inputList, indexf, i);
//                            palindromes.add(palindome);
//                            cutWord = false;
//                            break actualList;
//                        }
//                        if (indexf == i + 1) { //compara se a Letra presente no index F é a mesma coletada pelo i + 1
//                            wordReverse = buildWords(indexf + 1, inputList, false, 1);
//
//                            if (indexf < inputList.size() || i < inputList.size()) {
//                                for (int nextWordIndex = indexf + 2; nextWordIndex < inputList.size() - 1; nextWordIndex++) {
//                                    word = buildWords(nextWordIndex, inputList, false, 2);
//                                    if (!compareWords(wordIndex, word)) {
//                                        break;
//                                    }
//                                }
//                            }
//                        }
                        String palindome = palindromeWordController(inputList, indexf, i, wordIndex, wordReverse, word);
//                        String palindome = isPalindrome(wordIndex, wordReverse, word);
                        palindromes.add(palindome);
                        cutWord = false;
                        word = "";
                        break actualList;
                    }
                }
                if (cutWord) {
                    inputList.remove(inputList.get(i));
                    i = -1;
                }
            }
        }
        System.out.println(palindromes);
        return palindromes;
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

        List<List<String>> diagonals = new ArrayList<>();
        List<List<String>> diagonalsToRemove = new ArrayList<>();

        List<List<String>> reversePalindromeList = new ArrayList<>(palindromeList);
        Collections.reverse(reversePalindromeList);

        int n = palindromeList.size(); // Tamanho da matriz

        // Diagonal Principal
        List<String> diagonalMain = new ArrayList<>();
        List<String> diagonalReverse = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            diagonalMain.add(palindromeList.get(i).get(i));
            diagonalReverse.add(reversePalindromeList.get(i).get(i));
        }
        diagonals.add(diagonalMain);
        diagonals.add(diagonalReverse);


        // Diagonais Secundárias Acima da Principal
        for (int i = 0; i < n - 1; i++) {
            List<String> diagonalSecondary = new ArrayList<>();
            List<String> diagonalReverseSecundary = new ArrayList<>();

            for (int j = 0; j < n - i - 1; j++) {
                diagonalSecondary.add(palindromeList.get(j).get(j + i + 1));
                diagonalReverseSecundary.add(reversePalindromeList.get(j).get(j + i + 1));
            }
            diagonals.add(diagonalSecondary);
            diagonals.add(diagonalReverseSecundary);

        }

        // Diagonais Secundárias Abaixo da Principal
        for (int i = 0; i < n - 1; i++) {
            List<String> diagonalSecondary = new ArrayList<>();
            List<String> diagonalReverseSecundary = new ArrayList<>();

            for (int j = 0; j < n - i - 1; j++) {
                diagonalSecondary.add(palindromeList.get(j + i + 1).get(j));
                diagonalReverseSecundary.add(reversePalindromeList.get(j + i + 1).get(j));
            }
            diagonals.add(diagonalSecondary);
            diagonals.add(diagonalReverseSecundary);

        }


        for (List<String> diagonalWord : diagonals) {
            if (diagonalWord.size() < 3) {
                diagonalsToRemove.add(diagonalWord);
            }
        }

        diagonals.removeAll(diagonalsToRemove);
        System.out.println(diagonals);

        return diagonals;

    }


}
