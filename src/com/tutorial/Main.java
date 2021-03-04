package com.tutorial;

import com.tutorial.calculations.PrimeNumberResolver;
import com.tutorial.xlsparse.ParseExcelFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		//if argument were not typed in show console warning and close
		if (args.length == 1) {
			String path = args[0];
			File file = new File(path);
			//check if file exist
			if (file.exists()) {
				System.out.format("File %s exists\n", file.getAbsolutePath());
				List<Double> numberArr;
				System.out.println("Prime numbers are :");
				try {
					numberArr = ParseExcelFile.getParsedNumbers(path);
					for (double number : numberArr) {
						if (isNaturalNumber(number) && PrimeNumberResolver.isPrime(number)) {
							System.out.println(number);
						}
					}
				} catch (IOException e) {
					System.out.println(e);
				}
			}

			else {
				System.out.format("File %s does not exists", file.getAbsolutePath());
			}
		}
		
		else {
			System.out.println("APPLICATION TAKES PARAMETER OF INPUT FILE PATH");
		}

	}
	
	//test if number is natural not negative or with decimal places
	private static boolean isNaturalNumber(Double d) {
		if(d % 1 > 0) return false;
		if(d <= 0) return false;
		return true;
	}
}
