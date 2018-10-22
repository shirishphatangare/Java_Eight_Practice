
public class AbtractClassTest extends TestDefaultMethod11{

	public static void main(String[] args) {
		AbtractClassTest test = new AbtractClassTest();
		test.abstractMethod();
		test.defaultMethod();
	}

	@Override
	public void abstractMethod() { // For Abstract method with same signature, there is no issue because this method must be implemented in a class
		System.out.println("Inside Implemented abstract method..");
	}


}



abstract class TestDefaultMethod11{
	public abstract void abstractMethod();
	public void defaultMethod() {
		System.out.println("Inside Default Method of TestDefaultMethod1 interface..");
	}
	
}



