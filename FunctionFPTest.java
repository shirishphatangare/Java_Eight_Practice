import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;
/*
 * public interface Function<T, R>
 * abstract method - R apply(T t);
 * 
 */
public class FunctionFPTest {
	public static void main(String[] args) {
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person("John","Dave",33));
		persons.add(new Person("Kim","Tod",42));
		persons.add(new Person("Jim","Carry",63));
		persons.add(new Person("Steve","Sims",21));
		persons.add(new Person("Brandon","Jackson",73));
		
		// Single Function 
		Function<Person,String> getLastname2Chars = person -> person.getLastName().substring(0, 2);
		
		System.out.println("Printing first 2 characters for lastnames in a List..");
		persons.forEach(person -> {
			System.out.println(getLastname2Chars.apply(person));
		});
		
		// Passing function as a parameter to a function
		Function<Person,String> getFirstName = person -> person.getFirstName().toUpperCase();
		Function<Person,String> getLastName = person -> person.getLastName().toUpperCase();
		
		Random random = new Random();
		System.out.println("Printing First Or Last Name in uppercase..");

		persons.forEach(person -> {
			if(random.nextBoolean()){
				printFirstOrLastNamesRandomly(getFirstName,person);
			}else{
				printFirstOrLastNamesRandomly(getLastName,person);
			}
		});
		
		// Chained functions - compose function reverses execution order as that of andThen function
		Function<String,Integer>getLength = (arg1) -> arg1.length();
		
		System.out.println("Printing lengths of First and Last Names..");
		persons.forEach(person -> {
			int firstNameLength = getFirstName.andThen(getLength).apply(person);
			int lastNameLength = getLastName.andThen(getLength).apply(person);
			System.out.println(firstNameLength + "-" + lastNameLength);
		});
		
		// BiFunction for two input params - BiFunction can be a first argument in chained functions but not middle or last because it takes 2 input params
		// No compose function for BiFunction for above reason
		BiFunction<String, String, String> concatNames = (firstName,lastName) -> firstName.concat(" ").concat(lastName);
		System.out.println("Concatenating FirstName and LastName in a List..");
		persons.forEach(person -> {
			System.out.println(concatNames.apply(person.getFirstName(), person.getLastName()));
		});
		
		int l = 10;
		UnaryOperator<Integer> unary = i -> i+10;
		System.out.println("Value of l after adding 10 is : " + unary.apply(l));
		
		// DoubleUnaryOperator - Represents an operation on a single double-valued operand that produces a double-valued result.\
		// IntUnaryOperator - Represents an operation on a single int-valued operand that produces an int-valued result.
		// LongUnaryOperator - Represents an operation on a single long-valued operand that produces a long-valued result.
		
		int k=7;
		int o=7;
		
		BinaryOperator<Integer> binary = (i,j) -> i+(j*10);
		System.out.println(binary.apply(k, o));
		
		// DoubleBinaryOperator	- Represents an operation upon two double-valued operands and producing a double-valued result.
		// IntBinaryOperator - Represents an operation upon two int-valued operands and producing an int-valued result.
		// LongBinaryOperator - Represents an operation upon two long-valued operands and producing a long-valued result.
	}
	
	private static void printFirstOrLastNamesRandomly(Function<Person,String> getName,Person person){
		System.out.println(getName.apply(person));
	}
	
//	DoubleFunction<R> - Represents a function that accepts a double-valued argument and produces a result.
//	DoubleToIntFunction	- Represents a function that accepts a double-valued argument and produces an int-valued result.
//	DoubleToLongFunction - Represents a function that accepts a double-valued argument and produces a long-valued result.
//	IntFunction<R> - Represents a function that accepts an int-valued argument and produces a result.
//	IntToDoubleFunction - Represents a function that accepts an int-valued argument and produces a double-valued result.
//	IntToLongFunction - Represents a function that accepts an int-valued argument and produces a long-valued result.
//	LongFunction<R>	- Represents a function that accepts a long-valued argument and produces a result.
//	LongToDoubleFunction - Represents a function that accepts a long-valued argument and produces a double-valued result.
//	LongToIntFunction - Represents a function that accepts a long-valued argument and produces an int-valued result.
//	ToDoubleBiFunction<T,U>	- Represents a function that accepts two arguments and produces a double-valued result.
//	ToDoubleFunction<T>	- Represents a function that produces a double-valued result.
//	ToIntBiFunction<T,U> - Represents a function that accepts two arguments and produces an int-valued result.
//	ToIntFunction<T> - Represents a function that produces an int-valued result.
//	ToLongBiFunction<T,U> - Represents a function that accepts two arguments and produces a long-valued result.
//	ToLongFunction<T> - Represents a function that produces a long-valued result.
	
}

class Person{
	String firstName;
	String lastName;
	int age;
	
	
	public Person(String firstName, String lastName, int age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}
	
}
