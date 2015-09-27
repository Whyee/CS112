/**
 * File: Driver.java
 * The program implements a very basic social network.
 */

import java.awt.event.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;

public class Driver {

	public static void main(String[] args) {
		
		ProfileDatabase s = new ProfileDatabase();
		s.addFromFile("profiles");
		
		// create a window that says MyWorld
		JFrame frame = new JFrame ("MyWorld"); 
	    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	   
	    // create the main panel
	    MainPanel panel = new MainPanel(s);
	    frame.add(panel);
	    frame.pack();
	    frame.setVisible(true);
	}  //end main
}
