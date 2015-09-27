import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Thread;

import javazoom.jl.player.Player;
import javazoom.jl.decoder.JavaLayerException;

public class PlayerThread extends Thread {
	private Player pl;
	/*
	 * A background thread for playing audio files.
	 * Creates a player object based on the given filePath.
	 * run() plays the audio file.
	 */
	PlayerThread(String filePath)
	{
		try{
			FileInputStream fileIS = new FileInputStream(new File(filePath));
		
			try{
				pl = new Player(fileIS);
			}
			
			catch (JavaLayerException e)
			{
				System.out.println("Unable to play audio file.");
				e.printStackTrace();
			}
		}
		
		catch (FileNotFoundException e)
		{
			System.out.println("File not found.");
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		try {
			pl.play();
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
