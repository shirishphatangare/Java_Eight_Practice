import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;


public class ParallelStreamsTest {

	public static void main(String[] args) {
		
		IntStream stream1 = IntStream.range(5, 15);
		System.out.println("Parallel Stream is: ");
		stream1.parallel().forEach(System.out::println); // Parallel stream is not printed in order
		
		IntStream stream2 = IntStream.range(5, 15);
		System.out.println("Sequential Stream is: ");
		stream2.sequential().forEach(System.out::println); // sequential stream is printed in order
		
		
		Random random = new Random();
		List<Integer> numbers = new ArrayList<>();
		for(int i=1;i<1_000;i++){
			numbers.add(random.nextInt(1000));
		}
		
		long startTime = System.currentTimeMillis();
		int result = 0;
		for(int n : numbers){
			result += f(n);
		}
		System.out.println(result);
		long endTime = System.currentTimeMillis();
		
		System.out.println("NORMAL Approach - Time taken in millisecs.." + (endTime - startTime));
		
		// Normal Stream use only one processor core
		long startTime1 = System.currentTimeMillis();
		System.out.println(numbers.stream().map( i -> f(i)).reduce((a,b) -> a+b).get());
		long endTime1 = System.currentTimeMillis();
		System.out.println("STREAM Approach - Time taken in millisecs.." + (endTime1 - startTime1));
		
		// ParallelStream makes use of Multiple cores of processors
		long startTime2 = System.currentTimeMillis();
		System.out.println(numbers.parallelStream().map( i -> f(i)).reduce((a,b) -> a+b).get());
		long endTime2 = System.currentTimeMillis();
		System.out.println("PARALLEL STREAM Approach - Time taken in millisecs.." + (endTime2 - startTime2));
	}
	
	private static int f(int n){
		int k = 0;
		for(int i=1; i<10_00_000; i++){
			k = n + i;
		}
		return k;
	}
}
