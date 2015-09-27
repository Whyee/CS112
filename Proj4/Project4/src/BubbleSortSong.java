import java.util.ArrayList;

public class BubbleSortSong {
	public static ArrayList<Song> sortSongs(ArrayList<Song> sortArr, int option)
	{
		
		for (int k = 0; k < sortArr.size(); k++)
		{
			for (int i = 0; i < (sortArr.size() - (k+1)); i++)
			{
				Song curr = sortArr.get(i);
				if(option == 1)
				{
					//title sorting option
					
					if(curr.compareTo(sortArr.get(i+1)) > 0)
					{
						//switches the two songs' order in the list if the title starts
						//with a letter later in the alphabet
						sortArr.set(i, sortArr.get(i+1));
						sortArr.set(i+1, curr);
					}
				}
				
				if (option == 2)
				{
					//artist sorting option
					String artist = curr.getArtist();
					if (artist.compareToIgnoreCase(sortArr.get(i+1).getArtist()) > 0)
					{
						//switches in the same manner as above, but with artist name
						sortArr.set(i, sortArr.get(i+1));
						sortArr.set(i+1, curr);
					}
				}
				
			}
		}
		
		return sortArr;
	}
}
