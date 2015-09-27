import java.util.Calendar;

public class Post implements Comparable<Post> {
	private String author;
	private String text;
	private static Calendar cal;
	private long timeAtPost;
	
	Post(String text, String a)
	{
		author = a;
		this.text = text;
		cal = Calendar.getInstance();  //returns a Calendar object with current date/time
		timeAtPost = cal.getTimeInMillis();
		
	}  //end constructor
	
	public String getAuthor()
	{
		return author;
	}  //end getAuthor
	
	public long getTime()
	{
		return timeAtPost;
	}  //end getTime
	
	@Override
	public String toString()
	{
		return text;
	}  //end toString
	
	@Override
	public int compareTo(Post otherPost)
	{
		if(timeAtPost == otherPost.getTime())
			return 0;
		
		else if (timeAtPost > otherPost.getTime())
			return -1;
		
		return 1;
	}  //end compareTo
}  //end Post
