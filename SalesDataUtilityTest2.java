package assignment5;

/*
 * Class: CMSC203 20798
 * Instructor:Ahmed Tarek
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

public class SalesDataUtilityTest2 {
	private double[][] midEmpty = { { 10.0, 20.0 }, {}, { -30.0, 40.0 } };

	@Test
	public void testGetTotal() {
		double total = SalesDataUtility.getTotal(midEmpty);
		assertEquals(40.0, total, 0.001);
	}

	@Test
	public void testGetAverage() {
		double avg = SalesDataUtility.getAverage(midEmpty);
		assertEquals(10.0, avg, 0.001);
	}

	@Test
	public void testGetRowTotal() {
		assertEquals(30.0, SalesDataUtility.getRowTotal(midEmpty, 0), 0.001);
		assertEquals(00.0, SalesDataUtility.getRowTotal(midEmpty, 1), 0.001);
		assertEquals(10.0, SalesDataUtility.getRowTotal(midEmpty, 2), 0.001);
	}

	@Test
	public void testGetColumnTotalRagged() {
		assertEquals(-20.0, SalesDataUtility.getColumnTotal(midEmpty, 0), 0.001);
		assertEquals(60.0, SalesDataUtility.getColumnTotal(midEmpty, 1), 0.001);
	}

	@Test
	public void testHighestLowestArray() {
		assertEquals(40.0, SalesDataUtility.getHighestInArray(midEmpty), 0.001);
		assertEquals(-30.0, SalesDataUtility.getLowestInArray(midEmpty), 0.001);
	}
}