package assignment5;

import java.io.FileNotFoundException;

import java.util.Scanner;

/*
 * Class: CMSC203 20798
 * Instructor: Ahmed Tarek
 * Description: SalesAppDriver is the class which asks the user input file for where it abstract the data 
 * 				and  display the user the summary written to output file
 * Due:11/18/2026
 * Platform/compiler: Java/Eclipse
 * I pledge that I have completed the programming
 * assignment independently. I have not copied the code
 * from a student or any source. I have not given my code
 * to any student.
 * Print your Name here: Siddhant Bikram Thapa
 */
public class SalesAppDriver {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner keyboard = new Scanner(System.in);

		// asking user to input file name and trimming input if there is while space
		// after file name
		System.out.print("Enter the input file name: ");
		String inputFile = keyboard.nextLine().trim();

		// asking user in which file they want the summary to be written
		System.out.println("Enter the output summary file name: ");
		String outputFile = keyboard.nextLine().trim();

		// reading input file and displaying to user
		double[][] data = SalesFileIO.readSalesData(inputFile);

		// checking file if empty then display message
		if (data == null || data.length == 0) {
			System.out.println("No data, Exiting.");
			keyboard.close();
			return;
		}
		// display all the summary
		System.out.println("\n---- Sales Report ----");
		System.out.printf("Total sales: %.2f%n", SalesDataUtility.getTotal(data));
		System.out.printf("Average sale: %.2f%n", SalesDataUtility.getAverage(data));
		System.out.printf("Highest value: %.2f%n", SalesDataUtility.getHighestInArray(data));
		System.out.printf("Lowest value: %.2f%n", SalesDataUtility.getLowestInArray(data));

		// displaying row number with total
		System.out.println();
		for (int row = 0; row < data.length; row++) {
			System.out.printf("Row %d  total: %.2f  highest: %.2f  lowest: %.2f%n", row,
					SalesDataUtility.getRowTotal(data, row), SalesDataUtility.getHighestInRow(data, row),
					SalesDataUtility.getLowestInRow(data, row));
		}

		// Find the length of the longest row to determine the number of column
		// iterations
		int maxCols = 0;
		for (double[] row : data) {
			if (row.length > maxCols)
				maxCols = row.length;
		}

		System.out.println();
		for (int col = 0; col < maxCols; col++) {
			System.out.printf("Column %d total: %.2f%n", col, SalesDataUtility.getColumnTotal(data, col));

		}
		// writing the summary to output file
		SalesFileIO.writeSummary(outputFile, data);
		System.out.println("Summary written to: " + outputFile);

		keyboard.close();
		System.exit(0);
	}
}