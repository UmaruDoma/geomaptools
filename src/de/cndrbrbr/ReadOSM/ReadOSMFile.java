package de.cndrbrbr.ReadOSM;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Map;

public class ReadOSMFile {

	private String filenameandpath;
	private FileInputStream fstream;
	BufferedReader br;
	
	public ReadOSMFile(String filenameandpath) {
		super();
		this.filenameandpath = filenameandpath;
	}
	

	public String readFirstLine()
	{
		try {
			// Open the file
			fstream = new FileInputStream(filenameandpath);
			br = new BufferedReader(new InputStreamReader(fstream));
	
			String line;
	
			
			if ((line = br.readLine()) != null)   {
			  return (line);
			}
			else {
				fstream.close();//Close the input stream
				return null;				
			}			
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public String readNextLine()
	{
		try {
			String line;
			if ((line = br.readLine()) != null)   {
			  return (line);
			}
			else {
				fstream.close();//Close the input stream
				return null;				
			}			

		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	private boolean readtest() 
	{
	        try {
		        RandomAccessFile the_file = new RandomAccessFile(filenameandpath, "r");

		        FileChannel file_channel = the_file.getChannel();

		        System.out.println("File size is: " + file_channel.size());

		        ByteBuffer buffer = ByteBuffer.allocate((int) file_channel.size());
				file_channel.read(buffer);
		        buffer.flip();
		        System.out.println("Reading content and printing ... ");

		        for (int i = 0; i < file_channel.size(); i++) {
		            System.out.print((char) buffer.get());
		        }
				file_channel.close();
				the_file.close();
				return true;
			} catch (IOException e1) {
				
				e1.printStackTrace();
				return false;
			}

      


	    }

}
	
	

