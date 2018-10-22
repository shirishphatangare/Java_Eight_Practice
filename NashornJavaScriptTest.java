import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


/* In Java 8 old JavaScript engine "rhino" is replaced by new Engine "nashorn" */
/* using jjs.exe under bin directory js can be executed on cmd prompt */


public class NashornJavaScriptTest {

	public static void main(String[] args) throws ScriptException, FileNotFoundException, NoSuchMethodException {
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		ScriptEngine engine = scriptEngineManager.getEngineByName("nashorn");
		// inline execution - Executes the specified script. The default ScriptContext for the ScriptEngine is used
		System.out.println("Inline Script Execution");
		engine.eval("var a = 50; var b = 50; var x = myFunction(a, b); function myFunction(p1, p2) {return p1 * p2;} print(x);");
		
		// Execute js from file
		System.out.println("Script Execution from file");
		engine.eval(new FileReader("C:\\temp\\929354\\demo.js"));
		
		Invocable invocable = (Invocable) engine;
		System.out.println("Function Execution from script file");
		Object result = invocable.invokeFunction("myFunction", 33,33);
		System.out.println(result);
		
	}

}