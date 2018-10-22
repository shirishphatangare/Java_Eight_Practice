import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/* CompletableFuture class implements Future interface and CompletionStage interface. 
 * CompletionStage interface defines contract for an async computation step that can be combined with other steps.
 * In short, CompletableFuture is a framework which provides different methods for composing, combining, executing async computational steps (completion logic) and handling errors. 
 *  
 */


public class CompletableFutureTest1 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		CompletableFuture<String> completableFuture = joinStrings();
		System.out.println("Inside Thread " + Thread.currentThread().getName());
		System.out.println(completableFuture.get()); // Waits if necessary for this future to complete, and then returns its result.
		
		// Now  suppose you have some complex logic being executed in a function exceuteComplexLogic which returns a string - We can use completedFuture
		// completedFuture method returns a new CompletableFuture that is already completed with the given value (return value of exceuteComplexLogic method). 
		CompletableFuture<String> completableFuture1 = CompletableFuture.completedFuture(exceuteComplexLogic());
		System.out.println("Printing value of completedFuture - " + completableFuture1.get());
		System.out.println("----------------------------------------------------------");
		callAsyncMethods();
		callthenApply_thenAccept();
		
		// Ability to combine CompletableFuture instances in a chain of computation steps. Result of chaining itself is a CompletableFuture which allows further chaining and combining. This is feature of functional languages
		combineComputableFutures();
		
		// run Multiple Futures in parallel
		callParallelFuturesExecution();
		
		// Handling exception using handle method
		handleException();
	}
	
	private static String exceuteComplexLogic(){
		return "Hello World!!";
	}
	
	private static CompletableFuture<String> joinStrings(){
		CompletableFuture<String> completableFuture = new CompletableFuture<>();
		
		List<String> words = new ArrayList<>();
		words.add("KK");
		words.add("MM");
		words.add("NN");
		
		// Remember main purpose of future or CompletableFuture is to store a result obtained from a different thread.
		// Here result form new Thread of execution is stored in joinedStr and returned in main method using get() method
		
		ExecutorService service = Executors.newCachedThreadPool();
		
		service.submit( () -> {
			System.out.println("Inside Thread " + Thread.currentThread().getName());
			try{
				String joinedStr = String.join(",", words);
				
				// If not already completed, completes this CompletableFuture with a CancellationException.
				// set mayInterruptIfRunning to true 
				//completableFuture.cancel(true);
				
				// Complete method - if not already completed, sets the value returned by get() and related methods to the given value (Here - joinedStr).
				// Returns a boolean value - true or false. true if this invocation caused this CompletableFuture to transition to a completed state, else false
				completableFuture.complete(joinedStr);
			}catch(Exception e){
				e.printStackTrace();
			}
		});
		service.shutdown();
		return completableFuture;
	}
	
	/*
	 * static methods runAsync and supplyAsync allow us to create a CompletableFuture instance out of Runnable and Supplier Functional Interfaces.
	 * Runnable does not allow to return a value, whereas Supplier returns a value.
	 */
	
	private static void callAsyncMethods() throws InterruptedException, ExecutionException{
		// Runnable Task will be executed in thread from a default Thread pool (commonpool)
		CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync( () -> System.out.println("Testing CompletableFuture.runAsync method with a Runnable parameter") );
		completableFuture1.get();
		ExecutorService service1 =  Executors.newCachedThreadPool();
		// Runnable Task will be executed in thread from a custom Thread pool "service1" created in statement above
		CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync( () -> System.out.println("Testing CompletableFuture.runAsync method a Runnable and Executor parameters"), service1);
		completableFuture2.get();

		System.out.println("----------------------------------------------------------");
		// Supplier Task will be executed in thread from a default Thread pool (ForkJoinPool.CommonPool())
		CompletableFuture<String> completableFuture3 = CompletableFuture.supplyAsync( () -> "Testing CompletableFuture.supplyAsync method with a Supplier parameter");
		System.out.println(completableFuture3.get());

		ExecutorService service2 =  Executors.newCachedThreadPool();
		// Supplier Task will be executed in thread from a custom Thread pool "service2" created in statement above
		CompletableFuture<String> completableFuture4 = CompletableFuture.supplyAsync( () -> "Testing CompletableFuture.supplyAsync method with a Supplier and Executor parameters", service2);
		System.out.println(completableFuture4.get());
		
		service1.shutdown();
		//service1.awaitTermination(3000, TimeUnit.MILLISECONDS);

		service2.shutdown();
		//service2.awaitTermination(3000, TimeUnit.MILLISECONDS);
	}
	
	
	// Processing results of asynchronous operations using CompletableFuture thenApply and thenAccept methods
	private static void callthenApply_thenAccept() throws InterruptedException, ExecutionException {
		// --------------CASE 1 - take a parameter as result of previous operation and return String ---------------------
		
		CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> "India is ");
		CompletableFuture<String> completableFuture2 = completableFuture1.thenApply( s -> s+"a beautiful Country.");
		String result = completableFuture2.get();
		System.out.println(result);
		
		// above functionality can be succinctly written as below
		System.out.println(CompletableFuture.supplyAsync(() -> "India is ").thenApply(s -> s+"a nice Country.").get());
		// thenApply is synchronous i.e executed in same thread after main operation.
		// There are 2 a synchronous variants of thenApply - thenApplyAsync(Function) and thenApplyAsync(Function,Executor)
		
		// Difference between thenApply and thenAccept
		// 1) thenApply accepts a function argument and returns CompletableFuture<T>. thenAccept accepts a Consumer and returns CompletableFuture<Void>.
		// 2) thenApply has 3 variants. thenAccept has 6 variants

		// --------------CASE 2 - Take a parameter as result of previous operation and return nothing <void> ---------------------
		CompletableFuture<String> completableFuture3 = CompletableFuture.supplyAsync(() -> "India is ");
		CompletableFuture<Void> completableFuture4 = completableFuture3.thenAccept(s -> System.out.println(s+"a big Country."));
		completableFuture4.get();
		
		// above functionality can be succinctly written as below
		CompletableFuture.supplyAsync(() -> "India is ").thenAccept(s -> System.out.println(s+"a big Country.")).get();
		
		// --------------CASE 2 - Neither take a parameter as result of previous operation nor return anything ---------------------
		// thenRun accepts Runnable as parameter and has 3 variants - thenRun(Runnable) thenRunAsync(Runnable) and thenRunAsync(Runnable,Executor)
		CompletableFuture<String> completableFuture5 = CompletableFuture.supplyAsync(() -> "India has good cricket team");
		CompletableFuture<Void> completableFuture6 = completableFuture5.thenRun( () -> System.out.println("India pakistan matches are great to watch"));
		completableFuture6.get();
	}
	
	
	// Combine computable futures using thenCompose, thenCombine and thenAcceptBoth
	private static void combineComputableFutures() throws InterruptedException, ExecutionException{
		// thenCompose takes Function as an argument and returns new CompletionStage of type CompletableFuture<T>
		// thenCompose Returns a new CompletionStage that, when this stage completes normally, is executed with this stage as the argument to the supplied function.
		// thenCompose has 3 variants - thenCompose(Function), thenComposeAsync(Function), thenComposeAsync(Function,Executor)
		CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> "Hello ").thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " world!"));
		String result1 = completableFuture1.get();
		System.out.println(result1);
		
		// thenCombine takes CompletionStage and BiFunction as parameters
		// thenCombine Returns a new CompletionStage that, when this and the other given stage both complete normally, is executed with the wo results as arguments to the supplied function
		// thenCombine has 3 variants - thenCombine(CompletionStage,BiFunction),thenCombineAsync(CompletionStage,BiFunction) and thenCombineAsync(CompletionStage,BiFunction,Executor)
		CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> "Hello ").thenCombine(CompletableFuture.supplyAsync(() -> " World!!"), (s1,s2) -> s1+s2);
		String result2 = completableFuture2.get();
		System.out.println(result2);
		
		// thenAcceptBoth Returns a new CompletionStage that, when this and the other given stage both complete normally, is executed with the two results as arguments to the supplied action.
		// thenAcceptBoth has 3 variants thenAcceptBoth(CompletionStage,BiConsumer), thenAcceptBothAsync(CompletionStage,BiConsumer) a thenAcceptBothAsync(CompletionStage,BiConsumer,Executor)
		CompletableFuture<Void> completableFuture3 = CompletableFuture.supplyAsync(() -> "Hello ").thenAcceptBoth(CompletableFuture.supplyAsync(() -> " World!!!"), (s1,s2) -> System.out.println(s1+s2));
		completableFuture3.get();
	}
	
	private static void callParallelFuturesExecution() throws InterruptedException, ExecutionException{
		CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> "Syntel ");
		CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> "is now ");
		CompletableFuture<String> completableFuture3 = CompletableFuture.supplyAsync(() -> "Atos syntel");
		
		/*
		 * Returns a new CompletableFuture that is completed when all of the given CompletableFutures complete. If any of the given CompletableFutures complete exceptionally, 
		 * then the returned CompletableFuture also does so, with a CompletionException holding this exception as its cause. 
		 * Otherwise, the results, if any, of the given CompletableFutures are not reflected in the returned CompletableFuture, but may be obtained by inspecting them individually.
		 */
		//Among the applications of this method is to await completion of a set of independent CompletableFutures before continuing a program
		CompletableFuture<Void> completableFuture4 = CompletableFuture.allOf(completableFuture1,completableFuture2,completableFuture3);
		
		//get method returns null
		System.out.println(completableFuture4.get());
		
		System.out.println("isCancelled - " + completableFuture1.isCancelled()); //Returns true if this CompletableFuture was cancelled before it completed normally.
		System.out.println("isCompletedExceptionally - " + completableFuture1.isCompletedExceptionally()); // Returns true if this CompletableFuture completed exceptionally, in any way. Possible causes include cancellation, explicit invocation of completeExceptionally, and abrupt termination of a CompletionStage action.
		System.out.println("isDone - " + completableFuture1.isDone());      // Returns true if completed in any fashion: normally, exceptionally, or via cancellation.
		
		// To get individual results from each CompletableFuture
		System.out.println(completableFuture1.get());
		
		//To get Combined result
		//Collectors.joining - Returns a Collector that concatenates the input elements into a String, in encounter order.
		String combinedResult = Stream.of(completableFuture1,completableFuture2,completableFuture3).map(CompletableFuture::join).collect(Collectors.joining());
		System.out.println(combinedResult);
		
		/*Returns a new CompletableFuture that is completed when any of the given CompletableFutures complete, with the same result. 
		Otherwise, if it completed exceptionally, the returned CompletableFuture also does so, with a CompletionException holding this exception as its cause.*/ 
		
		CompletableFuture<Object> completableFuture5 = CompletableFuture.anyOf(completableFuture1,completableFuture2,completableFuture3);
		String result = (String)completableFuture5.join();
		System.out.println(result);
	}
	
	
	/* catch exception in handle method - try catch is adapted for CompletableFuture
	 * handle method Returns a new CompletionStage that, when this stage completes either normally or exceptionally, is executed with this stage's result and exception as arguments to the supplied function. 
	 *  When this stage is complete, the given function is invoked with the result (or null if none) and the exception (or null if none) of this stage as arguments, and the function's result is used to complete the returned stage.
	 *  handle method has 3 variants - handle(BiFunction),handleAsync(BiFunction),handleAsync(BiFunction,Executor)
	 */
	
	private static void handleException() throws InterruptedException, ExecutionException{
		String name = "Sean";
		//String name = null;
		
		CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
			if(name == null){
				throw new RuntimeException("Invalid name");
			}else {
				return "Hello " + name;
			}
		}).handle((s,t) -> {
			if(t == null){
				return s;
			}else{
				return t.getMessage();
			}
		});
		
		System.out.println(completableFuture1.get()); // In case when name is not null. Prints "Hello Sean"
		
		
	}
}
