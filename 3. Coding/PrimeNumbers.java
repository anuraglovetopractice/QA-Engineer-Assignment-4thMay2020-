package qaEngineer;

import java.time.Duration;
import java.time.Instant;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PrimeNumbers {

	public static void main(String args[]) {
		byte countShouldBeLessThanTwo = 0;
		long primeNumberIndex = 1;
		int totalPrimeNumbers = 0;
		boolean invalidInput = true;
		while (invalidInput) {
			try {
				System.out.print("How many prime numbers you want to see: ");
				Scanner scanner = new Scanner(System.in);
				totalPrimeNumbers = scanner.nextInt();
				if (totalPrimeNumbers > 0) {
					invalidInput = false;
				} else {
					System.out.println("Please enter only natural number...\n");
				}
			} catch (InputMismatchException e) {
				System.out.println("Please enter a natural number between range (1 to 2147483647) \n");
			}
		}
		
		System.out.println("First " + totalPrimeNumbers + " Prime numbers are:-");
		for (long i = 2; primeNumberIndex <= totalPrimeNumbers; i++) {
			for (long j = i; j > 1; j--) {
				if (i % j == 0) {
					countShouldBeLessThanTwo++;
				}
			}
			if (countShouldBeLessThanTwo < 2) {
				if (i > 1 && primeNumberIndex > 0) {
					System.out.println(primeNumberIndex + "\tprime number is: " + i);
					primeNumberIndex++;
				} else {
					break;
				}
			}
			countShouldBeLessThanTwo = 0;
		}
	}
}