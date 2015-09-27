import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

public class DirectoryTraverser {
	
	public static boolean traverseMP3(String directory, ArrayList<Song> resArr) {
		/* Obtains the list of File objects in the given directory
		// Iterates over the them, 
		// for each directory found, recursively calls traverse on it
		// for each file, checks extension for ".txt" and adds a song object to resArr.
		 */
		File fileDirect = new File(directory);
		File[] files = fileDirect.listFiles();  //creates an array of file objects that are in directory
		
		for (int i = 0; i < files.length; i++)
		{
			if (files[i].isDirectory())
			{
				//calls traverse again if the item is a directory, to get all files within
				traverseMP3(directory + "/" + files[i].getName(), resArr);
			}  //end directory
			
			if (files[i].isFile())
			{
				//adds the file to the ArrayList if the file fits criteria
				if(files[i].getName().endsWith(".mp3"))
				{
					String name;
					String artist;
					String album;
					String path;
					try
					{
						AudioFile f = AudioFileIO.read(files[i]);
						Tag tag = f.getTag();
						name = tag.getFirst(FieldKey.TITLE);
						artist = tag.getFirst(FieldKey.ARTIST);
						album = tag.getFirst(FieldKey.ALBUM);
						path = files[i].getPath();
						
						resArr.add(new Song(name, artist, album, path, files[i]));
					}
					
					catch (InvalidAudioFrameException e)
					{
						System.out.println("Error Reading File");
						e.printStackTrace();
						return false;
					} 
					catch (CannotReadException e) 
					{
						System.out.println("Error Reading File");
						e.printStackTrace();
						return false;
					} 
					catch (IOException e) 
					{
						System.out.println("Error Reading File");
						e.printStackTrace();
						return false;
					} 
					catch (TagException e) 
					{
						System.out.println("Error Reading File");
						e.printStackTrace();
						return false;
					} 
					catch (ReadOnlyFileException e) 
					{
						System.out.println("Error Reading File");
						e.printStackTrace();
						return false;
					}
				}
			}  //end file Add
		}  //end recursive file adding
		return true;
	}  //end traverse
}  //end FileBrowser

