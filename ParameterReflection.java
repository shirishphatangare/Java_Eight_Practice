import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/*Java provides a new feature in which you can get the names of formal parameters of any method or constructor. 
 * The java.lang.reflect package contains all the required classes like Method and Parameter to work with parameter reflection. 
 */


// Before compiling and executing below code, first compile Calculate class by following command: otherwise parameter names will be printed as arg0,arg1 etc.
// javac -parameters Calculate.java

public class ParameterReflection {

	public static void main(String[] args) {
		Calculate calculate = new Calculate();
		Class calculateClass = calculate.getClass();
		
		/* getMethods - Returns an array containing Method objects reflecting all the public methods of the class or interface represented by this Class object, 
		 * including those declared by the class or interface and those inherited from superclasses and superinterfaces.  
		 */
		
		//Method[] methods = calculateClass.getMethods();
		Method[] declaredMethods = calculateClass.getDeclaredMethods(); // Returns an array containing Method objects reflecting all thedeclared methods of the class or interface represented by this Class object, including public, protected, default (package)access, and private methods, but excluding inherited methods. 
		
		for (Method method: declaredMethods) {
				System.out.println("Method Name: " + method.getName());
				Parameter[] parameters = method.getParameters();
			for(Parameter parameter: parameters) {
				System.out.println("Parameter Name: " + parameter.getName());
				System.out.println("Parameter Type: " + parameter.getParameterizedType());
			}
			System.out.println("-------------------------------");
		}
	};

}

class Calculate {
	
	public int add (int parameter1, int parameter2) {
		return parameter1 + parameter2;
	}
	
	public int multiply (int parameter1, int parameter2) {
		return parameter1 * parameter2;
	}
}
