import java.util.Scanner;
import java.util.ArrayList;
//import needed packages

public class UserInterface {
	public static void mainLoop(PetDatabase pd)
	{
		//creates the needed scanner to read inputs and sets the delimiter to when the user presses enter
		Scanner input = new Scanner(System.in);
		input.useDelimiter("\n");
		
		//first prompt: type
		System.out.println("Enter the type of pet: Cat, Dog, Bird, or Any: ");
		String type = input.nextLine();
		
		//initializes breed to originally be an empty String, in case "Any" is selected for type
		String breed = "";
		if (! type.equals("Any"))
		{
			//if user specifies a type, those breeds are printed out
			pd.printBreeds(type);
			System.out.println();
			
			//prompts for breed after
			System.out.println("Enter a breed: ");
			breed = input.nextLine();
		}  //end "Any" type check
		
		//third prompt
		System.out.println("Enter age(young, adult, senior):");
		String age = input.nextLine();
		
		//fourth prompt
		System.out.println("Enter size (small, medium, large): ");
		String size = input.nextLine();
		
		//fifth prompt
		System.out.println("Enter a city in CA: ");
		String city = input.nextLine();
		
		//creates a result ArrayList and runs Pet Database search function on user-entered attributes
		ArrayList<Pet> results = new ArrayList<Pet>();
		results = pd.searchPets(type, breed, age, size, city);
		
		//prints message if no pets found
		if(results.size() == 0)
		{
			System.out.println("Could not find any pets.");
		}  //end if
		
		//prints results if pets found
		else
		{
			System.out.println("Found the following pets: ");
			System.out.println("--------------------------");
			
			for(int i = 0; i < results.size(); i++)
			{
				System.out.println(results.get(i));
			}  //end loop
		}  //end else
	}  //end mainLoop
}  //end UserInterface
