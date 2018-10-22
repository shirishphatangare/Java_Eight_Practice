import java.util.Arrays;
import java.util.Comparator;


/* Java provides a new additional feature in Array class which is used to sort array elements parallel. New methods has added to java.util.
 * Arrays package that use the JSR 166 Fork/Join parallelism common pool to provide sorting 
 * of arrays in parallel. The methods are called parallelSort() and are overloaded for all the primitive data types and Comparable objects.
 */

/* The sorting algorithm is a parallel sort-merge that breaks the array into sub-arrays that are themselves sorted and then merged. 
 * When the sub-array length reaches a minimum granularity, the sub-array is sorted using the appropriate Arrays.sort method. 
 * If the length of the specified array is less than the minimum granularity, then it is sorted using the appropriate Arrays.sort method. 
 * The algorithm requires a working space no greater than the size of the original array. The ForkJoin common pool is used to execute any parallel tasks.
 */

public class ParallelArraySortingTest {

	public static void main(String[] args) {
		int[] intArray1 = {23,4,12,45,27,96,34,764,-2,0};
		Integer[] intArray2 = {23,4,12,45,27,96,34,764,-2,0};
		
		Arrays.parallelSort(intArray1); // Arrays.parallelSort(int[] a). similarly versions exists for char[],float[],double[],long[],short[],byte[] 

		for (int i : intArray1) {  ;
            System.out.print(i+" ");  
        }  
		System.out.println();
		/* we are passing starting and end index of the array. The first index is inclusive and end index is exclusive i.e. if we pass 0 as start index and 4 as end index, only 0 to 3 index elements will be sorted.
		It throws IllegalArgumentException if start index > end index.
		It throws ArrayIndexOutOfBoundsException if start index < 0 or end index > a.length. */
		
		int[] intArray3 = {23,4,12,45,7,96,34,764,-2,0}; 
		Arrays.parallelSort(intArray3, 0, 5); // sorting only 0 to 4 index elements - similarly versions exists for char[],float[],double[],long[],short[],byte[] 
		
		for (int i : intArray3) {  ;
        	System.out.print(i+" ");  
		}  
		System.out.println();
		
		Arrays.parallelSort(intArray2); // Method used is Arrays.parallelSort(T[] a)
		displayArrayElements(intArray2);
		
		Arrays.parallelSort(intArray2,Comparator.reverseOrder()); // Returns a comparator that imposes the reverse of the natural ordering - Method used is Arrays.parallelSort(T[] a ,comparator)
		displayArrayElements(intArray2);
		
		Integer[] intArray4 = {23,4,12,45,27,96,34,764,-2,0};
		Arrays.parallelSort(intArray4,4,8); // sorting only 4 to 7 index elements 
		displayArrayElements(intArray4);
		
		Character[] charArray = {'f','E','e','S','a','G','z'};
		Arrays.parallelSort(charArray); // Method used is Arrays.parallelSort(T[] a)
		displayArrayElements(charArray);
		
		Arrays.parallelSort(charArray,3,6,Comparator.reverseOrder());  // Method used is Arrays.parallelSort(T[] a ,int startIndex, int endIndex,comparator) // sorting only 3 to 5 in reverse order
		displayArrayElements(charArray);
		
	}
	
	private static <T> void displayArrayElements(T [] anArray){
		for(T n : anArray){
			System.out.print(n);
			System.out.print(" ");
		}
		System.out.println();
	}

}
