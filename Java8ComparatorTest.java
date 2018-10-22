import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Java8ComparatorTest {

	public static void main(String[] args) {
		
		List<Employee> empList = new ArrayList<>();
		
		empList.add(new Employee("Jayesh",25));
		empList.add(new Employee("Karim",53));
		empList.add(new Employee("Jaju",52));
		empList.add(new Employee("Jaju",59));
		empList.add(new Employee(null,42));
		empList.add(new Employee("Jaju",29));
		empList.add(new Employee("Ramesh",23));
		empList.add(new Employee(null,62));
		empList.add(new Employee("Prakash",42));
		empList.add(new Employee("Harish",34));
		empList.add(new Employee(null,29));
		
		// Use Lamda in Collections.sort
//		Collections.sort(empList, (e1,e2) -> e1.getName().compareTo(e2.getName()));
//		empList.forEach(System.out::println);
		
		// Use Lamda in sort method of List
//		empList.sort((e1,e2) -> e1.getName().compareTo(e2.getName()));
//		empList.forEach(System.out::println);
		
		// comparing - Accepts a function that extracts a Comparable sort key from a type T, and returns a Comparator<T> that compares by that sort key. 
//		Comparator<Employee> comparator1 = Comparator.comparing(Employee::getAge);
//		empList.sort(comparator1);
//		empList.forEach(System.out::println);
		
		// Accepts a function that extracts a sort key from a type T, and returns a Comparator<T> that compares by that sort key using the specified Comparator
//		Comparator<Employee> comparator2 = Comparator.comparing(Employee::getAge, Comparator.reverseOrder());
//		empList.sort(comparator2);
//		empList.forEach(System.out::println);
		
//		Comparator<Employee> comparator3 = Comparator.comparing(Employee::getName, String.CASE_INSENSITIVE_ORDER);
//		empList.sort(comparator3);
//		empList.forEach(System.out::println);
//		
//		// Accepts a function that extracts an int sort key from a type T, and returns a Comparator<T> that compares by that sort key. 
//		Comparator<Employee> comparator4 = Comparator.comparingInt(Employee::getAge);
//		empList.sort(comparator4);
//		empList.forEach(System.out::println);
//		
//		List<String> names = Arrays.asList("Tara","Baby","Putty","Nanny","Babitta");
//		Comparator<String> comparator5 = Comparator.comparingInt(String::length);
//		names.sort(comparator5);
//		names.forEach(System.out::println);
//		
		// thenComparing with function as argument
//		Comparator<Employee> comparator6 = Comparator.comparing(Employee::getName).thenComparing(Employee::getAge);
//		empList.sort(comparator6);
//		empList.forEach(System.out::println);
		
		// thenComparing with Comparator as argument. other comparator to be used when this comparatorcompares two objects that are equal.
//		List<String> names = Arrays.asList("Tara","Baby","Putty","Nanny","Babitta");
//		Comparator<String> comparator8 = Comparator.comparing(String::length).thenComparing(String.CASE_INSENSITIVE_ORDER);
//		names.sort(comparator8);
//		names.forEach(System.out::println);
		
		// Returns a comparator that compares Comparable objects in naturalorder/reverseOrder. 
//		List<String> names = empList.stream().map(Employee::getName).collect(Collectors.toList());
//		//names.sort(Comparator.naturalOrder()); 
//		names.sort(Comparator.reverseOrder());
//		names.forEach(System.out::println);
		
		//nullFirst and nullLast
		//Comparator<Employee> comparator9 = Comparator.comparing(Employee::getName, Comparator.nullsFirst(String.CASE_INSENSITIVE_ORDER));
		
		// Note here method reference String::compareTo method signature matches that of Comparator compare method and hence can be used where Comparator is expected
		//Comparator<Employee> comparator9 = Comparator.comparing(e -> e.getName(), Comparator.nullsLast(String::compareTo));
		// Note that in above statement Function is actually extracting a String from Employee. Since we already have such a function in Employee class method reference can be use Employee::getName
		Comparator<Employee> comparator9 = Comparator.comparing(Employee::getName, Comparator.nullsLast(String::compareTo));
		empList.sort(comparator9);
		//empList.sort(comparator9.reversed()); // a comparator that imposes the reverse ordering of this comparator - nullLast becomes nullFirst and other names are also sorted in reverse order
		empList.forEach(System.out::println);

	}

}

class Employee {
	String name;
	Integer age;
	
	public Employee(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", age=" + age + "]";
	}
	
	
	
}
