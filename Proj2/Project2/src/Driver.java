
public class Driver {
	public static void main( String[] args )
	{
		//-------------------------------------------------------------------------------------
		//this main function creates everything needed to run the pet finder text-based version
		//-------------------------------------------------------------------------------------
		
		//shelter database and adding the data
		ShelterDatabase sd = new ShelterDatabase();
		sd.addDataFromFile("animalShelters.txt");
		
		//pet database and adding the data + matching with shelter
		PetDatabase pd = new PetDatabase();
		pd.addDataFromFile("petsFile.txt", sd);
		
		//initializing the user interface where searches can be made
		UserInterface.mainLoop(pd);
	}  //end main
}  //end driver
