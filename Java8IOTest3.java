import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Java8IOTest3 {

	public static void main(String[] args) {
		
		Path path = Paths.get("C:\\Users\\Shirish\\Documents\\Personal\\sample.txt");
		String fileContents = "India vs pakistan cricket match";
		List<String> names = Arrays.asList("shirish","vitthal","phatangare");

		/* Writes bytes to a file. The options parameter specifies how the file is created or opened. 
		 * If no options are present then this method works as if the CREATE, TRUNCATE_EXISTING, and WRITE 
		 * options are present. In other words, it opens the file for writing, 
		 * creating the file if it doesn't exist, orinitially truncating an existing regular-file toa size of 0 
		 */
		
		try {
			Path write1 = Files.write(path, fileContents.getBytes());
			System.out.println(write1.getFileName());
			// names is a List which implements Iterable interface
			Path write2 = Files.write(path, names);
			System.out.println(write2.toUri().getPath());

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Path start = Paths.get("C:\\\\Users\\\\Shirish\\\\Documents\\\\Personal");
		try(Stream<Path> links = Files.walk(start, FileVisitOption.FOLLOW_LINKS)) {  // Follow symbolic links
			links.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		System.out.println("-----------------------------------------------------");
		// maxDepth - Integer.MAX_VALUE can be used to indicate all the paths
		// Return a Stream that is lazily populated with Path by walking the file tree rooted at a given starting file
		try(Stream<Path> links = Files.walk(start,1, FileVisitOption.FOLLOW_LINKS)) {  // Follow symbolic links
			links.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
