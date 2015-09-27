import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Person extends Profile {
	private ArrayList<Post> Statuses;
	private ArrayList<String> Friends;
	
	Person (String n, String img, String status, ArrayList<String> f)
	{
		super(n, img);
		super.type = "Person";
		Statuses = new ArrayList<Post>();
		Statuses.add(new Post(status, name));
		Friends = f;
	}  //end constructor
	
	//getters
	public String getFirstStatus()
	{
		Collections.sort(Statuses);
		return Statuses.get(0).toString();
	}  //end getFirstStatus
	
	public String friendsAsString()
	{
		String fullList = "";
		for(int i = 0; i < Friends.size(); i++)
		{
			if(i == Friends.size() - 1)
				fullList+= Friends.get(i);
			else
				fullList += Friends.get(i) + ", ";
		}
		
		return fullList;
		
	}  //end friendsAsString
	
	public Iterator<String> getFriends()
	{
		return Friends.iterator();
	}  //end getFriends
	
	public Iterator<Post> getStatuses()
	{
		return Statuses.iterator();
	}  //end getStatuses
	
	//setters/adders
	
	public void addFriend(String name)
	{
		if (!Friends.contains(name))
			Friends.add(name);
	}  //end addFriend
	
	public void addStatus(String s)
	{
		Statuses.add(new Post(s, name));
	}  //end addStatus
}  //end Person
