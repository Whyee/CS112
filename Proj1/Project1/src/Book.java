
public class Book {
	//instance vars that store book info
	private String title;
	private String author;
	private int year;
	private boolean isCheckedOut;
	
	//Basic constructor, takes book info and sets isCheckedOut to false
	//in order for the book to be available.
	Book(String ti, String au, int yr)
	{
		title = ti;
		author = au;
		year = yr;
		isCheckedOut = false;
	}  //end constructor
	
	//getters
	public String getTitle()
	{
		return title;
	}  //end getTitle
	
	public String getAuthor()
	{
		return author;
	}  //end getAuthor
	
	public int getYear()
	{
		return year;
	}  //end getYear
	
	public boolean getIsCheckedOut()
	{
		return isCheckedOut;
	}  //end getIsCheckedOut
	
	//setters
	public void setTitle(String ti)
	{
		title = ti;
	}  //end setTitle
	
	public void setAuthor(String au)
	{
		author = au;
	}  //end setAuthor
	
	public void setYear(int yr)
	{
		year = yr;
	}  //end setYear
	
	public void setIsCheckedOut(boolean co)
	{
		isCheckedOut = co;
	}  //end setIsCheckedOut
	
	//toString method, returns all info about the book
	//and interprets for user the state of isCheckedOut, originally boolean,
	//to state whether or not book is checked out.
	public String toString()
	{
		String avail;
		if (isCheckedOut == false)
			avail = "Available for Checkout";
		else
			avail = "Not Available for Checkout";
				
		return title + ", " + author + ", " + year + ", " + avail;
	}  //end toString
	
	
	//checks out the book by setting isCheckedOut to true
	public void checkoutBook()
	{
		isCheckedOut = true;
	}  //end checkoutBook
	
	//returns the book by setting isCheckedOut as false
	public void returnBook()
	{
		isCheckedOut = false;
	}  //end checkoutBook
}  //end book
