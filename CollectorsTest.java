import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;



/* Class Collectors in Java 8 have methods which perform various useful reduction operations, such as accumulating elements into collections, summarizing elements according to various criteria, etc. 
 * Most common use of returned Collector is in  Stream.collect(Collector)
 */


public class CollectorsTest {
	private static final int PASS_THRESHOLD = 40;
	
	public static void main(String[] args) {
		List<Student> students = new ArrayList<Student>(); 
		students.add(new Student(65,"John","Computer",5300,25));
		students.add(new Student(34,"Tom","Mechanical",5060,21));
		students.add(new Student(15,"Donald","Electrical",2000,22));
		students.add(new Student(54,"Bishop","Civil",3040,21));
		students.add(new Student(72,"Lara","Mechanical",1040,22));
		students.add(new Student(25,"Ken","Computer",2030,24));
		students.add(new Student(75,"Josh","Electrical",2030,26));
		students.add(new Student(35,"Emma","Electrical",3000,20));
		students.add(new Student(56,"Don","Electrical",5600,23));
		
		// Accumulate names into a List
		// Stream.Collect function Performs a mutable reduction operation on the elements of this stream using a Collector - Terminal Operation
		// Returns a Collector that accumulates the input elements into a new List. There are no guarantees on the type, mutability, 
		// serializability, or thread-safety of the List returned; if more control over the returned List is required, use toCollection(Supplier).
		List<String> studentNames =  students.stream().map(Student::getName).collect(Collectors.toList());
		System.out.println(studentNames);
		
		// Accumulate names into a TreeSet using toCollection(Supplier) instead of toSet()
		Set<String> uniqueSortedNames = students.stream().map(Student::getName).collect(Collectors.toCollection(TreeSet::new));
		System.out.println(uniqueSortedNames);
		
		// Convert elements to strings and concatenate them, separated by Colon
		String studentJoinedNames = students.stream().map(Student::toString).collect(Collectors.joining(":"));
		System.out.println(studentJoinedNames);
		
		// Compute sum of salaries of students
		int totalSalary = students.stream().collect(Collectors.summingInt(Student::getSalary));
		System.out.println("Total Salary of all students: " + totalSalary);
		
		Map<String,List<Student>> groupedByDept = students.stream().collect(Collectors.groupingBy(Student::getDepartment));
		
		groupedByDept.forEach((k,v) -> {
			System.out.print("Department: " + k);
			System.out.println(" - Students: " + v);
		});
		
		// Compute sum of salaries by department
		Map<String,Integer> salarysumByDept = students.stream().collect(Collectors.groupingBy(Student::getDepartment,Collectors.summingInt(Student::getSalary)));
		
		salarysumByDept.forEach((k,v) -> {
			System.out.print("Department: " + k);
			System.out.println(" - Total Salary: " + v);
		});
		
		// Partition students into passing and failing
		
		Map<Boolean,List<Student>> passFailPartition = students.stream().collect(Collectors.partitioningBy( s -> s.getGrade() >= PASS_THRESHOLD));
		
		passFailPartition.forEach((k,v) -> {
			if(k){
				System.out.print("Passed students: ");
				System.out.println(v);
			}else{
				System.out.print("Failed students: ");
				System.out.println(v);
			}
		});
		
		// Partition students into passing and failing department wise
		
		Map<String,Map<Boolean,List<Student>>> passFailPartitionByDept = students.stream().collect(Collectors.groupingBy(Student::getDepartment,Collectors.partitioningBy((Student s) -> s.getGrade() >= PASS_THRESHOLD)));
		System.out.println(passFailPartitionByDept);
		
		// Count number of students in each department
		Map<String, Long> countByDept = students.stream().collect(Collectors.groupingBy(Student::getDepartment,Collectors.counting()));
		
		countByDept.forEach( (k,v) -> {
			System.out.println("Department - " + k);
			System.out.println("Number Of Students - " + v);
		});
		
		// Display average age of students in a School
		double avgAge = students.stream().collect(Collectors.averagingInt(Student::getAge));
		System.out.printf("average age of students in school: %.2f", avgAge);
		System.out.println();
		
		// Display average age of students in a School - department wise
		 Map<String, Double> agebyDept = students.stream().collect(Collectors.groupingBy(Student::getDepartment,Collectors.averagingInt(Student::getAge)));
		 
		 agebyDept.forEach( (k,v) -> {
				System.out.println("Department - " + k);
				System.out.println("avg Age Of Students - " + v);
		});
	}

}

class Student {
	int grade;
	String name;
	String department;
	int salary;
	int age;
	
	public Student(){
		
	}
	
	public Student(int grade, String name, String department, int salary,int age) {
		super();
		this.grade = grade;
		this.name = name;
		this.department = department;
		this.salary = salary;
		this.age = age;
	}
	
	public static String extractName(Student student){
		return student.getName();
	}
	
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", department=" + department
				+ ", age=" + age + "]";
	}
	
	
}
