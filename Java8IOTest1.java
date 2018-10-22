import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Java8IOTest1 {

	public static void main(String[] args) {
		
		// list() - Return a lazily populated Stream, the elements ofwhich are the entries in the directory. The listing is not recursive. 
		//try(Stream<Path> contentsDir = Files.list(Paths.get("C:/Java_Projects/JavaEIGHT/src"))) {
		//try(Stream<Path> contentsDir = Files.list(Paths.get("/Java_Projects","JavaEIGHT","src"))) { // Both try statements give same result
		// isExecutable - This method checks that a file existsand that this Java virtual machine has appropriate privileges to execute the file
		//try(Stream<Path> contentsDir = Files.list(Paths.get("/Java_Projects","JavaEIGHT","src")).filter(Files::isExecutable)) { // Filter out executable files
//		try(Stream<Path> contentsDir = Files.list(Paths.get("/Java_Projects","JavaEIGHT","src")).filter(t -> {
//			try {
//				return Files.isHidden(t); // show only hidden files
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//			return false;
//		})) {
		try(Stream<Path> contentsDir = Files.list(Paths.get("/Java_Projects","JavaEIGHT","src")).filter(Files::isRegularFile)) { // Show only files no dirs
			contentsDir.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			System.out.println("In useless?? finally Block");
		}
		
		// lines - Read all lines from a file as a Stream. 
		// onClose - Returns an equivalent stream with an additional close handler. Closehandlers are run when the close() methodis called on the stream, and are executed in the order they wereadded
		try(Stream<String> lines = Files.lines(Paths.get("C:\\Users\\Shirish\\Documents\\Personal\\coinbase1.txt")).onClose(() -> System.out.println("Stream Closed"))) {
			lines.forEach(System.out::println); // Print all lines in a file
			
			// Once terminal operation is executed stream can not be reused
			// For instance below findFirst logic has to be commented out because findFirst is a terminal operation same as forEach in above line.
			// Once forEach is executed in above line, stream can not be resused, since it is closed
			
//			Optional<String> entry = lines.filter(s -> s.contains("LTC")).findFirst();
//			if(entry.isPresent()) {
//				System.out.println(entry); // print only line having LTC
//			}else {
//				System.out.println("Not Found");
//			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
