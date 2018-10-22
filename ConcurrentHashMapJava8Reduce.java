import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


// Accumulating the data in a map. Java 8 added 19 different variants of the reducer in ConcurrentHashMap

public class ConcurrentHashMapJava8Reduce {

	public static void main(String[] args) {
		ConcurrentHashMap<String, List<String>> concurrentHashMap = new ConcurrentHashMap<>();
		
		concurrentHashMap.put("esakal", Arrays.asList("Bjp wins in pune", "Tiger witnessed", "politian arrested"));
		concurrentHashMap.put("timesofindia", Arrays.asList("buses on fire in pune", "Heavy snowfall in Kashmir"));
		concurrentHashMap.put("Mumbai mirror", Arrays.asList("corrupt politian dead", "Agriculture no rains"));
		concurrentHashMap.put("aaj tak", Arrays.asList("scoold built", "girls wins award", "India wins cup"));
		concurrentHashMap.put("India Today", Arrays.asList("Congress wins in mumbai", "Lion witnessed", "politian murdered"));
		
		int totalTilesInMap = concurrentHashMap.reduce(2, (k,v) -> v.size(), (a,b) -> a+b);
		System.out.println("Total Tiltles in a Map are: " + totalTilesInMap);
		
		// 3 variants of reduce are as below
		// concurrentHashMap.reduceToInt(parallelismThreshold, transformer, basis, reducer)
		// int java.util.concurrent.ConcurrentHashMap.reduceToInt(long parallelismThreshold, ToIntBiFunction<? super String, ? super List<String>> transformer, int basis, IntBinaryOperator reducer)

		// double java.util.concurrent.ConcurrentHashMap.reduceToDouble(long parallelismThreshold, ToDoubleBiFunction<? super String, ? super List<String>> transformer, double basis, DoubleBinaryOperator reducer)
		//concurrentHashMap.reduceToDouble(parallelismThreshold, transformer, basis, reducer);
		
		//long java.util.concurrent.ConcurrentHashMap.reduceToLong(long parallelismThreshold, ToLongBiFunction<? super String, ? super List<String>> transformer, long basis, LongBinaryOperator reducer)
		// concurrentHashMap.reduceToLong(parallelismThreshold, transformer, basis, reducer);

		
		
		// Returns the result of accumulating the given transformation of 
		// all keys using the given reducer to combine values, and the given basis as an identity value (Initial default value for reduction).
		// reduceKeysToInt returns int and use ToIntFunction as a transformer, basis a int value and IntBinaryOperator as reducer
		// int java.util.concurrent.ConcurrentHashMap.reduceKeysToInt(long parallelismThreshold, ToIntFunction<? super String> transformer, int basis, IntBinaryOperator reducer)
		
		int sumAllKeyLengths = concurrentHashMap.reduceKeysToInt(2, k -> k.length() , 0, (a,b) -> a+b); // returns a Sum of all key lengths
		System.out.println("Sum of all keys lenghts: "+ sumAllKeyLengths);
		
		// 5 variants of reduceKeys are as below
		
		// concurrentHashMap.reduceKeys(parallelismThreshold, reducer);
		// concurrentHashMap.reduceKeys(parallelismThreshold, transformer, reducer);
		// concurrentHashMap.reduceKeysToInt(parallelismThreshold, transformer, basis, reducer);
		// concurrentHashMap.reduceKeysToDouble(parallelismThreshold, transformer, basis, reducer);
		// concurrentHashMap.reduceKeysToLong(parallelismThreshold, transformer, basis, reducer);
		
		// 5 variants of reduceValues are as below
		
		//	concurrentHashMap.reduceValues(parallelismThreshold, reducer);
		//	concurrentHashMap.reduceValues(parallelismThreshold, transformer, reducer);
		//	concurrentHashMap.reduceValuesToInt(parallelismThreshold, transformer, basis, reducer);
		//	concurrentHashMap.reduceValuesToDouble(parallelismThreshold, transformer, basis, reducer);
		//	concurrentHashMap.reduceValuesToLong(parallelismThreshold, transformer, basis, reducer);
		
		// 5 variants of reduceEntries are as below
		
		//	concurrentHashMap.reduceEntries(parallelismThreshold, reducer);
		//	concurrentHashMap.reduceEntries(parallelismThreshold, transformer, reducer);
		//	concurrentHashMap.reduceEntriesToInt(parallelismThreshold, transformer, basis, reducer);
		//	concurrentHashMap.reduceEntriesToDouble(parallelismThreshold, transformer, basis, reducer);
		//	concurrentHashMap.reduceEntriesToLong(parallelismThreshold, transformer, basis, reducer);
		
		
		
		
		
		

	}

}
