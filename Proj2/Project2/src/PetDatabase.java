//import all needed packages
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PetDatabase {
	//this defines the framework for an ArrayList containing Pet information and methods to work with it
	private ArrayList<Pet> pd;
	
	PetDatabase()
	{
		pd = new ArrayList<Pet>();
	}  //end constructor
	
	public void addDataFromFile( String file, ShelterDatabase sd )
	{
		//adds the data from specified filename and matches Shelter objects to the shelter mentioned in retrieved data
		try  //catches ioException if something goes wrong
		{
			//creates the buffered reader, file reader, and String var that each line of the file goes through
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			
			while ( (line = br.readLine()) != null )
			{
				boolean wasAdded = true;  //for error detection
				String[] words = line.split(", ");  //splits the line of the file into individual elements
				//takes the split elements and assigns them to vars that correspond to what they are
				String species = words[0];
				String b = words[1];
				String n = words[2];
				String s = words[3];
				String a = words[4];
				String g = words[5];
				boolean condition = Boolean.parseBoolean(words[6]);
				String c = words[7];
				String l = words[8];
				
				//finds the Shelter object in database that matches name given in file
				Shelter location = sd.findShelter(l);
				
				if( location == null )
				{
					//if no location was found by the search function
					wasAdded = false;  //error detection. Setting as False will trigger error message to be printed later.
				}  //end if
				
		
				switch (species)  //initializes an object of specific species using given data
				{
					case "Cat": pd.add(new Cat(species, b, n, s, a, g, c, location, condition));
					break;
				
					case "Dog": pd.add(new Dog(species, b, n, s, a, g, c, location, condition));
					break;
					
					case "Bird": pd.add(new Bird(species, b, n, s, a, g, c, location, condition));
					break;
					
					default: 
						wasAdded = false;  //if a species not covered is given, gives False in error msg check below
						break;
				}  //end switch
				
				if (wasAdded == false)
				{
					//if for any reason an animal was not added during this process, message printed
					System.out.println("Error in importing information, check data files.\n");
					break;
				}  //end if
				
			}  //end loop
		}  //end try
		
		catch (IOException e)
		{
			//prints a message to handle any errors that deal with IOException and could actually stop execution
			System.out.println("Pet Database: Couldn't read from file.");
		}  //end catch
	}  //end addDataFromFile
	
	public void printData()
	{
		//prints the data in the whole ArrayList
		for (int i = 0; i < pd.size(); i++)
		{
			System.out.println(pd.get(i));
		}  //end loop
	}  //end printData
	
	public ArrayList<Pet> searchPets(String type, String breed, String age, String size, String city)
	{
		//searches for pets using given parameters and returns an ArrayList of Pets
		ArrayList<Pet> results = new ArrayList<Pet>();
		for (int i = 0; i < pd.size(); i++)
		{
			//iterating over all elements of the ArrayList
			//gets all the attributes of the animal (and the animal itself)
			Pet animal = pd.get(i);
			String species = animal.getSpecies();
			String breed1 = animal.getBreed();
			String age1 = animal.getAge();
			String size1 = animal.getSize();
			String city1 = animal.getCity();
			
			//checking of the conditions provided by user match this particular animal
			//also can execute if the user did not specify anything for any given attribute
			if( (type.equals(species)|| type.equals("Any")) && (breed.equals(breed1) || breed.equals("")))
			{
				if((age.equals(age1) || age.equals("")) && (size.equals(size1) || size.equals("")))
				{
					if(city.equals(city1) || city.equals(""))
					{
						results.add(animal);
					}  //end city check
				}  //end age and size check
			}  //and species and breed check
		}  //end loop
		
		return results;
	}  //end searchPets
	
	public void printBreeds( String species )
	{
		//prints all the available breeds of a specified type of pet, watching to not repeat breeds
		ArrayList<String> printedBreeds = new ArrayList<String>();  //list that keeps the breeds already printed
		for (int i = 0; i < pd.size(); i++)
		{
			//iterating over all the elements of the Pet Database
			Pet animal = pd.get(i);  //gets the animal object
			
			if(animal.getSpecies().equals(species))
			{
				//if the species (type) is correct
				String breed = animal.getBreed();
			
				if(!printedBreeds.contains(breed))
				{
					//if the breed hasn't been printed already
					System.out.println(breed);
					printedBreeds.add(breed);
				}  //end if
			}  //end if species check
		}  //end loop
	}  //end printBreeds
}  //end PetDatabase
