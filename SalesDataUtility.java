package assignment5;

/*
 * Class: CMSC203 20798
 * Instructor: Ahmed Tarek 
 * Description: SalesDataUttility class provides methods for calculating
 *              totals, averages, and highest/lowest values on a ragged
 *              2D array of sales data.
 * Due:
 * Platform/compiler: Java/Eclipse
 * I pledge that I have completed the programming
 * assignment independently. I have not copied the code
 * from a student or any source. I have not given my code
 * to any student.
 * Print your Name here: Siddhant Bikram Thapa
 */

public class SalesDataUtility {

	/**
	 * calculating and returning the total of all values in the ragged 2D array.
	 * this method iterates every rows and columns to give total
	 */
	public static double getTotal(double[][] data) {
		double total = 0.0;
		for (int row = 0; row < data.length; row++) {
			for (int col = 0; col < data[row].length; col++) {
				total += data[row][col];
			}
		}
		return total;
	}

	/**
	 * calculating average and returning the average this method counts the number
	 * of element in 2D array and divides the sum by count, also safety checks if
	 * there is no element in the file
	 */
	public static double getAverage(double[][] data) {
		int count = 0;
		for (int row = 0; row < data.length; row++) {
			count += data[row].length;
		}
		// safety check to avoid division of 0
		if (count == 0)
			return 0.0;
		else
			return getTotal(data) / count;
	}

	// calculates the total of specific row and returns the value;

	public static double getRowTotal(double[][] data, int row) {
		double total = 0.0;
		for (int col = 0; col < data[row].length; col++) {
			total += data[row][col];
		}
		return total;
	}

	/**
	 * Calculate and returns the total of all values in the specified column,
	 * skipping it do not have that column index.
	 */
	public static double getColumnTotal(double[][] data, int col) {
		double total = 0.0;
		for (int row = 0; row < data.length; row++) {
			// to check if the current row have value in the index
			if (col < data[row].length) {
				total += data[row][col];
			}
		}
		return total;
	}

	/*
	 * comparing the highest in specific row and returning the highest
	 */
	public static double getHighestInRow(double[][] data, int row) {
		// at first, assuming first element in row to be largest and then comparing with
		// remaining

		double highest = data[row][0];
		for (int col = 1; col < data[row].length; col++) {
			if (data[row][col] > highest) {
				highest = data[row][col];
			}
		}
		return highest;
	}

	/*
	 * comparing the each variables in the specific row and returning it
	 */
	public static double getLowestInRow(double[][] data, int row) {
		// first assuming 1st element in the row to be lowest and comparing other
		double lowest = data[row][0];
		for (int col = 1; col < data[row].length; col++) {
			if (data[row][col] < lowest) {
				lowest = data[row][col];
			}
		}
		return lowest;
	}

	/*
	 * comparing all the elements across the entire ragged 2D array and returning
	 * the highest value .
	 */
	public static double getHighestInArray(double[][] data) {
		double highest = data[0][0];
		for (int row = 0; row < data.length; row++) {
			for (int col = 0; col < data[row].length; col++) {
				if (data[row][col] > highest) {
					highest = data[row][col];
				}
			}
		}
		return highest;
	}

	/*
	 * comparing all the elements across the entire ragged 2D array and returning
	 * lowest value .
	 */
	public static double getLowestInArray(double[][] data) {
		double lowest = data[0][0];
		for (int row = 0; row < data.length; row++) {
			for (int col = 0; col < data[row].length; col++) {
				if (data[row][col] < lowest) {
					lowest = data[row][col];
				}
			}
		}
		return lowest;
	}
}