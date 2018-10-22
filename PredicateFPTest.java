import java.util.ArrayList;
import java.util.List;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

/*
 *  A functional interface has only one abstract method, but it can have multiple default methods.
 *	@FunctionalInterface annotation is used to ensure an interface can’t have more than one abstract method. The use of this annotation is optional.
 *	The java.util.function package contains many built-in functional interfaces in Java 8, like Predicate, Consumer, Supplier, Function.
 */

public class PredicateFPTest {

	public static void main(String[] args) {

		// Simple Predicate
		int j=1;
		Predicate<Integer> lessThan10 = i -> i < 10; // Lamda Matches abstract method (test) of Predicate interface -  boolean test(T t); - Takes 1 parameter and returns a boolean value

		// Lamda in above statement can be replaced by an anonymous class which implements Predicate interface as below
		Predicate<Integer> lessThan11 = new Predicate<Integer>(){
			@Override
			public boolean test(Integer t) {
				return (t < 11);
			}
		}; 
		
		System.out.println("Is " + j + " less than 10? " + lessThan10.test(j));
		System.out.println("Is " + j + " less than 11? " + lessThan11.test(j));
		
		// Pass Predicate to a function as a parameter
		System.out.println(checkNumber(5,lessThan10));
		
		// Predicate chaining
		int k = 12;
		Predicate<Integer> greaterThan5 = i -> i > 5; 
		System.out.println("Is " + k + " greater than 5 and less than 10? " + greaterThan5.and(lessThan10).test(k));
		System.out.println("Is " + k + " greater than 5 and not less than 10? " + greaterThan5.and(lessThan10.negate()).test(k));
		System.out.println("Is " + k + " greater than 5 or less than 10? " + greaterThan5.or(lessThan10).test(k));
		System.out.println("Is " + k + " not greater than 5 or less than 10? " + greaterThan5.or(lessThan10).negate().test(k));
		
		// Predicate in Lists
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee(34,"Shirish"));
		employees.add(new Employee(36,"Rajesh"));
		employees.add(new Employee(45,"Naresh"));
		employees.add(new Employee(76,"Sarang"));
		
		
		// Functional interface parameter for forEach function is of type Consumer
		employees.forEach(employee -> {
			Predicate<Employee> predicate = emp -> emp.getName().startsWith("S");
			System.out.print(employee.getName() + " - Does Employee name start with S? - ");
			System.out.println(predicate.test(employee));
		});
		
		// primitive specializations of the Predicate interface
		// IntPredicate test method accepts only integer as a parameter whereas DoublePredicate test method accepts only double 
		IntPredicate prei = i -> i > 15;
		System.out.println(prei.test(13));
		
		DoublePredicate doui = i -> i > 10.0;
		System.out.println(doui.test(15.2));
		
		LongPredicate prel = i -> i > 15;
		System.out.println(prel.test(13));
		
		
		String str = "test";
		Predicate<String> predicate = Predicate.isEqual(str); // behaves like Objects.equals(Object,Object) - equality is determined by using the equals method of the first argument.
		System.out.println(predicate.test("Test"));
	}
	
	private static boolean checkNumber(int i, Predicate<Integer> lessThan10){
		System.out.print("Is " + i + " less than 10? ");
		return lessThan10.test(i);
	}
	
	//BiPredicate<T,U> - Represents a predicate (boolean-valued function) of two arguments.

}

class Employee{
	
	int age;
	String name;
	
	public Employee(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
