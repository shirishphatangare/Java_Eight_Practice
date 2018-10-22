import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/* Operations on Stream are lazily evaluated. Intermediate operations will be be performed until there is a terminal operation.
 * Once terminal operation on a stream is called, we will get illegalStateException, if we try to operate on that stream again.
 */

public class StreamsAPITest {

	public static void main(String[] args) {
		List<String> brands = Arrays.asList("G56", "G34", "I90", "Y76","G23","T56","G66", "G78","O90","g35");

//		List<String> gNumbers = new ArrayList<>();
//		brands.forEach(str -> { 
//			if(str.toUpperCase.startsWith("G")){
//				gNumbers.add(str);
//				//System.out.println(str);
//			}
//		});
//		gNumbers.sort((s1,s2) -> s1.compareTo(s2));
//		gNumbers.forEach(str -> System.out.println(str));
		
		// Above commented code can be succinctly written using stream API as below
		brands
		.stream()
		.map(String::toUpperCase)
		.filter(str -> str.startsWith("G"))
		.sorted((s1,s2) -> s1.compareTo(s2))// Intermediate operations returns Stream
		.forEach(System.out::println); // method references - alternative to Lamda // Terminal Operation returns void
		
		// Create Stream another approach
		
		Stream<String> ioNumbers = Stream.of("I88","O20","I34","I56","O90","I63","O12");
		Stream<String> inNumbers = Stream.of("N28","N21","I34","I56","N09","I63","N12");
		Stream<String> concatenatedStream = Stream.concat(ioNumbers,inNumbers);
		
		System.out.println(
		concatenatedStream
		.distinct()
		.sorted()
		.peek(e -> System.out.println("Sorted distinct value " + e)) // display intermediate results from sorted
		.count());
		
		// flatmap Method
		CompanyEmployee emp1 = new CompanyEmployee(23,"Ramesh Narsimha");
		CompanyEmployee emp2 = new CompanyEmployee(53,"Prakash Sinha");
		CompanyEmployee emp3 = new CompanyEmployee(33,"Rajesh Balu");
		CompanyEmployee emp4 = new CompanyEmployee(33,"Adi Srivastava");
		CompanyEmployee emp5 = new CompanyEmployee(33,"Shirish Prabhu");

		Department hrDepartment = new Department("HR");
		hrDepartment.addEmployee(emp1);
		hrDepartment.addEmployee(emp2);
		hrDepartment.addEmployee(emp3);
		
		Department accountDepartment = new Department("Accounting");
		accountDepartment.addEmployee(emp4);
		accountDepartment.addEmployee(emp5);
		
		List<Department> departments = new ArrayList<>();
		departments.add(hrDepartment);
		departments.add(accountDepartment);
		
		departments
		.stream()
		.flatMap(department -> department.getEmployees().stream()) // returns a stream consisting of employee streams of each department
		.forEach(System.out::println); // printing all employees under each department in departments List
		
		// Terminal function Collect
		System.out.println("Storing Stream results in a List and printing it..");
		
		List<String> storeCollectionResults = 
		brands
		.stream()
		.map(String::toUpperCase)
		.filter(str -> str.startsWith("G"))
		.sorted((s1,s2) -> s1.compareTo(s2))
		.collect(Collectors.toList());
		
		System.out.println(storeCollectionResults);
		
		// Collectors.groupingBy Method
		Map<Integer, List<CompanyEmployee>> employeesGroupByAge =  
		departments
		.stream()
		.flatMap(department -> department.getEmployees().stream())
		.collect(Collectors.groupingBy(emp -> emp.getAge()));
		
		System.out.println(employeesGroupByAge);
		
		// Using reduce to print youngest employee name
		departments
		.stream()
		.flatMap(department -> department.getEmployees().stream())
		.reduce((e1,e2) -> e1.getAge()<e2.getAge() ? e1 : e2) // reduce returns Optional<CompanyEmployee> type hence we are using ifPresent. This is a terminal operation
		.ifPresent(System.out::println); // using ifPresent because previous operation returns an Optional class. If a CompanyEmployee is present, print it, otherwise do nothing.
		
		
		// Specific Stream interfaces 
		System.out.println(
		IntStream.of(12,43,12,45,66,43,23)
		.filter(arg -> arg < 50)
		.sorted()
		.sum()); // Returns the sum of elements in this stream. This is a special case of a reduction and is equivalent to: return reduce(0, Integer::sum); This is a terminal operation.

		// Similarly there are interfaces like LongStream, DoubleStream etc.
	}
}

class CompanyEmployee {
	
	int age;
	String name;
	
	public CompanyEmployee(int age, String name) {
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

	@Override
	public String toString() {
		return "CompanyEmployee [name=" + name + "]";
	}
	
	
}

class Department {
	String name;
	List<CompanyEmployee> employees = new ArrayList<>();
	
	public Department(String name) {
		super();
		this.name = name;
	}

	public void addEmployee(CompanyEmployee e){
		employees.add(e);
	}
	
	public List<CompanyEmployee> getEmployees(){
		return employees;
	}
	
	
}
