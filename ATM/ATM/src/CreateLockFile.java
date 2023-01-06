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

public class CreateLockFile {

	public static void creatingFile(String cardnum, boolean status) {
		String writeInFile = "";
		Path file =
		         Paths.get("Lock.txt");
		
		
		File yourFile = new File("Lock.txt");
		
		try {
			yourFile.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Scanner input;
		try {
			input = new Scanner(yourFile);
			while(input.hasNext())
			{
				writeInFile +=input.nextLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FileChannel fc = null;
		String delimiter = ", ";
		
		try
	      {
	         fc = (FileChannel)Files.newByteChannel(file, READ, WRITE); 
	         writeInFile += cardnum+delimiter+status+System.getProperty("line.separator");
	         writeInFile += "\n";
	         byte[] data = writeInFile.getBytes();
	         ByteBuffer buffer = ByteBuffer.wrap(data);
	         fc.write(buffer);
	         fc.close();
	      }
	      catch (Exception e)
	      {
	          System.out.println("Error message: " + e);
	      }
		
	}
}
