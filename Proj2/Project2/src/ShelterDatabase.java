import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//imports needed packages

public class ShelterDatabase {
	//framework for a Database of shelters, using ArrayLists
	private ArrayList<Shelter> sd;
	
	ShelterDatabase()
	{
		sd = new ArrayList<Shelter>();
	}  //end constructor
	
	public Shelter findShelter(String shelterName)
	{
		//search function that looks for the shelter whose name is input
		for (int i = 0; i < sd.size(); i++)
		{
			if ( shelterName.equals( sd.get(i).getName() ))
			{
				return sd.get(i);
			}
		}  //end loop
		
		return null;  //returns null if no shelter could be found under name
	}  //end findShelter
	
	public void addDataFromFile(String file)
	{
		//reads shelter data from file and adds information to the ArrayList of shelters
		try
		{
			//try block, is able to catch IOException errors (catch block) without stopping execution
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			
			while( (line = br.readLine()) != null )
			{
				String[] words = line.split("; ");  //splits the line from file in components
				//saves each piece of data into an appropriately named var
				String name = words[0];
				String [] address = words[1].split(", ");
				String street = address[0];
				String city = address[1];
				String state = address[2];
				int zip = Integer.parseInt(address[3]);
				String phone = words[2];
				
				sd.add(new Shelter(name, street, city, state, zip, phone));  //adds info into the ArrayList as a Shelter object
			}  //end while
		}  //end try
		
		catch (IOException e)
		{
			//prints error message if error occurs
			System.out.println("Shelter Database: Couldn't read from file.");
		}  //end catch
	}  //end addDataFromFile
	
	public void printData()
	{
		//prints all the data in the shelter file
		for (int i = 0; i < sd.size(); i++)
		{
			System.out.println(sd.get(i));
		}
	}
}  //end ShelterDatabase
