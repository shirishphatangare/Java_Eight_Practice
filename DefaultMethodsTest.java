
/* 
 * Default methods functionality can be achieved with abstract classes (see AbtractClassTest class) .However, default methods in interfaces have a specific purpose concerning backward compatibility. 
 * Before Java 8, interfaces could have only abstract methods. The implementation of these methods has to be provided in a separate class. 
 * So, if a new method is to be added in an interface, then its implementation code has to be provided in the class implementing the same interface. 
 * To overcome this issue, Java 8 has introduced the concept of default methods which allow the interfaces to have methods with implementation without 
 * affecting the classes that implement the interface.
 */

public class DefaultMethodsTest implements TestDefaultMethod1,TestDefaultMethod2{

	public static void main(String[] args) {
		DefaultMethodsTest test = new DefaultMethodsTest();
		test.abstractMethod();
		test.defaultMethod();
		TestDefaultMethod1.staticDefaultMethod();
	}

	@Override
	public void abstractMethod() { // For Abstract method with same signature, there is no issue because this method must be implemented in a class
		System.out.println("Inside Implemented abstract method..");
	}
	
	// To avoid multiple inheritance issue, a default method with same signature (defaultMethod) in 2 interfaces (TestDefaultMethod1,TestDefaultMethod2) implemented by this class (DefaultMethodsTest)
	
	public void defaultMethod() {
		System.out.println("This is overidden default method ");
		// use super keyword to call the defaultMethods of TestDefaultMethod1 and TestDefaultMethod2 interfaces
		TestDefaultMethod1.super.defaultMethod();
		TestDefaultMethod2.super.defaultMethod();
	}

}

/*
 * Default methods enable you to add new functionality to existing interfaces and ensure binary compatibility with code written for older versions of those interfaces. 
 * In particular, default methods enable you to add methods that accept lambda expressions as parameters to existing interfaces.
 */

interface TestDefaultMethod1{
	
	public void abstractMethod();
	default void defaultMethod() {
		System.out.println("Inside Default Method of TestDefaultMethod1 interface..");
	}
	
	static void staticDefaultMethod(){
		System.out.println("Inside Static Default Method of TestDefaultMethod1 interface..");
	}
	
}


interface TestDefaultMethod2{
	
	public void abstractMethod();
	default void defaultMethod() {
		System.out.println("Inside Default Method of TestDefaultMethod2 interface..");
	}
	
}


/* When you extend an interface that contains a default method, you can do the following:

A) Not mention the default method at all, which lets your extended interface inherit the default method.
B) Redeclare the default method, which makes it abstract.
C) Redefine the default method, which overrides it. */


