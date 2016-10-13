package edu.osu.cs362;

import static org.junit.Assert.*;
import org.junit.Test;

public class WarmupTest  {
    @Test
    public void testFindIntegerExample() {

		int result = WarmUp.findInteger(new int[] { 1, 2, 3, 4, 5 }, 100);
		assertEquals(result, -1);

		for (int i = 1; i < 5; i++) {
			int result2 = WarmUp.findInteger(new int[] { 1, 2, 3, 4, 5 }, i + 1);
			assertEquals("findInteger(new int[]{1,2,3,4,5}," + i + ")", result2, i);
		}
	}

	//test that we cant find an int in empty array
	@Test
	public void testFindIntegerNullArray() {
		int arr[] = null;
		assertEquals(-1, WarmUp.findInteger(arr, 0));
	}

	//test that we can find and int that isn't in the array
	@Test
	public void testFindIntegerNumberNotThere() {
		int arr[] = new int[]{1,2,3,4,5};
		assertEquals(-1, WarmUp.findInteger(arr, 100));
	}

	//test that we can find ints regardless of element number
	@Test
	public void testFindIntegerNumberThereEachSpot() {
		int arr[] = new int[]{1,2,3,4,5};
		for ( int i = 0; i < arr.length; i++ ){
			assertEquals(i, WarmUp.findInteger(arr, arr[i]));
		}
	}

	//test that we can find zero regardless of element number
	@Test
	public void testLastZeroOneZeroEachCell() {
		//test each cell to be zero
		for (int i = 0; i < 5; i++) {
			int testArr[] = new int[]{1, 2, 3, 4, 5};
			testArr[i] = 0;
			assertEquals(i, WarmUp.lastZero(testArr));
		}
	}

	//test that we get can't find zero on empty array
	@Test
	public void testLastZeroEmptyArray() {
	//test for an empty array
		int testArr[] = null;
		assertEquals(-1,WarmUp.lastZero(testArr));
	}

	//cant find zero when no zeros in array
	@Test
	public void testLastZeroNoZeros() {
	//test for no zero cells
		assertEquals(-1,WarmUp.lastZero(new int[]{1,2,3,4,5}));
	}

	//find last zero with multiple zeros, skip element 0 as that would be only checking one element,
	//done in different test
	@Test
	public void testLastZeroMultipleZeros(){
	//test multiple cells to be zero //decp,,emt
		int testArr[] = new int[]{0,2,3,4,5};
		for ( int i = 1; i < 5; i++ ){
			testArr[i] = 0;
			assertEquals(i,WarmUp.lastZero(testArr));
		}
	}

	@Test
	public void testLastNullArray(){
		//test for null array
		int testArr[] = null;
		assertEquals(-1,WarmUp.last(testArr,5));
	}

	@Test
	public void testLastOnlyOneValEach() {
		//test for one occurance of each value, test that we can find regardless of element number
		int testArr[] = new int[]{0, 1, 2, 3, 4};
		for (int i = 0; i < testArr.length; i++) {
			assertEquals(i, WarmUp.last(testArr, i));
		}
	}

	//test that we can find last element regardless of number of elements
	//dont test first element as that's tested above
	@Test
	public void testLast(){
		//test for multiple occurances of value
		int testArr[] = new int[]{10, 1, 2, 3, 4};
		for (int i = 1; i < 5; i++) {
			testArr[i] = 10;
			assertEquals(i, WarmUp.last(testArr, 10));
		}
	}

	//test that there are no positives in empty array
	@Test
	public void testPositiveNullArray() {
		//test for null array
		int testArr[] = null;
		assertEquals(0, WarmUp.positive(testArr));//decomment
	}

	//test that there are no negs in array
	@Test
	public void testPositiveNoNegatives() {
		int testArr[] = new int[]{1, 2, 3, 4, 5};
		//test for no negatives
		assertEquals(testArr.length, WarmUp.positive(testArr));
	}

	//test that we correctly count elements regardless of their location
	@Test
	public void testPositiveCountElementsRegardlessLocation() {
		//test for various negative counts, including all negatives
		for ( int i = 0; i < testArr.length; i++ ){
			testArr[i] = testArr[i] * -1;
			assertEquals(testArr.length-(i+1),WarmUp.positive(testArr));
		}
	}
	//test for null array
	int testArr[] = null;//used for numerous upcoming tests

	@Test
	public void testOddOrPosNullArray() {
		assertEquals(0,WarmUp.oddOrPos(testArr));//decomment
	}

	//test all pos array, some odd
	@Test
	public void testOddOrPos() {
		testArr = new int[]{1, 2, 3, 4, 5};
		assertEquals(testArr.length, WarmUp.oddOrPos(testArr));
	}

	//test all pos array, no odd
	@Test
	public void testOddOrPosAllPosNoNeg() {
		for (int i = 0; i < testArr.length; i++) {
			testArr[i] = testArr[i] * 2;
		}
		assertEquals(testArr.length, WarmUp.oddOrPos(testArr));
	}

	//test all neg array, no odds
	@Test
	public void testOddOrPosAllNegNoOdd() {
		for (int i = 0; i < testArr.length; i++) {
			testArr[i] = testArr[i] * -1;
		}
		assertEquals(0, WarmUp.oddOrPos(testArr));
	}

	//test half neg array, no odds
	@Test
	public void testOddOrPosJalfNegNoOdd(){
		for ( int i = 0; i < testArr.length; i++ ){
			if ( i % 2 == 0){
				testArr[i] = testArr[i] * -1;
			}
		}
		int correctTestAnswer;
		if ( testArr.length % 2 == 0){
			correctTestAnswer = testArr.length/2;
		}
		else{
			correctTestAnswer = testArr.length/2 + 1;
		}
		assertEquals(correctTestAnswer,WarmUp.oddOrPos(testArr));
	}
}
