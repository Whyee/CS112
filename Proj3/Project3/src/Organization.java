import java.util.*;

public class Organization extends Profile {
	private String phone;
	private String address;
	private ArrayList<Post> Announcements;
	private int likeCounter;
	private ArrayList<String> Supporters;
	
	Organization(String n, String img, String p, String a,
			String announce, int count, ArrayList<String> support)
	{
		super(n, img);
		super.type = "Organization";
		address = a;
		phone = p;
		Announcements = new ArrayList<Post>();
		Announcements.add(new Post(announce, name));
		likeCounter = count;
		Supporters = support;
	}  //end constructor
	
	//getters
	public String getPhone()
	{
		return phone;
	}  //end getPhone
	
	public String getAddress()
	{
		return address;
	}  //end getAddress
	
	public Iterator<Post> getAnnouncements()
	{
		return Announcements.iterator();
	}  //end getAnnouncements
	
	public int getLikes()
	{
		return likeCounter;
	}  //end getLikes
	
	public String supportersAsString()
	{
		String fullList = "";
		for (int i = 0; i < Supporters.size(); i++)
		{
			if(i == Supporters.size() - 1)
				fullList+= Supporters.get(i);
			else
				fullList += Supporters.get(i) + ", ";
		}
		
		return fullList;
	}  //end supportersAsString
	
	public Iterator<String> getSupporters()
	{
		return Supporters.iterator();
	}  //end getSupporters
	
	//setters
	public void setPhone(String p)
	{
		phone = p;
	}  //end setPhone
	
	public void setAddress(String a)
	{
		address = a;
	}  //end setAddress
	
	public void addAnnouncement(String an)
	{
		Announcements.add(new Post(an, name));
		Collections.sort(Announcements);
	}  //end addAnnouncements
	
	public boolean containsSupporter(String name)
	{
		return Supporters.contains(name);
	}  //end containsSupporter
	
	public void addSupporter(String name)
	{
		if (!Supporters.contains(name))
		{
			Supporters.add(name);
			likeCounter ++;
		}
	}  //end addSupporter
	
	public void removeSupporter(String name)
	{
		if (Supporters.contains(name))
		{
			Supporters.remove(name);
			likeCounter--;
		}
	}  //end removeSupporter
}  //end Organization
