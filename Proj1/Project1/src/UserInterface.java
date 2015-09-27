import java.util.Scanner;  //imports the scanner to collect user inputs
import java.lang.NumberFormatException;;  //imports to catch exception

public class UserInterface {
	//runs all back-end methods of catalog interface, brings all the other classes together
	//and pushes up to Driver for clean user experience.
	public void mainLoop(LibraryCatalog bc)
	{
		int option = 0;  //sets an option var
		Scanner input = new Scanner(System.in);  //creates the scanner input catcher
		input.useDelimiter("\n");  //sets the scanner to break inputs after enter is pressed,
		//not after a space (default)
		
		while (option != 4)  //will break after 4 is entered
		{
			//presents the options to the user
			System.out.printf("Choose an option: \nEnter 1 to check out a book. \nEnter 2 to return a book.\n");
			System.out.printf("Enter 3 to print the list of available books. \nEnter 4 to exit.\n");
			
			try
			{
				option = Integer.parseInt(input.next());  //collects the input
			}
			
			catch (NumberFormatException e)  //catches if a number was not entered as option, prints error message and prompts again.
			{
				System.out.println("Data entered was not a numerical option number, please enter a number from the options above.");
				System.out.print("Choose another option: ");
				option = Integer.parseInt(input.next());
				
			}
			switch (option)  //executes certain blocks of code depending on option number
			{
			case 1:  //book checkout option
				boolean result1;
				System.out.println("Book Checkout");
				System.out.print("Enter the Book title: ");
				String title1 = input.next();  //takes the title
				result1 = bc.checkoutBook(title1);  //checks out the title (this method executes a search for the book too)
				
				//checks return result of the method for success or not
				if (result1 == true)
					System.out.printf("%s successfully checked out.\n", title1);
				
				else
				{
					//if failed, error message
					System.out.println("Error checking out book, it either does not exist or has been checked out.");
					System.out.println("Please try again.");
				}
				break;  //stops remaining code in switch from executing
			
			case 2:   //book return method
				boolean result2;
				System.out.println("Book Return");
				System.out.print("Enter the Book title: ");
				String title2 = input.next();  //takes the title
				result2 = bc.returnBook(title2);  //returns the book
				
				//checks for and tells user result, like in option 1
				if (result2 == true)
					System.out.printf("%s successfully returned.\n", title2);
				
				else
				{
					//if failed, error message
					System.out.println("Error returning book, it either does not exist or has already been returned.");
					System.out.println("Press 3 for available books and try again.");
				}
				
				break;
				
			case 3:  //available book display method
				bc.printAvailableBooks();  //calls appropriate method in the catalog object
				break;
				
			case 4:  //breaks out of switch to then stop the next iteration of while and end the program
				break;
				
			default:  //in case any number other than defined is entered.
				System.out.println("System does not recognize the option you entered.");
				System.out.println("Please try again with an option 1-4:");
			}  //end switch
			
		}  //end while
	}  //end mainLoop
}  //end UserInterface
