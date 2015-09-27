/*
 * File: ProfileDatabase.java
 * -------------------------------
 * This class keeps track of the profiles of all users and organizations.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;

public class ProfileDatabase  {

    private HashMap<String, Profile> dt;
    
    ProfileDatabase()
    {
    	dt = new HashMap<String, Profile>();
    }
    
    public void addFromFile(String file)
    {
    	try
    	{
    		BufferedReader br = new BufferedReader(new FileReader(file));
    		String line;
    		
    		while ((line = br.readLine()) != null)
    		{
    			String [] parts = line.split("; ");
    			String type = parts[0];
    			String name = parts[1];
				String image = parts[2];
    			
    			if (type.equals("user"))
    			{
    				String status = parts[3];
    				String[] fr = parts[4].split(", ");
    				
    				ArrayList<String> friends = new ArrayList<String>();
    				for(String i: fr)
    				{
    					friends.add(i);
    				}  //end loop
    				
    				dt.put(name, new Person(name, image, status, friends));
    			}  //end user adding
    			
    			else if (type.equals("organization"))
    			{
    				String phone = parts[3];
    				String address = parts[4];
    				String announcement = parts[5];
    				int likeCount = Integer.parseInt(parts[6]);
    				String[] su = parts[7].split(", ");
    				
    				ArrayList<String> supporters = new ArrayList<String>();
    				for (String i : su)
    				{
    					supporters.add(i);
    				}  //end loop
    				
    				dt.put(name, new Organization(name, image, phone, address, announcement, likeCount, supporters));
    			}  //end org adding
    			
    			else
    				System.out.println("could not add" + name + ", type not recognized.");
    		}  //end while
    		
    		br.close();
    	}  //end try
    	
    	catch (IOException e)
    	{
    		System.out.println("File Error");
    	}  //end catch
    }  //end addFromFile
    
    public Profile search(String n)
    {
    	Profile result = dt.get(n);
    	
    	return result;
    }  //end search
    
    public ArrayList<Post> prepareNewsFeed(Person p)
    {
    	ArrayList<Person> people = new ArrayList<Person>();
    	ArrayList<Post> posts = new ArrayList<Post>();
    	
    	Iterator<String> friendIt = p.getFriends();
    	people.add(p);  //adds the person whose posts you're compiling into the list
    	
    	while (friendIt.hasNext())
    	{
    		//adds the other people into the list
    		Person result = (Person) search(friendIt.next());
    		
    		if(result == null)
    			continue;  //goes to next iteration if the name does not match a person's record
    		
    		people.add(result);
    	}  //end while
    	
    	for(Person i : people)
    	{
    		//adds all the posts of each person in the people list
    		Iterator<Post> it = i.getStatuses();
        	while (it.hasNext())
        	{
        		
        		posts.add(it.next());
        	}  //end while
    	}
    	
    	Collections.sort(posts);
    	
    	return posts;
    }

    public Set<String> returnKeySet()
    {
    	//Returns a Set of keys
    	return dt.keySet();
    }  //end keySet
}  //end ProfileDatabase
