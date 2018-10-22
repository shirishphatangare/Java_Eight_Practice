import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureBlockingProblem {
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(10);
		
		Future<Integer>[] futures = new Future[5]; 
		for(int i=0;i<5;i++) {
			// Submit task to executor service and accept a placeholder object for return value 
			futures[i] = service.submit(new Task());
		}
		
		
		// If get method for futures 1 is blocked to get the result, other futures are blocked too ,even though they might already have a value in placeholder
		// This happens due to blocking (sync) nature of get method in Future class.
		// Solution for this problem is to use CompletableFuture
		for(int i=0;i<5;i++) {
			try {
				// Get task return value
				// Main thread blocks here in get method if return value is not yet ready in placeholder (future)
				Integer result = futures[i].get(); // Blocking operation i.e Synchronous
				System.out.println("Result of Task " + i + " is: " + result );
			}catch(InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		service.shutdown();
	}
}

class Task implements Callable<Integer>{
	@Override
	public Integer call() throws Exception {
		Random random = new Random();
		Thread.sleep(4000);
		return random.nextInt();
	}
}
