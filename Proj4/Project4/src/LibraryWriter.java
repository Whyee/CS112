import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class LibraryWriter {
	public static void writeToFile(File file, ArrayList<Song> songs)
	{
		//writes the given song information into a file
		PrintWriter printWriter;
		
		try {
			printWriter = new PrintWriter(file);
			printWriter.println(songs.size());
			
			for (int i = 0; i < songs.size(); i++)
			{
				Song s = songs.get(i);
				printWriter.println(s.getName());
				printWriter.println(s.getArtist());
				printWriter.println(s.getAlbum());
				printWriter.println(s.getFilePath());
				
				if ( !(i == (songs.size() - 1)) )
					printWriter.println();
			}	
			
			if (printWriter != null)
				printWriter.close();
		}
		
		catch (FileNotFoundException e)
		{
			System.out.println("File not found.");
			e.printStackTrace();
		}
	}
}
