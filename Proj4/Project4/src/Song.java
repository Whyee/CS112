import java.lang.Comparable;
import java.io.File;

public class Song implements Comparable<Song>{
	private String name, artist, album, filePath;
	private File songFile; 
	
	Song(String n, String a, String al, String f, File sf)
	{
		name = n;
		artist = a;
		album = al;
		filePath = f;
		songFile = sf;
	}
	
	//getters
	public String getName()
	{
		return name;
	}
	
	public String getArtist()
	{
		return artist;
	}
	
	public String getFilePath()
	{
		return filePath;
	}
	
	public String getAlbum()
	{
		return album;
	}
	
	public File getFile()
	{
		return songFile;
	}
	
	//setters
	public void setName(String n)
	{
		name = n;
	}
	
	public void setArtist(String a)
	{
		artist = a;
	}
	
	public void setAlbum(String al)
	{
		album = al;
	}
	
	public void setFile(String f, File sf)
	{
		filePath = f;
		songFile = sf;
	}
	
	@Override
	public String toString()
	{
		return name + " " + artist + " " + filePath;
	}
	
	@Override
	public int compareTo(Song other)
	{
		//compares the names alphabetically
		if (name.compareToIgnoreCase(other.getName()) > 0)
		{
			return 1;
		}
		
		else if(name.compareToIgnoreCase(other.getName()) < 0)
		{
			return -1;
		}
		
		return 0;
	}
}
