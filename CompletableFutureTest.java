import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* CompletableFuture implementation using chained method calls */
/* Main Thread is not blocked */

public class CompletableFutureTest {
	public static void main(String[] args) {
		// 10 independent flows are executed asynchronously (without blocking) main thread or each other 
		for(int i=0 ; i<10; i++) {
			// First operation has to be Async always and then same Thread is used for subsequent operations
			CompletableFuture.supplyAsync(() -> chainedMethod1()) // Execute chainedMethod1 Asynchronously and give output to chainedMethod2 as input
			.thenApply(message -> chainedMethod2(message))
			.exceptionally(e -> failedMethod(e)) // catch exception from any of the previous statements and pass new failed message to next statements using exceptionally
			.thenApply(message -> chainedMethod3(message))
			.thenAccept(message -> chainedMethod4(message)); // Returns a new CompletionStage that, when this stage completesnormally, is executed with this stage's result as the argumentto the supplied action.
		}
		// thenAccept has Consumer as input parameter whereas thenApply has Function as a input parameter
		
		System.out.println("*************************************************");
		
		for(int i=0 ; i<10; i++) {
			// First operation has to be Async always and then different Thread (from same ThreadPool) is used for subsequent operations with thenApplyAsync method
			CompletableFuture.supplyAsync(() -> chainedMethod1()) // Execute chainedMethod1 Asynchronously and give output to chainedMethod2 as input
			.thenApplyAsync(message -> chainedMethod2(message)) // New Thread from same ThreadPool
			.thenApplyAsync(message -> chainedMethod3(message)) // New Thread from same ThreadPool
			.thenAcceptAsync(message -> chainedMethod4(message)); // New Thread from same ThreadPool
		}
		System.out.println("*************************************************");
		
		ExecutorService service1 = Executors.newFixedThreadPool(10);
		ExecutorService service2 = Executors.newCachedThreadPool();
		for(int i=0 ; i<10; i++) {
			// First operation has to be Async always and then different Thread (from different ThreadPool) is used for subsequent operations with thenApplyAsync method
			CompletableFuture.supplyAsync(() -> chainedMethod1()) //Use Common Thread pool - ForkJoinPool.commonPool
			.thenApplyAsync(message -> chainedMethod2(message),service1) // New Thread from different ThreadPool supplied as argument
			.thenApplyAsync(message -> chainedMethod3(message),service2) // New Thread from different ThreadPool supplied as argument
			.thenAcceptAsync(message -> chainedMethod4(message),service2); // New Thread from different ThreadPool supplied as argument
		}
		
		try {
			service1.shutdown();
			service1.awaitTermination(3000, TimeUnit.MILLISECONDS);
			service2.shutdown();
			service2.awaitTermination(3000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static String chainedMethod1() {
		return "Method1";
	}

	private static String chainedMethod2(String message) {
		//throw new NullPointerException();
		return message + " Method2";
	}

	private static String chainedMethod3(String message) {
		return message + " Method3";
	}
	
	private static void chainedMethod4(String message) {
		System.out.println(message + " Inside Method4");
	}
	
	private static String failedMethod(Throwable e) {
		return "Failed Message! " + e;
	}
}
