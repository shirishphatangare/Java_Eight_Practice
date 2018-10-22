import java.util.concurrent.ConcurrentHashMap;


public class ConcurrentHashMapJava8ForEach {

	public static void main(String[] args) {
		ConcurrentHashMap<String,String> conHashMap = new ConcurrentHashMap<>();
		ConcurrentHashMapJava8ForEach thisObject = new ConcurrentHashMapJava8ForEach();
		
		conHashMap.put("Gujarat", "Gandhinagar");
		conHashMap.put("Goa", "Panaji");
		conHashMap.put("Jharkhand", "Ranchi");
		conHashMap.put("Kerala", "Thiruvananthapuram");
		conHashMap.put("Tamil Nadu", "Chennai");
		conHashMap.put("Karnataka", "Bengaluru");
		conHashMap.put("Meghalaya", "Shillong");
		conHashMap.put("Punjab", "Chandigarh");
		conHashMap.put("Uttarakhand", "Dehradun");
		conHashMap.put("Delhi", "Delhi");
		
		// Performs the given action for each entry in this map until all entries have been processed or the action throws an exception. Uses single thread of execution. No Parallel Threads are used
		conHashMap.forEach((k,v) -> {
			System.out.println("Inside " + Thread.currentThread().getName() + " Thread");
			System.out.println(k + "-" + v);
		});
		
		System.out.println("-------------------------------------------");
		// parallelismThreshold the (estimated) number of elements needed for this operation to be executed in parallel
		conHashMap.forEach(3, (k,v) -> {
			System.out.println("Inside " + Thread.currentThread().getName() + " Thread");
			System.out.println(k + "-" + v);
		});
		
		// map.forEach(4, this::someHeavyOperation); //  someHeavyOperation method will run in parallel when the size of the map reaches the parallelism threshold being 4.  
		
		System.out.println("-------------------------------------------");
		
		// The transformer transforms the data before sending it to the Consumer — pretty much like executing a map function on the key-value pair before passing it along.
		// Transformer Performs the given action for each non-null transformation of each (key, value).
		conHashMap.forEach(3, thisObject::convertToUpperCase, item -> {
			System.out.println("Inside " + Thread.currentThread().getName() + " Thread");
			System.out.println(item);
		});
		
		System.out.println("-------------------------------------------");
		
		conHashMap.forEachKey(3, k -> System.out.println("Printing all keys " + k)); // normal version
		
		System.out.println("-------------------------------------------");
		
		conHashMap.forEachKey(3, thisObject::convertToUpperCase, System.out::println); // Transformer version
		
		System.out.println("-------------------------------------------");
		
		conHashMap.forEachValue(3, k -> System.out.println("Printing all values " + k)); // normal version
		
		System.out.println("-------------------------------------------");
		
		conHashMap.forEachValue(3, thisObject::convertToUpperCase, System.out::println); // Transformer version
		
		System.out.println("-------------------------------------------");
		
		// Similarly eachEntry versions exists
		//conHashMap.forEachEntry(parallelismThreshold, action);
		//conHashMap.forEachEntry(parallelismThreshold, transformer, action);
	}
	
	private String convertToUpperCase(String key, String value){
		return key.toUpperCase().concat(" ").concat(value.toUpperCase());
	}
	
	private String convertToUpperCase(String key){
		return key.toUpperCase();
	}

}
