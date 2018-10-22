import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;


public class Base64EncodingDecodingTest {

	public static void main(String[] args) throws IOException {
		String str1 = "This is Java 8";
		String str2 = "Hello G⌡unte▬yÉ";
		
		System.out.println("Basic Encoding");
		basicEncodingAndDecoding(str1);
		basicEncodingAndDecoding(str2);
		
		System.out.println("------------------------------");
		System.out.println("MIME Encoding");
		mimeEncodingAndDecoding(str1);
		mimeEncodingAndDecoding(str2);

		System.out.println("------------------------------");
		System.out.println("URL Encoding");
		urlEncodingAndDecoding(str1);
		urlEncodingAndDecoding(str2);
		
		encodeAndWriteToFile("India is a beautiful country!");
		readFromFileAndDecode();
	}
	
	private static void basicEncodingAndDecoding(String str) throws UnsupportedEncodingException {
		System.out.println("Original Text: " + str);
		// Returns a Encoder that encodes using the Basic type base64 encoding scheme
		String encodedString = Base64.getEncoder().encodeToString(str.getBytes("UTF-8")); // getBytes - Encodes this String into a sequence of bytes using the given charset (here UTF-8), storing the result into a new byte array.
		System.out.println("Encoded Text: " + encodedString);
		
		byte[]decodedByteArr = Base64.getDecoder().decode(encodedString);
		String decodedString = new String(decodedByteArr,"UTF-8");
		System.out.println("Decoded Text: " + decodedString);
	}
	
	
	private static void mimeEncodingAndDecoding(String str) throws UnsupportedEncodingException {
		System.out.println("Original Text: " + str);
		// Returns a Encoder that encodes using the MIME type base64 encoding scheme
		String encodedString = Base64.getMimeEncoder().encodeToString(str.getBytes("UTF-8")); // getBytes - Encodes this String into a sequence of bytes using the given charset (here UTF-8), storing the result into a new byte array.
		System.out.println("Encoded Text: " + encodedString);
		
		byte[]decodedByteArr = Base64.getMimeDecoder().decode(encodedString);
		String decodedString = new String(decodedByteArr,"UTF-8");
		System.out.println("Decoded Text: " + decodedString);
	}
	
	private static void urlEncodingAndDecoding(String urlText) throws UnsupportedEncodingException {
		System.out.println("Original Text: " + urlText);
		// Returns a Encoder that encodes using the URL and Filename safe type base64 encoding 
		String encodedString = Base64.getUrlEncoder().encodeToString(urlText.getBytes("UTF-8")); // getBytes - Encodes this String into a sequence of bytes using the given charset (here UTF-8), storing the result into a new byte array.
		System.out.println("Encoded Text: " + encodedString);
		
		byte[]decodedByteArr = Base64.getUrlDecoder().decode(encodedString);
		String decodedString = new String(decodedByteArr,"UTF-8");
		System.out.println("Decoded Text: " + decodedString);
	}
	
	// Java 8 offers the possibility to directly encode strings and store them in a file using wrap() of the base64 encoders
	
	private static void encodeAndWriteToFile(String str) throws IOException {
		OutputStream outputStream = new FileOutputStream(new File("C:\\temp\\929354\\base64File.txt"));
		OutputStream wrappedOS = Base64.getEncoder().wrap(outputStream);
		byte [] temp = str.getBytes("UTF-8");
		System.out.println("Original String encoded and written to File: " + new String(temp,"UTF-8"));
		wrappedOS.write(temp);
	}
	
	// Read decode strings contained in a file using the wrap() method of the decoders.
	private static void readFromFileAndDecode() throws IOException {
		InputStream inputStream = new FileInputStream(new File("C:\\temp\\929354\\base64File.txt"));
		InputStream wrappediStream = Base64.getDecoder().wrap(inputStream);
		byte[] buffer = new byte[100];
		int length;
		while((length = wrappediStream.read(buffer)) != -1){
			System.out.println(new String(buffer,0,length,"UTF-8")); // Last 2 characters missing. Looks like API bug!!
		}
	}
	

}
