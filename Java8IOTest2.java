import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/* Return a Stream that is lazily populated with Path by searching for files in a file tree rooted at a given startingfile.*/

/*Parameters:start - the starting file
maxDepth - the maximum number of directory levels to search
matcher - the function used to decide whether a file should be includedin the returned stream
options - options to configure the traversal*/

public class Java8IOTest2 {

	public static void main(String[] args) {

		Path path = Paths.get("C:\\Users\\Shirish\\Documents\\Personal");
		try (Stream<Path> fileNames =  Files.find(path, 2, (filePath,BasicFileAttributes) -> String.valueOf(filePath).endsWith(".txt"))){
			fileNames.map(p -> p.getFileName()).forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			List<String> allFileLines = Files.readAllLines(Paths.get("C:\\Users\\Shirish\\Documents\\Personal\\coinbase1.txt"));
			//Files.readAllLines(Paths.get("C:\\Users\\Shirish\\Documents\\Personal\\coinbase1.txt"), Charset.forName("UTF-8"));
			allFileLines.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//try(BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("C:\\Users\\Shirish\\Documents\\Personal\\coinbase1.txt"))) {
		try(BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("C:\\Users\\Shirish\\Documents\\Personal\\coinbase1.txt"),Charset.forName("UTF-8"))) {	
			bufferedReader.lines().forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}	

	}

}
