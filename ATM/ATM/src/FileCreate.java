import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileCreate {

	public static void createFile(String name, String cardnum,
			double cbal, double sbal) throws IOException {
		String s = "";
		Path file = Paths.get("Log.txt");

		File myFile = new File("Log.txt");
		
		if (!myFile.exists()) {
			try {
				myFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Scanner input;

		try {
			input = new Scanner(myFile);
			//System.out.println(input);
			while (input.hasNext()) {
				s += input.nextLine();
				s += "\n";
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		s += "\n";
		
		
		final int SIZE = s.length();
		FileChannel fc = null;
		String deliminator = ", ";
		
		try {
		    fc = (FileChannel)Files.newByteChannel(file, READ, WRITE); 
			s += name + deliminator + cardnum + deliminator + 
					cbal + deliminator + sbal + System.getProperty("line.separator"); 
			s += "\n";
			byte[] data = s.getBytes();
			ByteBuffer buffer = ByteBuffer.wrap(data);
			fc.write(buffer);
			fc.close();
		} catch (Exception e){
		   System.out.println("Error message: " + e);
		}
	
	
	}
	
}
