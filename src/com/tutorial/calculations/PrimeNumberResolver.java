package com.tutorial.calculations;

public class PrimeNumberResolver {


    public static boolean isPrime(int number){
        boolean isPrime = true;
        for (int i = 2; i < number/2; i++) {
            if(number % i == 0){
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }
}
