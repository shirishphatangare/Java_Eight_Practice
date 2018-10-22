import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;


public class ConcurrentHashMapJava8Search {

	public static void main(String[] args) {
		ConcurrentHashMap<String, List<String>> concurrentHashMap = new ConcurrentHashMap<>();
		
		concurrentHashMap.put("esakal", Arrays.asList("Bjp wins in pune", "Tiger witnessed", "politian arrested"));
		concurrentHashMap.put("timesofindia", Arrays.asList("buses on fire in pune", "Heavy snowfall in Kashmir"));
		concurrentHashMap.put("Mumbai mirror", Arrays.asList("corrupt politian dead", "Agriculture no rains"));
		concurrentHashMap.put("aaj tak", Arrays.asList("scoold built", "girls wins award", "India wins cup"));
		concurrentHashMap.put("India Today", Arrays.asList("Congress wins in mumbai", "Lion witnessed", "politian murdered"));

		// Returns a non-null result from applying the given search function on each (key, value), or null if none. Upon success, 
		// further element processing is suppressed and the results of any other parallel invocations of the search function are ignored.
		concurrentHashMap.search(3, (k,v) -> {
			if(k.equals("timesofindia")){  
				System.out.println(v);
				return v;
			}else{
				return null; // If the current key-value pair doesn't qualify, the provided function needs to return null to make the search continue.
			}	
		});
		
		String newsPaperName = concurrentHashMap.searchKeys(2, k -> {
			if(k.contains("Mumbai")){
				return k;
			}else{
				return null;
			}
		});
		
		System.out.println(newsPaperName);
		
		List<String> articles = concurrentHashMap.searchValues(1, v -> {
			if(v.stream().anyMatch(s -> s.contains("pune"))){
				return v;
			}else{
				return null;
			}
		});
		
		System.out.println(articles);
		
		Entry<String,List<String>> entry =  concurrentHashMap.searchEntries(2, e -> {
			String key = e.getKey(); 
			if(key.startsWith("a")){
				return e;
			}else{
				return null;
			}
		});
		
		System.out.println(entry.getKey() + "**" + entry.getValue());
	}

}
