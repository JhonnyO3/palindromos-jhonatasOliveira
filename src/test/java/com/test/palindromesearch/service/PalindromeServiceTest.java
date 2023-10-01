package com.test.palindromesearch.service;

import com.test.palindromesearch.ApplicationConfigTest;
import com.test.palindromesearch.config.PalindromeException;
import com.test.palindromesearch.dto.AngleEnum;
import com.test.palindromesearch.dto.ColumnDTO;
import com.test.palindromesearch.dto.MatrizDTO;
import com.test.palindromesearch.dto.PalindromeDTO;
import com.test.palindromesearch.mapper.PalindromeDTOMapperr;
import com.test.palindromesearch.mapper.PalindromeDTOResponseMapper;
import com.test.palindromesearch.mapper.PalindromeMapper;
import com.test.palindromesearch.model.Palindrome;
import com.test.palindromesearch.repository.PalindromeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("PalindromeServiceTest")
class PalindromeServiceTest extends ApplicationConfigTest {

    @MockBean
    private PalindromeDTOMapperr palindromeDTOMapper;
    @MockBean
    private PalindromeMapper palindromeMapper;
    @MockBean
    private PalindromeDTOResponseMapper palindromeDTOResponseMapper;

    @MockBean
    private PalindromeRepository palindromeRepository;
    @Autowired
    PalindromeService palindromeService;


    List<Palindrome> palindromeList() {
        String palindrome2 = "YJJY";
        String palindrome1 ="OSSO";
        String palindrome3 = "ARARA";
        String palindrome4 = "LPPL";


        List<String> palindromeExtractedList = new ArrayList<>(Arrays.asList(palindrome1, palindrome2, palindrome3, palindrome4));
        List<Palindrome> palindromeObjectList = new ArrayList<>();

        for (String palindrome : palindromeExtractedList) {
            Palindrome palindromeObject = new Palindrome(palindrome);
            palindromeObjectList.add(palindromeObject);

        }
        return palindromeObjectList;
    }

    ArrayList<PalindromeDTO> generetePalindromeDTO() {
        ArrayList<PalindromeDTO> palindromeDTOS = new ArrayList<>(Arrays.asList(
                new PalindromeDTO(1L, "OSSO"),
                new PalindromeDTO(2L, "YJJY"),
                new PalindromeDTO(3L, "ARARA"),
                new PalindromeDTO(4L, "LPPL")
        ));
        return palindromeDTOS;
    }



    List<List<String>> generatePalindromeList() {
        return List.of(
                List.of("A", "Y", "J", "J", "Y"),
                List.of("O", "R", "S", "K", "L"),
                List.of("S", "Z", "A", "P", "E"),
                List.of("S", "X", "P", "R", "R"),
                List.of("O", "L", "M", "Z", "A")
        );
    }

    List<List<String>> generateFinalPalindromeList() {
        return List.of(
                List.of("A", "O", "S", "S", "O"),
                List.of("Y", "R", "Z", "X", "L"),
                List.of("J", "S", "A", "P", "M"),
                List.of("J", "K", "P", "R", "Z"),
                List.of("Y", "L", "E", "R", "A")
        );
    }

    List<List<String>> generateMatrizList() {
        List<List<String>> matrizList = new ArrayList<>();

        matrizList.add(Arrays.asList("A", "O", "S", "S", "O"));
        matrizList.add(Arrays.asList("Y", "R", "Z", "X", "L"));
        matrizList.add(Arrays.asList("J", "S", "A", "P", "M"));
        matrizList.add(Arrays.asList("J", "K", "P", "R", "Z"));
        matrizList.add(Arrays.asList("Y", "L", "E", "R", "A"));
        matrizList.add(Arrays.asList("A", "Y", "J", "J", "Y"));
        matrizList.add(Arrays.asList("O", "R", "S", "K", "L"));
        matrizList.add(Arrays.asList("S", "Z", "A", "P", "E"));
        matrizList.add(Arrays.asList("S", "X", "P", "R", "R"));
        matrizList.add(Arrays.asList("O", "L", "M", "Z", "A"));
        matrizList.add(Arrays.asList("A", "R", "A", "R", "A"));
        matrizList.add(Arrays.asList("Y", "K", "A", "X", "O"));
        matrizList.add(Arrays.asList("O", "Z", "P", "Z"));
        matrizList.add(Arrays.asList("L", "P", "P", "L"));
        matrizList.add(Arrays.asList("S", "X", "M"));
        matrizList.add(Arrays.asList("E", "R", "M"));
        matrizList.add(Arrays.asList("Y", "S", "P", "R"));
        matrizList.add(Arrays.asList("J", "S", "Z", "S"));
        matrizList.add(Arrays.asList("J", "K", "E"));
        matrizList.add(Arrays.asList("J", "R", "S"));

        return matrizList;
    }

    MatrizDTO generateMatrizDTO() {
        String[][] lines = {
                {"A", "O", "S", "S", "O"},
                {"Y", "R", "Z", "X", "L"},
                {"J", "S", "A", "P", "M"},
                {"J", "K", "P", "R", "Z"},
                {"Y", "L", "E", "R", "A"}
        };


        List<ColumnDTO> columns = new ArrayList<>();

        for (int i = 0; i < lines[0].length; i++) {
            List<String> lineList = new ArrayList<>();
            for (int j = 0; j < lines.length; j++) {
                lineList.add(lines[j][i]);
            }
            columns.add(new ColumnDTO(lineList));
        }

        MatrizDTO matrizDto = new MatrizDTO(columns);

        return matrizDto;
    }

