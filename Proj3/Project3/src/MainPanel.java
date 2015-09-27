import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;


public class MainPanel extends JPanel
{
	private ProfileDatabase socialNetwork;
	private String loggedInUser = " "; // the user or organization who is currently logged in
	private String lookedUpUser = " "; // the user  or organization we looked up
	private String inOrganization = " ";  //the organization being looked at
	
	// the text field and the button used to log in
    JTextField loginUserName;
    JButton loginUserButton;

	// the text field and the button used to look up somebody's profile
    JTextField searchUserName;
    JButton searchUserButton;

	// the text field and the button used to update the status
    JTextField newStatus;
    JButton changeStatusButton;
    
    // add announcement button
    JTextField newAnnouncement;
    JButton newAnnouncementButton;

	// the text field and the button used to add a new friend
    JTextField enterNewFriend;
    JButton addFriendButton;
    
    // when clicked, should take you to the profile of the loggedUser
    JButton homeButton; 
 
    // When clicked, adds the currently logged user as a supporter for the organization
    JButton likeButton;
    
    // When clicked, removes the user from the supporters list (opp of above)
    JButton unlikeButton;
    
    // subpanels
    JPanel topPanel; //the blue panel on top, where we login or search for users
    JPanel userProfilePanel; // left panel; shows user's profile
    JPanel newsFeedPanel; // right panel: shows recent posts of user and friends
    JPanel bottomPanel; // shows who is logged in
    
    public MainPanel (ProfileDatabase s) {
      socialNetwork = s;
      setPreferredSize (new Dimension(700, 400));
      setBackground (Color.lightGray);
      setLayout (new BorderLayout ());
      createSubpanels() ;
   }

