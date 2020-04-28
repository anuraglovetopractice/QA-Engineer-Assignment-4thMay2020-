package qaEngineer;

import java.time.Duration;
import java.time.Instant;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PrimeNumbers {

	public static void main(String args[]) {
		int countShouldBeLessThanTwo = 0;
		int primeNumberIndex = 1;
		int totalPrimeNumbers = 0;
		boolean invalidInput = true;
		while (invalidInput) {
			try {
				System.out.print("How many prime numbers you want to see: ");
				Scanner scanner = new Scanner(System.in);
				totalPrimeNumbers = scanner.nextInt();
				if (totalPrimeNumbers != 0) {
					invalidInput = false;
				}
				else {
					System.out.println("Please enter only natural number...\n");
				}
			} catch (InputMismatchException e) {
				System.out.println("Please enter only natural number...\n");
			}
		}
		Instant start = Instant.now();
		System.out.println("First " + totalPrimeNumbers + " Prime Numbers Are:-");
		for (int i = 2; primeNumberIndex <= totalPrimeNumbers; i++) {
			for (int j = i; j > 1; j--) {
				if (i % j == 0) {
					countShouldBeLessThanTwo++;
				}
			}
			if (countShouldBeLessThanTwo < 2) {
				System.out.println(primeNumberIndex + "\tprime number is: " + i);
				primeNumberIndex++;
			}
			countShouldBeLessThanTwo = 0;
		}
		Instant finish = Instant.now();
		long timeElapsed = Duration.between(start, finish).toMillis();

		System.out.println("\n\nTotal time taken by program to find the " + primeNumberIndex + " prime numbers, are "
				+ timeElapsed + " milli seconds");
	}
}
