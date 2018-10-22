import java.util.Optional;

/* Optional class Do not have accessible constructors. */

public class OptionalClassTest {

	public static void main(String[] args) {
		String str1 = "India is Great";
		String str2 = null;
		
		// Returns an empty Optional instance. No value is present for thisOptional.
		Optional<String> optional1 = Optional.empty();
		System.out.println("Printing empty optional: " + optional1);
		
		// Returns an Optional describing the specified value, if non-null,otherwise returns an empty Optional.
		Optional<String> optional2  = Optional.ofNullable(str1);
		

		if(optional2.isPresent()) { // isPresent() -  Return true if there is a value present, otherwise false.
			// get() - If a value is present in this Optional, returns the value,otherwise throws NoSuchElementException.
			System.out.println("Value is Not null: " + optional2.get());
		}else {
			System.out.println("Value is NULL");
		}
		
		optional2.ifPresent( s -> System.out.println("Invoked via Consumer argument of ifPresent: " + s)); //ifPresent() - If a value is present, invoke the specified consumer with the value,otherwise do nothing.
		
		Optional<String> optional3  = Optional.ofNullable(str2);
		
		if(optional3.isPresent()) { // isPresent() -  Return true if there is a value present, otherwise false.
			// get() - If a value is present in this Optional, returns the value,otherwise throws NoSuchElementException.
			System.out.println("Value is Not null: " + optional3.get());
		}else {
			System.out.println("Value is NULL");
		}
		
		System.out.println("Return a value if present in optional2 otherwise return default value mentioned in parameter of orElse method: " + optional2.orElse("default value"));
		System.out.println("Return a value if present in optional3 otherwise return default value mentioned in parameter of orElse method: " + optional3.orElse("default value"));
		
		// Return the value if present, otherwise invoke supplier lamda and return the result of that invocation.
		System.out.println(optional2.orElseGet(() -> "default from supplier Lamda"));
		System.out.println(optional3.orElseGet(() -> "default from supplier Lamda"));
		
		// Return the contained value, if present, otherwise throw an exception to be created by the provided supplier.
		System.out.println(optional2.orElseThrow(IllegalStateException::new));
		//System.out.println(optional3.orElseThrow(IllegalStateException::new));
		
		// filter() - If a value is present, and the value matches the given predicate,return an Optional describing the value, otherwise return an empty Optional.
		
		System.out.println(optional2.filter(s -> s.contains("is")));
		System.out.println(optional2.filter(s -> s.contains("was")));
		
		
		// map() - If a value is present, apply the provided mapping function to it,and if the result is non-null, return an Optional describing the result. Otherwise return an empty Optional.
		System.out.println(optional2.map(s -> s.toUpperCase()));
		System.out.println(optional3.map(s -> s.toUpperCase()));
		
		Optional<String> optional4 = Optional.of(str1); // returns non-empty optional with value of str1
		System.out.println("Non-empty Optional: " + optional4.get());

		//Optional<String> optional5 = Optional.of(str2); // NullPointerException since str2 is null
		
		// Instances of Optional class are Considered equal solely based on equals(), not based on reference equality(==).
		/* Indicates whether some other object is "equal to" this Optional. The other object is considered equal if: 
		* 1) It is also an Optional and; 
		* 2) Both instances have no value present or; 
        * 3) The present values are "equal to" each other via equals(). */ 

		System.out.println("Is equal: " + optional2.equals(str1)); // false
		
		String str3 = "India is Great";
		
		Optional<String> optional6 = Optional.ofNullable("India is Great"); 
		Optional<String> optional7 = Optional.ofNullable(str3); 
		
		System.out.println("Is equal: " + optional6.equals(optional2)); // true
		System.out.println("Is equal: " + optional7.equals(optional2)); // true
		
		Optional<String> optional8 = Optional.empty();
		System.out.println("Is equal: " + optional8.equals(optional1)); // true
		
	}

}
