package assignment5;

/*
 * Class: CMSC203 20798
 * Instructor: Ahmed Tarek
 * Description: Utility junit test for the Assignment 5
 * Due: 11/18/2026
 * Platform/compiler: Java/Eclipse
 * I pledge that I have completed the programming
 * assignment independently. I have not copied the code
 * from a student or any source. I have not given my code
 * to any student.
 * Print your Name here: Siddhant Bikram Thapa
 */

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

public class SalesDataUtilityTest1 {
	private double[][] negativeData = { { -100.0, -220.50, -30.0 }, { -455.25, -50.66 } };

	@Test
	public void testGetTotal() {
		double total = SalesDataUtility.getTotal(negativeData);
		assertEquals(-856.41, total, 0.001);
	}

	@Test
	public void testGetAverage() {
		double avg = SalesDataUtility.getAverage(negativeData);
		assertEquals(-171.282, avg, 0.001);
	}

	@Test
	public void testGetRowTotal() {
		assertEquals(-350.5, SalesDataUtility.getRowTotal(negativeData, 0), 0.001);
		assertEquals(-505.91, SalesDataUtility.getRowTotal(negativeData, 1), 0.001);

	}

	@Test
	public void testGetColumnTotalRagged() {
		assertEquals(-555.25, SalesDataUtility.getColumnTotal(negativeData, 0), 0.001);
		assertEquals(-271.16, SalesDataUtility.getColumnTotal(negativeData, 1), 0.001);
		assertEquals(-30.0, SalesDataUtility.getColumnTotal(negativeData, 2), 0.001);
	}

	@Test
	public void testHighestLowestArray() {
		assertEquals(-30.0, SalesDataUtility.getHighestInArray(negativeData), 0.001);
		assertEquals(-455.25, SalesDataUtility.getLowestInArray(negativeData), 0.001);
	}
}