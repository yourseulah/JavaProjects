import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;

public class CreateFile {
	
	public void createFile(String t) throws IOException {
		Path file = Paths.get("300361747.txt");
		//Path filename = file.getFileName();
	
		//File yourfile = new File("C:\\User\\"+filename);
		//yourfile.createNewFile();
		
		//String s = "";
		//final int RESIZE = s.length();
		//String deliminator;
		
		FileChannel fc = null;
		try {
			fc = (FileChannel)Files.newByteChannel(file,CREATE,WRITE);
			byte[] data = t.getBytes();
			ByteBuffer buffer = ByteBuffer.wrap(data);
			fc.write(buffer);
			fc.close();
		} catch (Exception e)
	      {
	          System.out.println("Error message: " + e);
	      }
		
	}

}