    @Test
    @DisplayName("Have to return all palindromes existing in H2 Database")
    void testFindAllPalindromes() {
        List<Palindrome> list = palindromeList();
        when(palindromeRepository.findAll()).thenReturn(list);

        List<Palindrome> palindromeList = palindromeRepository.findAll();
        assertEquals(list.size(), palindromeList.size());

        assertTrue(list.containsAll(palindromeList));
        assertTrue(palindromeList.containsAll(list));

    }

    @Test
    @DisplayName("Have to returl a exception because not found any palindrome in H2 Database")
    void testNotFoundAnyPalindrome() {
        List<Palindrome> emptyList = new ArrayList<>();

        when(palindromeRepository.findAll()).thenReturn(emptyList);

        PalindromeException exception = assertThrows(PalindromeException.class, () -> palindromeService.getAllPalindromes());

        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertEquals("Not found any palindrome", exception.getMessage());
    }

    @Test
    @DisplayName("Verify if the matriz is quadratic ")
    void testMatrizSize() throws Exception {
        ColumnDTO column1 = new ColumnDTO(Arrays.asList("A", "O"));
        ColumnDTO column2 = new ColumnDTO(Arrays.asList("A", "O"));
        List<ColumnDTO> columnDTOS = new ArrayList<>(Arrays.asList(column1, column2));

        assertDoesNotThrow(() -> new MatrizDTO(columnDTOS));

    }

    @Test
    @DisplayName("checks if an exception is returned if the matrix is not quadratic")
    void testInvalidMatriz() throws Exception {
        ColumnDTO column1 = new ColumnDTO(Arrays.asList("A", "O", "O"));
        ColumnDTO column2 = new ColumnDTO(Arrays.asList("A", "O", "B"));
        List<ColumnDTO> columnDTOS = new ArrayList<>(Arrays.asList(column1, column2));

        PalindromeException exception = assertThrows(PalindromeException.class, () -> new MatrizDTO(columnDTOS));

        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertEquals("The columns and rows must be the same size, forming a square matrix.", exception.getMessage());
    }

    @Test
    @DisplayName("Check angle Vertical in the matrix are being converted correctly")
    void testVerticalFormat() {
        List<List<String>> palindromeList = new ArrayList();
        MatrizDTO matrizDTO = generateMatrizDTO();

        for (ColumnDTO verticalColumn : matrizDTO.columns()) {

            List<String> line = verticalColumn.lines();

            palindromeList.add(line);
        }

        List<List<String>> expected = List.of(
                List.of("A", "Y", "J", "J", "Y"),
                List.of("O", "R", "S", "K", "L"),
                List.of("S", "Z", "A", "P", "E"),
                List.of("S", "X", "P", "R", "R"),
                List.of("O", "L", "M", "Z", "A")
        );

        assertEquals(expected, palindromeList);
    }

    @Test
    @DisplayName("Check angle Vertical in the matrix are being converted correctly")
    void testHorizontalFormat() {
        List<List<String>> palindromeList = generatePalindromeList();
        List<List<String>> columns = new ArrayList<>();

        for (int i = 0; i < palindromeList.size(); i++) {

            List<String> column = new ArrayList<>();

            for (int b = 0; b < palindromeList.get(i).size(); b++) {

                column.add(palindromeList.get(b).get(i));
            }

            columns.add(column);

        }

        List<List<String>> expected = List.of(
                List.of("A", "O", "S", "S", "O"),
                List.of("Y", "R", "Z", "X", "L"),
                List.of("J", "S", "A", "P", "M"),
                List.of("J", "K", "P", "R", "Z"),
                List.of("Y", "L", "E", "R", "A")
        );

        assertEquals(expected, columns);
    }

    @Test
    @DisplayName("Check angle Vertical in the matrix are being converted correctly")
    void testdiagonalFormat() {

        List<List<String>> expected = List.of(
                List.of("A", "R", "A", "R", "A"),
                List.of("Y", "K", "A", "X", "O"),
                List.of("O", "Z", "P", "Z"),
                List.of("L", "P", "P", "L"),
                List.of("S", "X", "M"),
                List.of("E", "R", "M"),
                List.of("Y", "S", "P", "R"),
                List.of("J", "S", "Z", "S"),
                List.of("J", "K", "E"),
                List.of("J", "R", "S")
        );

        List<List<String>> palindromeList = generateFinalPalindromeList();

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

        assertEquals(expected, diagonals);

    }

    @Test
    void testFindPalindromeInMatriz() throws Exception {

        List<PalindromeDTO> palindromeDTOS = generetePalindromeDTO();

        Mockito.when(palindromeMapper.mapToList(ArgumentMatchers.eq(palindromeDTOS))).thenReturn((palindromeList()));

        Mockito.when(palindromeRepository.saveAll(ArgumentMatchers.eq(palindromeList()))).thenReturn(Collections.emptyList());

        Mockito.when(palindromeRepository.findAll()).thenReturn(palindromeList());

        Mockito.when(palindromeDTOMapper.mapToDTOList(ArgumentMatchers.any())).thenReturn(generetePalindromeDTO());

        List<PalindromeDTO> palindromeDTOList = palindromeService.findPalindromeInMatriz(generateMatrizDTO());

        assertEquals(palindromeDTOS, palindromeDTOList);

        System.out.println(palindromeDTOList);

    }






}
