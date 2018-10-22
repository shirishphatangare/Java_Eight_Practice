import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/* StringJoiner is a class in java.util package which is used to construct a sequence of 
 * characters(strings) separated by a delimiter and optionally starting with a 
 * supplied prefix and ending with a supplied suffix. Though this can also be with the help of 
 * StringBuilder class to append delimiter after each string, but StringJoiner provides easy way to do that without much code to write.
 */

public class StringJoinerTest {

	public static void main(String[] args) {
		List<String> names = new ArrayList<>();
		names.add("Alice");
		names.add("Bob");
		names.add("Marry");
		names.add("Ed");
		
		StringJoiner joiner1 = new StringJoiner(",");
		// setEmptyValue() - Sets the sequence of characters to be used when determining the string representation of this StringJoiner and no elements have beenadded yet, that is, when it is empty. 
		joiner1.setEmptyValue("EMPTY");
		System.out.println(joiner1); // prints "EMPTY"
		joiner1.add(names.get(0)).add(names.get(1)); // add() - Adds a copy of the given CharSequence value as the next element of the StringJoiner value. If newElement is null, then "null" is added.
		System.out.println(joiner1);
		
		StringJoiner joiner2 = new StringJoiner(":");
		System.out.println(joiner2); // empty not set hence prints a blank line
		joiner2.add(names.get(2)).add(names.get(3));
		System.out.println(joiner2);
		
		System.out.println(joiner1.merge(joiner2)); // merge() - Adds the contents of the given StringJoiner without prefix and suffix as the next element if it is non-empty. If the given StringJoiner is empty, the call has no effect.
		
		StringJoiner joiner3 = new StringJoiner(":","PRE "," SUF");
		joiner3.add(names.get(1)).add(names.get(3));
		System.out.println(joiner3);
		
		System.out.println(joiner1.merge(joiner3)); // notice joiner3 is merged to joiner1 without prefix and suffix
	}

}
