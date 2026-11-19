package assignment5;

/*
 * Class: CMSC203 20798
 * Instructor:Ahmed Tarek
 * Description: SalesFileIO reads sales data from a text file
 *              into a ragged 2D array and writes a summary report to an
 *              output file.
 * Due:11/18/2026
 * Platform/compiler: Java/Eclipse
 * I pledge that I have completed the programming
 * assignment independently. I have not copied the code
 * from a student or any source. I have not given my code
 * to any student.
 * Print your Name here: Siddhant Bikram Thapa
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class SalesFileIO {

	/**
	 * Reads sales data from the given file path into a ragged 2D array. Each line
	 * is one row; values are space-separated doubles.
	 */
	public static double[][] readSalesData(String filename) {
		/*
		 * we don't know how many array lines will be in file so array list is made to
		 * store the each element of row
		 */
		ArrayList<double[]> rows = new ArrayList<>();

		// created a file object for input file
		File file = new File(filename);

		/*
		 * to prevent for file not found used try and catch, looping the file to read
		 * the whole file
		 */
		try {
			Scanner fileScanner = new Scanner(file);

			// loop to read whole file
			while (fileScanner.hasNextLine()) {

				// to read the next line and trimming extra spaces in that line
				String line = fileScanner.nextLine().trim();

				// to prevent form the empty lines in the file if it empty also continue reading
				// file

				if (line.isEmpty())
					continue;
				/*
				 * a Scanner is made for the line to count how many values are in it, it
				 * separates the values wherever there is spaces or tabs, even if there are
				 * multiple spaces in a row
				 */
				Scanner countScanner = new Scanner(line);
				int count = 0;
				while (countScanner.hasNext()) {
					countScanner.next();
					count++;
				}
				countScanner.close();

				// the count sets the size of the double array for this row, then a second
				// Scanner reads each value and converts it into a double
				double[] values = new double[count];
				Scanner lineScanner = new Scanner(line);
				for (int i = 0; i < count; i++) {
					values[i] = Double.parseDouble(lineScanner.next());
				}
				lineScanner.close();

				// adding those converted values to row array list
				rows.add(values);
			}

			// display message if file not found
		} catch (FileNotFoundException e) {
			System.out.println("Error: File not found - " + filename);

			// display message if a value in the file is not a number, then clear the rows
			// so an empty array is returned
		} catch (NumberFormatException e) {
			System.out.println("Error: File contains a non-numeric value - " + e.getMessage());
			rows.clear();
		}

		// Converting the array list into 2D array
		double[][] data = new double[rows.size()][];
		for (int i = 0; i < rows.size(); i++) {
			data[i] = rows.get(i);
		}
		return data;
	}

	/*
	 * Writing a formatted summary report to output file.
	 */
	public static void writeSummary(String filename, double[][] data) throws FileNotFoundException {
		File file = new File(filename);

		PrintWriter writer = new PrintWriter(file);
		// Writing total sale and average in the file

		// when there is no data there is no data
		if (data == null || data.length == 0) {
			writer.println("No sales data to summarize.");
			writer.close();
			return;
		}

		writer.printf("Total sales: %.2f%n", SalesDataUtility.getTotal(data));
		writer.printf("Average sale: %.2f%n", SalesDataUtility.getAverage(data));

		// running in loop to write total by each row
		for (int row = 0; row < data.length; row++) {
			writer.printf("Row %d total: %.2f highest: %.2f lowest: %.2f%n", row,
					SalesDataUtility.getRowTotal(data, row), SalesDataUtility.getHighestInRow(data, row),
					SalesDataUtility.getLowestInRow(data, row));
		}
		// finding the longest row of ragged array to know how many loop to iterate for
		// the column
		int maxCols = 0;
		for (double[] row : data) {
			if (row.length > maxCols)
				maxCols = row.length;
		}
		// using the above find max number of column to write summary of the total
		// number of column in the the summary document
		for (int col = 0; col < maxCols; col++) {
			writer.printf("Column %d total: %.2f%n", col, SalesDataUtility.getColumnTotal(data, col));
		}
		// writing the highest value of array and lowest in the summary file
		writer.printf("Highest value: %.2f%n", SalesDataUtility.getHighestInArray(data));
		writer.printf("Lowest value: %.2f%n", SalesDataUtility.getLowestInArray(data));

		writer.close();

	}
}