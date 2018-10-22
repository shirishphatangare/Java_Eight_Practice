import java.util.ArrayList;
import java.util.List;

/* Lamda is treated as a nested block of code and has same scope as a nested block */
/* Local variable from enclosing scope used inside lamda must be effectively final (same rule applies for anonymous function). i.e either declared as a final or it's value never changed */
/* For enhanced for loop, a new local variable is created for each iteration of the loop. This local variable is final and hence can be used inside lamda.
 * For old-style for loop, a new local variable is created for each iteration of the loop only when declaration of variable is inside for loop.
 * If declaration is outside, then lamda can not use it, since it is not final. 
 * Lamdas are Java's first step towards functional programming.
 */

public class LamdaExpressions2 {

	public static void main(String[] args) {
		testLamda1();
		testLamda2();
	}
	
	private static void testLamda1() {
		int i = 0;
		i++;
		int j = 0;
	//	j++; // This is not allowed if j is to be used inside a lamda expression or a nested block 
		
		final int k = 5; 
		// Below is a nested block of code which can access local variable declared in an enclosing scope i.e. testLamda() function
		{
			i++;
			System.out.println("value of i declared in an enclosing block is - " + i);
		}
		
		i++;
		System.out.println("value of i declared in an enclosing block is - " + i); // same variable is again accessed outside a nested block
		
		BlockLamda bl = () -> {
			System.out.println("Inside Lamda..");
			System.out.println("Value of j declared in an enclosing block is - " + j); // j is effectively final
			System.out.println("Value of k declared in an enclosing block is - " + k); // k is final
		};
		
		bl.printValue();
	}
	
	private static void testLamda2() {
		List<String> names = new ArrayList<>();
		
		names.add("Shirish");
		names.add("Nitin");
		names.add("Kaka");
		names.add("Lofer");
		
//		for(String name: names) { // For Enhanced for loop a new local variable is created in each iteration. Hence it is effectively final and can be used in lamda
//			BlockLamda bl = () -> {
//				System.out.println("Inside Lamda..");
//				System.out.println("Name in list is " + name);
//			};
//			bl.printValue();
//		}
		
		//String name = null;
		for(int i=0;i<names.size();i++) {
			String name = names.get(i); // No compiler error and program executes successfully
			//name = names.get(i); // gets compiler error, since name is no longer effectively final
			BlockLamda bl = () -> {
				System.out.println("Inside Lamda..");
				System.out.println("Name in list is " + name);
			};
			bl.printValue();
		}
	}
}

interface BlockLamda{
	public void printValue();
}
