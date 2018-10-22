import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

/*1) forEach() is a terminal operation, which means once calling forEach() method on stream, you cannot call another method. It will result in a runtime exception.
2) When you call forEach() on parallel stream, the order of iteration is not guaranteed, but you can ensure that ordering by calling forEachOrdered() method.
3) There are two forEach() method in Java 8, one defined inside Iterable and other inside java.util.stream.Stream class. If purpose of forEach() is just iteration then you can directly call it e.g. list.forEach() or set.forEach() but if you want to perform some operations e.g. filter or map then better first get the stream and then perform that operation and finally call forEach() method.
4) Use of forEach() results in readable and cleaner code.*/


public class ForEachTest {

	public static void main(String[] args) {
		// foreach Performs the given action for each element of the Iterable until all elements have been processed or the action throws an exception. 
		// Exceptions thrown by the action are relayed to the caller.
		// Implementing Iterable interface allows an object to be the target of the "for-each loop" statement. Almost all Collections types implement iterable
		// foreach accepts a Consumer action as a parameter
		
		System.out.println("ArrayList Entries ");
		List<String> names1 = Arrays.asList("AA","CC","RR","SS","TT","AA","SS");
		names1.forEach(s -> System.out.println(s));
		
		System.out.println("HashSet Entries ");
		HashSet<String> names2 = new HashSet<>(names1);
		names2.forEach(s -> System.out.println(s));
		
		System.out.println("HashMap Entries ");
		HashMap<String,String> names3 = new HashMap<>();
		
		names3.put("Maharashtra", "Mumbai");
		names3.put("Karnataka", "Bangalore");
		names3.put("MadhyaPradesh", "Bhopal");
		names3.put("Gujrat", "Gandhinagar");
		names3.put("Kerala", "Kochi");
		
		names3.forEach((k,v) -> {
			System.out.println("Key: " + k);
			System.out.println("Value: " + v);
		});
		
		// foreach with Streams
		System.out.println("Sequential Stream Entries ");
		Stream<String> streams = Stream.of("AAA","VVV","FFF","RRR");
		streams.forEach(s -> System.out.println(s));
		
		System.out.println("Parallel Stream Entries (Order not guaranteed) ");
		names1.parallelStream().forEach(s -> System.out.println(s));
		
		System.out.println("Parallel Stream Entries (Order guaranteed) ");
		names1.parallelStream().forEachOrdered(s -> System.out.println(s));
		
		// forEach as a terminal operation on Stream after performing some operations like Sorting,filter,map,reduce etc.
		System.out.println("Stream sorted in reverse order ");
		names1.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
		System.out.println("Stream filtred for SS ");
		names1.stream().filter(s -> s.contains("SS")).forEach(System.out::println);
	}

}
