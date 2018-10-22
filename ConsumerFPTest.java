import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;


/* Consumer FP has one abstract functional method accept(T t)and a default method andThen(Consumer<? super T> after) */

public class ConsumerFPTest {

	public static void main(String[] args) {
		String str1 = "Happy";
		
		Consumer<String> consumer1 = arg1 -> System.out.println("Consumer1: " + arg1);
		consumer1.accept(str1);

		Consumer<String> consumer2 = arg2 -> System.out.println("Consumer2: " + arg2);
		consumer1.andThen(consumer2).accept("TestingandThen - ");
		
		
		// primitive specializations of the Consumer interface
		
		int a = 55;
		double d = 66.55;
		long l = 1_00_000_000L;
		
		IntConsumer intConsumer = a1 -> {System.out.println("Consuming integer.." + a1);};
		intConsumer.accept(a);
		
		DoubleConsumer doubleConsumer = b1 -> {System.out.println("Consuming double.." + b1);}; 
		doubleConsumer.accept(d);
		
		LongConsumer longConsumer = c1 -> {System.out.println("Consuming long.." + c1);};
		longConsumer.accept(l);
		
		/*If performing either operation throws an exception, it is relayed to the caller of the composed operation. 
		  If performing this operation throws an exception, the after operation will not be performed.
		  If performing after operation throws an exception, the first operation will be performed.
		*/
		
		Consumer<String> consumer3 = arg3 -> System.out.println("Consumer3: " + arg3.charAt(0));
		Consumer<String> consumer4 = arg4 -> System.out.println("Consumer4: " );
		
		try{
			consumer3.andThen(consumer4).accept(null);
		}catch (Exception e){
			System.out.println(e);
		}
		
//		BiConsumer<T,U>	- Represents an operation that accepts two input arguments and returns no result.
//		ObjDoubleConsumer<T> - Represents an operation that accepts an object-valued and a double-valued argument, and returns no result.
//		ObjIntConsumer<T> - Represents an operation that accepts an object-valued and a int-valued argument, and returns no result.
//		ObjLongConsumer<T> - Represents an operation that accepts an object-valued and a long-valued argument, and returns no result.
//		
	}

}
