import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* Lambda expressions basically express instances of functional interfaces (An interface with single abstract method is called functional interface. 
 * An example is java.lang.Runnable). lambda expressions implement the only abstract function and therefore implement functional interfaces.
 */

public class LamdaExpressions1 {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Runnable Functional Interface Demo: ");
		
		// Lamda expresssion implementing Runnable interface and overriding run method 
		Runnable r = () -> System.out.println("Inside new Thread.."); // Runnable is functional interface 
		Thread thread = new Thread(r);
		thread.start();
		thread.join();
		
		List<String> names = new ArrayList<>();
		
		System.out.println("Comparator Functional Interface Demo: ");
		
		names.add("Sasa");
		names.add("Pacha");
		names.add("Naso");
		names.add("Uero");
		names.add("Honda");

		// Comparator is a functional interface 
		Comparator<String> c = (a,b) -> a.compareTo(b);
		Collections.sort(names, c);
		
		// Using lamda inside enhanced for loop
		names.forEach(name -> System.out.println(name));
		
		ConcatUpperCase cu = (s1,s2) -> { 
				System.out.println("Inside Lamda.."); 
				return s1.toUpperCase() + s2.toUpperCase(); // return mandatory if more than one statements in a lamda OR curly braces are used
		};
		
		// Lamda expression passed as a data to a function
		System.out.println(doStringStuff(cu,names.get(0),names.get(1)));
	}
	
	private static String doStringStuff(ConcatUpperCase cuc, String s1, String s2) {
		return cuc.concatAndUpper(s1, s2);
	}
}

interface ConcatUpperCase {
	
	public String concatAndUpper(String s1, String s2);
}

/* Rules - 
 
1) The body of a lambda expression can contain zero, one or more statements.
2) When there is a single statement, curly brackets are not mandatory and the return type of the anonymous function is the same as that of the body expression.
3) When there are more than one statements, then these must be enclosed in curly brackets (a code block) and the return type of the anonymous function is the 
   same as the type of the value returned within the code block, or void if nothing is returned.  
4) Lamda expressions are added to enable to treat functionality as a method argument, or code as data.
5) A lambda expression can be passed around as if it was an object and executed on demand.
6) Lamda expressions are often used where anonymous classes are used. They can also be used as a replacement of enhanced for loop.
7) Lamda expressions help to optimize code by reducing lines of code.
8) Lamda expression has got parentheses , arrow and body.
9) It is not mandatory to use parentheses if there is a single parameter. Use parentheses only when there are no params or more than one params. 
10) 
*/