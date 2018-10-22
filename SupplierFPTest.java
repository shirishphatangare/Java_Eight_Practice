import java.util.Random;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;


/*T get(): This abstract method does not accept any argument but instead returns newly generated values, T, in the stream. 
 But there is no requirement that new or distinct results be returned each time the supplier is invoked.*/

public class SupplierFPTest {
	public static void main(String[] args) {
		Random random = new Random(); 
		Supplier<Integer> randomSupplier = () -> random.nextInt(100);
		System.out.println("Supplying 10 random integers..");
		for(int i=0;i<10;i++){
			System.out.println(randomSupplier.get());
		}
		
		// primitive specializations of the Supplier interface
		IntSupplier intSupplier = () ->  random.nextInt(10);
		System.out.println(intSupplier.getAsInt());
		
		LongSupplier longSupplier = () -> random.nextLong();
		System.out.println(longSupplier.getAsLong());
		
		DoubleSupplier doubleSupplier = () -> random.nextDouble();
		System.out.println(doubleSupplier.getAsDouble());
		
		BooleanSupplier booleanSupplier = () -> random.nextBoolean();
		System.out.println(booleanSupplier.getAsBoolean());
	}
}
