import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Type inference is a Java compiler's ability to look at each method invocation and corresponding declaration to determine \
 * the type argument (or arguments) that make the invocation applicable. The inference algorithm determines the types of the arguments and, if available, the type that the result is being assigned, or returned. 
 * Finally, the inference algorithm tries to find the most specific type that works with all of the arguments.
 */

public class TypeInferenceTest {

	public static void main(String[] args) {
		List<Box<Integer>> listofBoxes = new ArrayList<>(); // Use of Diamond
		TypeInferenceTest.<Integer>addBox(10, listofBoxes); // This is acceptable but not required
		addBox(20, listofBoxes); // Java compiler automatically infers (from the method's arguments) that the type parameter is Integer
		addBox(30, listofBoxes);
		
		displayBoxes(listofBoxes);
		
		/*Compilers from releases prior to Java SE 7 are able to infer the actual type parameters of generic constructors, similar to generic methods. However, compilers in Java SE 7 
		 * and later can infer the actual type parameters of the generic class being instantiated if you use the diamond (<>).
		 */
		
		//Box<Integer> box = new Box<Integer>("Type inference"); // Prior to JDK 1.7
		Box<Integer> box = new Box<>("Type inference"); // For JDK 1.7 and 1.8 
		
		/*
		 * In this case, processStringList requires an argument of type List<String>. The method Collections.emptyList returns a value of List<T>, 
		 * so using the target type of List<String>, the compiler infers that the type argument T has a value of String.
		 */
		
		// The notion of what is a target type has been expanded to include method arguments in JDK 1.8
		new TypeInferenceTest().processStringList(Collections.emptyList()); // This works in JDK 1.8 but not in JDK 1.7 and prior
		//new TypeInferenceTest().processStringList(Collections.<String>emptyList()); // This works in JDK 1.7 and prior
		
		List<String> listOne = Collections.emptyList(); // This works in JDK 1.8 and JDK 1.7 and prior too
	}
	
	
	
	public static <T> void addBox(T t, List<Box<T>> boxes){
		Box<T> box = new Box<>(); // Use of Diamond
		box.setT(t);
		boxes.add(box);
	}
	
	public static <T> void displayBoxes(List<Box<T>> boxes){
		for(int i=0;i<boxes.size();i++){
			System.out.println("Box #" + i + " contains [" + boxes.get(i).getT().toString() + "]");
		}
	}
	
	public void processStringList(List<String> stringList) {
	    // process stringList
	}

}

class Box<T> {
	private T t;
	
	Box(){
		
	}
	// constructors can be generic (in other words, declare their own formal type parameters) in both generic and non-generic classes
	<U>Box(U u){
		System.out.println(u);
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}
	
}
