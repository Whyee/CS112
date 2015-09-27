import java.util.ArrayList;

public class LibraryCatalog {
       
       /* ArrayList to store the books in the catalog. Remember to
       initialize this array in the constructor of the class. */
       private ArrayList<Book> books;
	
       LibraryCatalog() {
      
	   // FILL IN CODE: initialize the ArrayList books here
       }
    
        /* Adds a book to the array books. */
	public void add(Book book) {
	    // FILL IN CODE

	}

       
       /* Searches for the book with the given title in the ArrayList
        * "books" and returns the book. Returns null if there is no book
        *  with this title. */
	public Book getBook(String title) {
	    // FILL IN CODE
	   
	
	}
        



      /* Checks out the book with the given title from the library.
       *  First, searches for the book with the given title, and then
       * checks it out (by calling the corresponding method in class Book)
       * if it is not currently checked out. The method returns true if it
       * was able to check out the book, otherwise false. */
       public boolean checkoutBook(String title) {

	   // FILL IN CODE
	  

       }




      /* Returns the book with the given title to the library. If the
       * book is in the ArrayList of books, has the matching title and is
       * currently checked out, the method is going to "return" it (by
       * calling the returnBook() method of class Book. */
       public boolean returnBook(String title) {
	   // FILL IN CODE
	  


       }

       
	   
       /* Reads a list of books line by line from the file and adds each
	* book to the ArrayList of books. Each line in the file corresponds
	* to one book. Book information is in the following format: name/author/year. 
	* For instance, this is a single line from that file: The Lord of the Rings/Tolkien/1986 */
        public void addBooksFromFile(String filename) {

	   // FILL IN CODE
	   // You can use BufferedReader (or Scanner) to read from the file.
	   // To split the line into individual words (title, author, year), use line.split("/"). 

	    // To convert a String into an integer for the
	    // variable "year", you can use Integer.parseInt(String s) function.
	    // You are required to catch IOException in this method
	 }




	
        // Displays a list of book titles that are available for checkout     
	public void printAvailableBooks() {
	    // FILL IN CODE

	}
}
