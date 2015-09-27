
public class Driver {
	public static void main( String[] args )
	{
		//creates a catalog object to store the book info and adds books from file
		LibraryCatalog catalog = new LibraryCatalog();
		catalog.addBooksFromFile("bookList.txt");
		
		//creates a UI object and runs the mainLoop method to begin user experience
		UserInterface ui = new UserInterface();
		ui.mainLoop(catalog);
		
	}  //end main
}  //end Driver
