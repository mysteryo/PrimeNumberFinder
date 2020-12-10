package com.tutorial;

import com.tutorial.calculations.PrimeNumberResolver;
import com.tutorial.xlsparse.ParseExcelFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String path = args[0];
        File file = new File(path);

        if (file.exists()) {
            System.out.format("File %s exists\n", file.getAbsolutePath());
            List<Integer> numberArr;
            System.out.println("Prime numbers are :");
            try {
                numberArr = ParseExcelFile.getParsedNumbers(path);
                for(int number : numberArr){
                    if(number > 0 && PrimeNumberResolver.isPrime(number)){
                        System.out.println(number);
                    }
                }
            }
            catch (IOException e){
                System.out.println(e.toString());
            }

        } else {
            System.out.format("File %s does not exists", file.getAbsolutePath());
        }

    }
}
