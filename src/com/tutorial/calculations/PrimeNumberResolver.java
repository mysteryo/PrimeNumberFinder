package com.tutorial.calculations;

public class PrimeNumberResolver {


	
    public static boolean isPrime(double number){
    	//1 is not prime number
    	if(number == 1) return false;
    	if(number == 2 || number == 3) return true;
        boolean isPrime = true;
        //if we square root the number and remove decimal places we should get the highest nonrepeatable divider
        //example square root of 63 is 7.9... therefore if we divide 63 with 2,3,4,5,6,7 we already got all combinations
        //to decide if number is prime or not... numbers 2 and 3 don't work with this so just return true for them
        int root = (int)Math.sqrt(number);
        for (int i = 2; i <= root; i++) {
            if(number % i == 0){
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }
}
