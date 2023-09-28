package com.test.palindromesearch.service;

import com.test.palindromesearch.dto.AngleEnum;
import com.test.palindromesearch.dto.PalindromeResultDto;
import com.test.palindromesearch.model.ColumnModel;
import com.test.palindromesearch.model.MatrizModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PalindromeService {

    private AngleEnum angleEnum;


    public List<String> switchPalindromes(MatrizModel matrizModel, AngleEnum angle) throws Exception {

        PalindromeResultDto palindromeResultDto;

        List<String> palindromeList = new ArrayList();

        //TODO: Criar dto com enum dos palindromos.
        switch (angle) {
            case ANGLE_HORIZONTAL:


            case ANGLE_VERTICAL:

            case ANGLE_DIAGONAL:

        }

       List<String> resultPalindromeList = isPalindrome(matrizModel, palindromeList);


        return resultPalindromeList;
    }



    public List<String> isPalindrome(MatrizModel matrizModel, List<String> palindromeList) throws Exception {

        List<String> palindrome = new ArrayList<>();

        for (ColumnModel columnModel : matrizModel.getColumns()) {
            List<String> line = columnModel.getLines();

            for (int indexA = 0; indexA < line.size(); indexA++) {
                if (indexA < line.size() - 1 ) {
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
        throw  new Exception("Not found any palindrome");

    }

    public  List<String>  findPalindromeInMatriz(MatrizModel matrizModel) throws Exception {
       List<String> palidromes =  switchPalindromes(matrizModel, AngleEnum.ANGLE_HORIZONTAL);

       return palidromes;


    }
}
