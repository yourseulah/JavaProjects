import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ReadLock {

	static String[] array = new String[2];
	static String cardNum;
	static boolean status;
	
	public static void read() {
		Path file = Paths.get("Lock.txt");
		
		String s = "";
		
		try {
			InputStream input = 
					new BufferedInputStream(Files.newInputStream(file));
			BufferedReader reader = 
					new BufferedReader(new InputStreamReader(input));
			s = reader.readLine();
			array = s.split(",");
			cardNum = array[0];
			if(cardNum != null) {
				status = Boolean.parseBoolean(array[1]);
			}
			reader.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(cardNum);
	}
}
