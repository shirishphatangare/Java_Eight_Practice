import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.function.Supplier;


/* We use lambda expressions to create anonymous methods. Sometimes, however, a lambda expression does nothing but call an existing method. 
 * In those cases, it's often clearer to refer to the existing method by name. Method references enable you to do this; they are compact, 
 * easy-to-read lambda expressions for methods that already have a name.
 */

public class MethodReferencesTest {

	public static void main(String[] args) {
		
		List<Member> members = new ArrayList<>();
		members.add(new Member("Bala", 33));
		members.add(new Member("Chela", 23));
		members.add(new Member("Nana", 37));
		members.add(new Member("Tejas", 53));
		members.add(new Member("Namo", 73));
		
		Member[] memberArray = members.toArray(new Member[members.size()]);
		
		// The method signature of this invocation of sort is the following:
		// static <T> void sort(T[] a, Comparator<? super T> c)
		
		// Approach 1 - pass new instance of MemberAgeComparator which implements Comparator interface and overrides compare method
		Arrays.sort(memberArray, new MemberAgeComparator());
		
		
		
		// Approach 2 - Use Anonymous class which implements Comparator interface and overrides compare method
		Comparator<Member> comparator = new Comparator<Member>(){ // diamond can not be used with anonymous classes
			@Override
			public int compare(Member a, Member b) {
				if(a.getAge() > b.getAge()){
					return 1;
				}else if(a.getAge() < b.getAge()){
					return -1;
				}else{
					return 0;
				}
			}
		};
				
		Arrays.sort(memberArray, comparator);
		
		
		
		// Approach 3 - Use Anonymous class directly in method
		Arrays.sort(memberArray, new Comparator<Member>(){ // diamond can not be used with anonymous classes
			@Override
			public int compare(Member a, Member b) {
				if(a.getAge() > b.getAge()){
					return 1;
				}else if(a.getAge() < b.getAge()){
					return -1;
				}else{
					return 0;
				}
			}
		});
		
		
		// Approach 4 - Use Lamda Expressions
		Arrays.sort(memberArray, (a,b) -> {
			if(a.getAge() > b.getAge()){
				return 1;
			}else if(a.getAge() < b.getAge()){
				return -1;
			}else{
				return 0;
			}
		});
		
		
		// Approach 5 - Use Lamda Expressions using existing static method (compareByAge) in Member class
		
		Arrays.sort(memberArray, (a,b) -> Member.compareByAge(a,b));
		
		
		// Approach 6 -  You can use a method reference instead of a lambda expression:
		
		//	The method reference Member::compareByAge is semantically the same as the lambda expression (a, b) -> Member.compareByAge(a, b). Each has the following characteristics:
		//		1) Its formal parameter list is copied from Comparator<Member>.compare, which is (Member, Member).
		//		2) Its body calls the method Member.compareByAge.
		
		Arrays.sort(memberArray, Member::compareByAge); // Reference to a static method
		
		for(Member m: memberArray){
			System.out.println(m);
		}
		
		System.out.println("****************************************");
		
		
		Member memberInstance = new Member();
		Arrays.sort(memberArray,memberInstance::compareByName); // Reference to an Instance Method of a Particular Object
		
		for(Member m: memberArray){
			System.out.println(m);
		}
		
		//Reference to an Instance Method of an Arbitrary Object of a Particular Type
		
		String[] stringArray = { "Barbara", "James", "Mary", "John", "Patricia", "Robert", "Michael", "Linda" };
		Arrays.sort(stringArray, String::compareToIgnoreCase);
		Arrays.sort(stringArray, (a, b) -> a.compareToIgnoreCase(b)); // Equivalent Lamda expression of above method reference
		
		//Reference to a Constructor
		List<Member> copiedList1 = transferElements(members, () -> new ArrayList<>());
		System.out.println("Copied members List (using Lamda) is as below: ");
		System.out.println(copiedList1);
		
		// Second parameter Lamda expression can be replaced with method argument as below
		List<Member> copiedList2 = transferElements(members, ArrayList::new); // Type inference - ArrayList<Member>::new - is OK too
		System.out.println("Copied members List (using Method Referecnes) is as below: ");
		System.out.println(copiedList2);
		
	}
	
	public static <T, SRC extends Collection<T>,DEST extends Collection<T>> DEST transferElements (SRC srcCollection, Supplier<DEST> destCollectionFactory){
		DEST destCollection = destCollectionFactory.get();
		srcCollection.forEach( s -> {
			destCollection.add(s);
		});
		return destCollection;
	}

}


class Member {
	
	String name;
	int age;
	
	public Member(){
		
	}
	
	public Member(String name, int age) {
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
	
	
	public int compareByName(Member a , Member b){
		return a.getName().compareTo(b.getName());
	}
	
	public static int compareByAge(Member a , Member b){
		if(a.getAge() > b.getAge()){
			return 1;
		}else if(a.getAge() < b.getAge()){
			return -1;
		}else{
			return 0;
		}
	}

	@Override
	public String toString() {
		return "Member [name=" + name + ", age=" + age + "]";
	}
	
}

class MemberAgeComparator implements Comparator<Member> {
	@Override
    public int compare(Member a, Member b) {
    	if(a.getAge() > b.getAge()){
			return 1;
		}else if(a.getAge() < b.getAge()){
			return -1;
		}else{
			return 0;
		}
    }
}


