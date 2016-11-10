package edu.osu.sort;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;
import java.util.Random;

public class SortTest  {

    private static List<Integer> randomIntegerArray(int n){
	return null;
    }

    /*
    @Test // This test is an example template using an oracle
    public void randomTestExample() {
	int n_runs = 10;
	for(int i=0;i<n_runs;i++){
	    
	    // Generate a random array of size 10
	    List<Integer> sorted = randomIntegerArray(10);

	    // Copy the sorted array before sorting it 
	    List<Integer> original = new ArrayList(sorted);

	    // Sort it
	    Sort.sort(sorted);

	    // Test the result 
	    assertTrue(SortOracle.isSorted(original,sorted));
	}
    }
    
    */
    @Test
    public void randomTestOracle() {    
        final int numberOfTests = 100;
        Random r = new Random();
        int listSize = r.nextInt(1000);
        for (int testNum = 0; testNum < numberOfTests; testNum++ ){
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < listSize; i++ ){
                list.add(r.nextInt(1000) - 500);
            }
            ArrayList<Integer> listTestSort = new ArrayList<Integer>(list);
            Sort.sort(listTestSort);
            assertTrue(SortOracle.isSorted(list,listTestSort));
        }


    }

    @Test
    public void randomTestAssertion() {
        final int numberOfTests = 100;
        Random r = new Random();
        int listSize = r.nextInt(1000);
        for (int testNum = 0; testNum < numberOfTests; testNum++ ){
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < listSize; i++ ){
                list.add(r.nextInt(1000) - 500);
            }
            ArrayList<Integer> controlList = new ArrayList<Integer>(list);
            Sort.sort(list);
            assertEquals(listSize,list.size());//first assert
            assertTrue(list.get(0)<=list.get(listSize-1));//second assert
            for (int i = 0; i < listSize; i++ ){
                assertTrue(list.get(0) <= list.get(i));//third assert
            }
            for (int i = 0; i < listSize; i++ ){
                assertTrue(list.get(listSize-1) >= list.get(i));//fourth assert
            }
            for (int i = 0; i < listSize; i++ ){
                list.remove(controlList.get(i));
            }
            assertTrue(list.isEmpty());//fifth assert
        }

    }
}
