import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;


public class Library {
	private ArrayList<Song> songs;
	
	Library()
	{
		songs = new ArrayList<Song>();
	}
	
	public boolean inputFromDT(String directory)
	{
		songs.clear();
		//uses DirectoryTraverser to return a hashmap of mp3s
		boolean result = DirectoryTraverser.traverseMP3(directory, songs);
		return result;
	}
	/*
	public ArrayList<Song> search(String s)
	{
		// TODO: Input binary search algorithm 
		
	}
	*/
	
	public int getSize()
	{
		//returns the size of the arrayList
		return songs.size();
	}
	
	public void saveLibrary(File file)
	{
		//uses LibraryWriter class to write the current library to a file
		LibraryWriter.writeToFile(file, songs);
	}
	
	public String getFileAtIndex(int i)
	{
		//returns the file path of the song at specified index
		Song s = songs.get(i);
		return s.getFilePath();
	}
	
	public void sortSongsTitle()
	{
		//uses the sorting class to sort by title
		songs = BubbleSortSong.sortSongs(songs, 1);
	}
	
	public void sortSongsArtist()
	{
		//uses the sorting class to sort by Artist
		songs = BubbleSortSong.sortSongs(songs, 2);
	}
	
	public Iterator<Song> libraryIterator()
	{
		//returns an iterator to access the songs in this Library
		Iterator<Song> it = songs.iterator();
		return it;
	}
	
	public void loadLibrary(File f)
	{
		songs.clear();  //clears the library ArrayList
		
		Scanner sc;
		try {
			sc = new Scanner(f);
			int numSongs = Integer.parseInt(sc.nextLine());  //gets the number of songs
			
			for (int i = 0; i < numSongs; i++)
			{
				String title;
				String artist;
				String album;
				String audioF;
				
				title = sc.nextLine();
				artist = sc.nextLine();
				album = sc.nextLine();
				audioF = sc.nextLine();
				
				songs.add(new Song(title, artist, album, audioF, new File(audioF)));
				
				if (i != numSongs - 1)
					sc.nextLine();  //accounts for the empty line btwn each song
			}
		} 
		
		catch (IOException e) {
			System.out.println("Error reading file.");
			e.printStackTrace();
		}
		
	}
	
	
}
