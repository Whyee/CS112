import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//imports needed packages

public class LibraryCatalog {
	private ArrayList<Book> books;
		
	//constructor, creates the ArrayList
	LibraryCatalog()
	{
		books = new ArrayList<Book>();
	}  //end LibraryCatalog
	
	//adds books to the ArrayList
	public void add(Book book)
	{
		books.add(book);
	}  //end add
	
	//searches for a book in the ArrayList
	//returns the book object if exists, returns null otherwise
	public Book getBook(String title)
	{
		for (int i = 0; i < books.size(); i++)  //iterating over the members of books
		{
			if (title.equals(books.get(i).getTitle()))  //if the book exists in the ArrayList
			{
				return books.get(i);  //returns the book object
			}  //end if
			
		}  //end loop
		return null;  //if the book does not exist
			
	}  //end getBook
	
	
	//checks out the book by searching for book
	//and then checking out using class Book method checkoutBook()
	public boolean checkoutBook(String title)
	{
		Book search = getBook(title);
		if (search == null)
			return false;  //returns false immediately if book is not found
		
		else
		{
			if(search.getIsCheckedOut() == false)
			{
				search.checkoutBook();
				return true;
			}  //end isCheckedOut check
			
		}  //end check if book exists
		
		return false;  //if search or checkout checks aren't successful
		

	}  //end checkoutBook
	
	//returns the book by searching for title
	//and checking in the book using class Book returnBook()
	public boolean returnBook(String title)
	{
		Book search = getBook(title);
		
		if (search == null)
			return false;  //returns false immediately if book is not found
		
		else
		{
			if (search.getIsCheckedOut() == true)
			{
				search.returnBook();
				return true;
			}  // end isCheckedOut check
		}  //end book existence check
		
		return false;  //if either of the above checks don't work
	}  //end returnBook
	
	//takes a text file and adds the books into the ArrayList
	public void addBooksFromFile(String filename)
	{
		String line;
		String title;
		String author;
		int year;
		
		//try and catch blocks to process IOExceptions when file isn't found, can't read from it, etc
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(filename));  //buffered reader and fileReader objects
		
			while((line = br.readLine()) != null )  //while lines remain in the file
			{
				String[] words = line.split("/");  //splits the line using "/" as a delimiter
				//takes each split piece and saves to vars
				title = words[0];  
				author = words[1];
				year = Integer.parseInt(words[2]);  //has to convert String value to int
				
				books.add(new Book(title, author, year));  //adds it to books
			
			}  //end while
		}  //end try
		
		catch (IOException e)  //if exception occurs, error message
		{
			System.out.println("Error, can't read from file.");
		}  //end catch
	}  //end addBooksFromFile
	
	//prints the books in ArrayList that are avail for checkout
	public void printAvailableBooks()
	{
		int size = books.size();
		int counter = 0;
		
		for (int i = 0; i < size; i++)  //iterates over the ArrayList and checks for availability
		{
			if (books.get(i).getIsCheckedOut() == false)
			{
				System.out.println(books.get(i));
				counter ++;  //counter goes up to override (no books avail) statement
			}
		}  //end loop
		
		//in case all books are checked out, a message is still printed
		if (counter == 0)
			System.out.println("No books available.");
	}  //end printAvailableBooks
	
}  //end LibraryCatalog