   /** Creates the four subpanels of the current panel **/
    public void createSubpanels() {
   
    	createTopPanel();
    	createUserProfilePanel();
        createNewsFeedPanel();
        createBottomPanel();
       
        String randName = getRandomName();
        Profile user = socialNetwork.search(randName);
        
        //gets a new name as long as the name returned is an organization
        while (user.getType().equals("Organization"))
        {
        	user = socialNetwork.search(getRandomName());
        }
    	
        loggedInUser = user.getName();
        showPerson((Person) user);
       
        // do not change the code below
        add(topPanel, BorderLayout.NORTH);
        add(userProfilePanel, BorderLayout.CENTER);
        add(newsFeedPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
    	
    }
    
    private String getRandomName()
    {
    	int userCounter = 0;
        
        for(String i : socialNetwork.returnKeySet())
        {
        	//counts the number of users for random generator input
        	userCounter++;
        }
        
        Random rand = new Random();
        int limit = rand.nextInt(userCounter - 1);
        int counter = 0;
        String randU = " ";
        
        for (String j : socialNetwork.returnKeySet())
        {
        	//counts the random number of times through the keySet, then pulls that name
        	if (counter == limit)
        	{
        		randU = j;
        		break;
        	}
        	counter++;
        }
        return randU;
    }  //end getRandomName
    

    /** Creates the top panel that is used for logging in and looking up a profile **/
    private void createTopPanel() {
    	
        topPanel = new JPanel();
        topPanel.setBackground (new Color(0, 0, 100));
        
        // the text field and the button for letting the user login
        loginUserName = new JTextField(5);
        topPanel.add(loginUserName);
        loginUserButton = new JButton("Login");
        loginUserButton.addActionListener (new ButtonListener());
        topPanel.add(loginUserButton);
        this.add(topPanel, BorderLayout.NORTH);
        
        // the text field and the button for looking up some user's profile
        searchUserName = new JTextField(5);
        topPanel.add(searchUserName);
        searchUserButton = new JButton("Find a profile");
        searchUserButton.addActionListener (new ButtonListener());
        topPanel.add(searchUserButton);

        this.add(topPanel, BorderLayout.NORTH);

    }
    
    /**  Creates the subpanel  on the left that displays the user's profile  **/
    private void createUserProfilePanel() {
    	
        userProfilePanel = new JPanel();
        userProfilePanel.setPreferredSize (new Dimension(350, 400));
        userProfilePanel.setLayout (new BoxLayout( userProfilePanel, BoxLayout.Y_AXIS));
        userProfilePanel.setBackground (Color.lightGray);
        
    }
    
    /**  Creates the subpanel on the right that displays the the user's news feed  **/
    private void createNewsFeedPanel() {
    	
    		newsFeedPanel = new JPanel();
        newsFeedPanel.setPreferredSize (new Dimension(350, 400));
        newsFeedPanel.setBackground (Color.white);
        newsFeedPanel.setLayout (new BoxLayout (newsFeedPanel, BoxLayout.Y_AXIS));
 	
    }
    
    /**  The subpanel  on the bottom that displays who is logged in **/
    private void createBottomPanel() {
    	
    	 	 bottomPanel = new JPanel();
         JLabel currentlyLoggedIn = new JLabel("You are currently logged in as " + loggedInUser);
         bottomPanel.add(currentlyLoggedIn);    
    }
    
    /** The inner class that implements ActionListener interface
     *  Use it to handle all the button events.
     */
     private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			System.out.println("Button pressed");
			JButton b = (JButton)e.getSource();
			
			if (b.equals(loginUserButton)) 
			{ 
				// The code below will be executed when the Login button is pressed
				userProfilePanel.removeAll();
				String name = loginUserName.getText();
				loggedInUser = name;
				
				Profile user = socialNetwork.search(name);
				
				if(user.getType().equals("Person"))
				{
					Person userP = (Person) user;
					showPerson(userP);
				}  //end person
				
				else if (user.getType().equals("Organization"))
				{
					Organization userO = (Organization) user;
					showOrganization(userO);
				}  //end organization
			}
			
			if (b.equals(addFriendButton))
			{
				// The code will execute when the add Friend button is pressed
				userProfilePanel.removeAll();
				String name = enterNewFriend.getText();
				Person user = (Person) socialNetwork.search(loggedInUser);
				Person friend = (Person) socialNetwork.search(name);
				
				if (friend == null)
				{
					showPerson(user);
					addLabel("No Person With Given Name Found", "Serif", 18, userProfilePanel);
				}
				
				else
				{
					user.addFriend(name);
					friend.addFriend(loggedInUser);
					showPerson(user);
				}
			}  //end addFriendButton
			
			if (b.equals(changeStatusButton))
			{
				// If the change status button is pressed
				userProfilePanel.removeAll();
				String status = newStatus.getText();
				Person user = (Person) socialNetwork.search(loggedInUser);
				user.addStatus(status);
				
				showPerson(user);
			}  //end changeStatus
			
			if (b.equals(homeButton))
			{
				//Directs to the loggedInUser if the home button is pressed
				userProfilePanel.removeAll();
				showPerson((Person) socialNetwork.search(loggedInUser));
			}  //end homeButton
			
			if (b.equals(newAnnouncementButton))
			{
				// If the change status button is pressed
				userProfilePanel.removeAll();
				String status = newAnnouncement.getText();
				Organization user = (Organization) socialNetwork.search(loggedInUser);
				user.addAnnouncement(status);
				
				showOrganization(user);
			}  //end newAnnouncementButton
			
			
			if (b.equals(searchUserButton))
			{
				//When "Find a profile" is pressed
				userProfilePanel.removeAll();
				String lookup = searchUserName.getText();
				Profile lookedUpUser = socialNetwork.search(lookup);
				
				if(lookedUpUser.getType().equals("Person"))
				{
					Person userP = (Person) lookedUpUser;
					showPerson(userP);
				}  //end person
				
				else if (lookedUpUser.getType().equals("Organization"))
				{
					Organization userO = (Organization) lookedUpUser;
					showOrganization(userO);
				}  //end organization
				
			}  //end searchUserButton
			
			if (b.equals(likeButton))
			{
				userProfilePanel.removeAll();
				Organization org = (Organization) socialNetwork.search(inOrganization);
				
				org.addSupporter(loggedInUser);
				
				showOrganization(org);
			}  //end likeButton
			
			if (b.equals(unlikeButton))
			{
				userProfilePanel.removeAll();
				Organization org = (Organization) socialNetwork.search(inOrganization);
				org.removeSupporter(loggedInUser);
				showOrganization(org);
			}  //end unlikeButton
			
			updateUI();
		}
   }  //end ButtonListener
     
     
     /** The function that shows how to use GUI to display a user profile. 
      * Everything is hard coded. You need to replace it with your own function
      * that uses information from the profile database.
      **/
     public void showPerson(Person p) { 
    	 
    	 // add the name label  and image to the panel
    	 addLabel(p.getName(), "Serif", 25, userProfilePanel);
    	 addImage(p.getImgSource(), userProfilePanel);
    	 
    	 // add status
    	 addLabel(p.getFirstStatus(), "Serif", 18, userProfilePanel);
    	 
    	 //only if user logged in
    	 if (loggedInUser.equals(p.getName()))
    		 addStatusTextfieldAndButton("Change status");
    	 
    	 // add friends
    	 String friends = p.friendsAsString();
    	 addLabel("Friends: " + friends, "Serif", 18, userProfilePanel);
    	 
    	 //only if user logged in
    	 if (loggedInUser.equals(p.getName()))
    	 {
    	 	addFriendTextfieldAndButton();

    	 	// add information to the news feed
    	 	newsFeedPanel.removeAll();
    	 	JLabel title = new JLabel("News Feed"); 
    	 	title.setFont(new Font("Serif", Font.PLAIN, 20));
    	 	newsFeedPanel.add(title);
    	 	JLabel line = new JLabel("------------");
    	 	newsFeedPanel.add(line);
    	 	newsFeedPanel.add(new JLabel("\n"));
    	 	
    	 	ArrayList<Post> posts = socialNetwork.prepareNewsFeed(p);
   	     
    	 	//limits posts displayed to 5, so the screen is not overloaded
    	 	if(posts.size() < 5)
    	 	{
    	 		for (Post i : posts)
    	 		{
    	 			showPersonPost(i);
    	 		}
    	 	}
    	 	else
    	 	{
    	 		for (int i = 0; i < 5; i++)
    	 		{
    	 			showPersonPost(posts.get(i));
    	 		}
    	 	}
   	     
   	    
    	 	bottomPanel.removeAll();
    	 	bottomPanel.add(new JLabel("Currently logged in as " + loggedInUser));
    	 }  //end loggedIn display
    	 
    	 else
    	 {
    	 	newsFeedPanel.removeAll();
    	 	//home button
    	 	homeButton = new JButton("Return to " + loggedInUser + "'s Profile");
    	 	homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    	 	homeButton.addActionListener(new ButtonListener());
    	 	newsFeedPanel.add(homeButton);
    	 }  //end not logged in display
      
     }  //end showPerson
     
     public void showPersonPost(Post p)
     {
    	 JLabel nameFriend = new JLabel(p.getAuthor());
	     nameFriend.setAlignmentX(Component.LEFT_ALIGNMENT);
	     nameFriend.setFont(new Font("Serif", Font.PLAIN, 18));
	     newsFeedPanel.add(nameFriend);
	     JLabel friendsStatus = new JLabel(p.toString());
	     friendsStatus.setAlignmentX(Component.LEFT_ALIGNMENT);
	     newsFeedPanel.add(friendsStatus);
	     newsFeedPanel.add(new JLabel(" "));
     }
    
     public void showOrganization(Organization o)
     {
    	 inOrganization = o.getName();
    	 addLabel(o.getName(), "Serif", 25, userProfilePanel);
    	 addImage(o.getImgSource(), userProfilePanel);
    	 
    	 addLabel("Ph: " + o.getPhone(), "Serif", 18, userProfilePanel);
    	 addLabel(o.getAddress(), "Serif", 18, userProfilePanel);
    	 
    	 if (loggedInUser.equals(o.getName()))
    	 {
    		 // add announcement
    		 addAnnouncementTextfieldAndButton("Add an announcement");
    	 }
    	 
    	 //add likes
    	 addLabel("Likes: " + o.getLikes(), "Serif", 18, userProfilePanel);
    	 
    	 // add supporters
    	 String supporters = o.supportersAsString();
    	 addLabel("Supporters: " + supporters, "Serif", 18, userProfilePanel);
    	 
    	 if ((!loggedInUser.equals(o.getName())) && (!o.containsSupporter(loggedInUser)))
    	 {
    		 //adds the like button if user hasn't liked the page yet
    		 likeButton = new JButton("Like");
    		 likeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    		 likeButton.addActionListener(new ButtonListener());
    		 userProfilePanel.add(likeButton);
    	 }
    	 
    	 if ((!loggedInUser.equals(o.getName())) && (o.containsSupporter(loggedInUser)))
    	 {
    		 //adds the unlike button when org is liked
    		 unlikeButton = new JButton("Unlike");
    		 unlikeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    		 unlikeButton.addActionListener(new ButtonListener());
    		 userProfilePanel.add(unlikeButton);
    	 }

    	 // add information to the news feed
    	 newsFeedPanel.removeAll();
		 JLabel title = new JLabel("Announcements"); 
	     title.setFont(new Font("Serif", Font.PLAIN, 20));
	     newsFeedPanel.add(title);
	     JLabel line = new JLabel("------------");
	     newsFeedPanel.add(line);
	     newsFeedPanel.add(new JLabel("\n")); 
	     
	    Iterator it = o.getAnnouncements();
	    
	    while(it.hasNext())
	    {
	    	JLabel announcement = new JLabel(it.next().toString());
		     announcement.setAlignmentX(Component.LEFT_ALIGNMENT);
		     newsFeedPanel.add(announcement);
		     newsFeedPanel.add(new JLabel(" "));
	    }
	     
	    bottomPanel.removeAll();
		bottomPanel.add(new JLabel("Currently logged in as " + loggedInUser)); 
     }  //end showOrganization
     
     /** Adds a label with the given text (name) to the panel passed as a parameter
      *  Centers the label in the center of the panel.
      */
     public void addLabel(String name, String font, int fontSize, JPanel panel) {

    	 	JLabel userName = new JLabel(name);
    	 	userName.setFont(new Font(font, Font.PLAIN, fontSize));
    	 	userName.setAlignmentX(Component.CENTER_ALIGNMENT);
    	 	panel.add(userName);    
     }
     
     /** Adds an image with the given filename to the userProfilePanel */
     public void addImage(String imagefile, JPanel panel) {
	    	 File imFile = new File("images/" + imagefile);	
	    	 BufferedImage myPicture;
	    	 try {
	    		 myPicture = ImageIO.read(imFile);
	    		 JLabel picLabel = new JLabel(new ImageIcon(myPicture));
	    		 picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    		 panel.add(picLabel);
	    	 }
	    	 catch (IOException e) {
	    		 System.out.println(e);
	    	 }
     }   
     
     
     /** Adds a text field and a button for changing the status to the userProfilePanel */
      public void addStatusTextfieldAndButton(String text) {
    	 // add a textfield
    	 newStatus = new JTextField(15);
    	 newStatus.setMaximumSize( newStatus.getPreferredSize() );
    	 newStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
         userProfilePanel.add(newStatus );
         
         //add a button
         changeStatusButton = new JButton(text);
         changeStatusButton.addActionListener (new ButtonListener());
         changeStatusButton.setAlignmentX(Component.CENTER_ALIGNMENT);
         userProfilePanel.add(changeStatusButton);

      }
     
      /** Adds a text field and a button for adding a friend to the userProfilePanel */
      public void addFriendTextfieldAndButton() {
    	 // add a text field
    	 enterNewFriend = new JTextField(15);
    	 enterNewFriend.setMaximumSize( newStatus.getPreferredSize() );
    	 enterNewFriend.setAlignmentX(Component.CENTER_ALIGNMENT);
    	 userProfilePanel.add(enterNewFriend );
      
    	 //add a button
    	 addFriendButton = new JButton("Add a friend");
    	 addFriendButton.addActionListener (new ButtonListener());
    	 addFriendButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    	 userProfilePanel.add(addFriendButton);
      }
      
      public void addAnnouncementTextfieldAndButton(String text)
      {
    	  newAnnouncement = new JTextField(15);
    	  newAnnouncement.setMaximumSize(newStatus.getPreferredSize() );
    	  newAnnouncement.setAlignmentX(Component.CENTER_ALIGNMENT);
    	  userProfilePanel.add(newAnnouncement);
    	  
    	  newAnnouncementButton = new JButton(text);
    	  newAnnouncementButton.addActionListener(new ButtonListener());
    	  newAnnouncementButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    	  userProfilePanel.add(newAnnouncementButton);
      }
}

