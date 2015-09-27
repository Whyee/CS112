import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MPlayerPanel extends JPanel {

	// three subpanels
	JPanel topPanel, bottomPanel; 
	JScrollPane centerPanel;

	// boxes, textfield, check box
	JButton playButton, stopButton, exitButton, loadMp3Button, saveButton, openButton;

	// the checkbox that specifies whether the songs should be sorted by Artist (or by Title)
	JCheckBox sortBox = new JCheckBox("Sort by Artist");

	// the text field used to search for a song
	JTextField searchBox;
	
	//library object
	private Library lib;
	
	//player thread and GUI Thread
	private Thread GUIThread;
	private Thread currentThread;

	int selectedSong = -1; // the index of the row that corresponds to the selected song
	private JTable table = null;
	private final JFileChooser fc = new JFileChooser(); // for opening a window to select a file

	MPlayerPanel() {

		lib = new Library();
		
		this.setLayout(new BorderLayout());
		// Create panels: top, center, bottom
		// Create the top panel that has buttons: Load mp3, Save Library, Load Library 
		// It also has a textfield to search for a song and "sort by artist" checkbox
		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1,4));

		// create buttons
		loadMp3Button = new JButton("Load mp3");
		saveButton = new JButton("Save Library");
		openButton = new JButton("Load Library");
		searchBox = new JTextField(5);
		exitButton = new JButton("Exit");
		playButton = new JButton("Play");
		stopButton = new JButton("Stop");

		// add a listener for each button
		loadMp3Button.addActionListener(new MyButtonListener());
		saveButton.addActionListener(new MyButtonListener());
		openButton.addActionListener(new MyButtonListener());
		sortBox.addActionListener(new MyButtonListener());
		exitButton.addActionListener(new MyButtonListener());
		playButton.addActionListener(new MyButtonListener());
		stopButton.addActionListener(new MyButtonListener());
		searchBox.addActionListener(new MyButtonListener());


		// add buttons, textfield and a checkbox to the top panel
		topPanel.add(loadMp3Button);
		topPanel.add(saveButton);
		topPanel.add(openButton);
		topPanel.add(searchBox);
		topPanel.add(sortBox);

		this.add(topPanel, BorderLayout.NORTH);

		// create the bottom panel and add three buttons to it
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1,3));
		bottomPanel.add(playButton);
		bottomPanel.add(stopButton);
		bottomPanel.add(exitButton);

		this.add(bottomPanel, BorderLayout.SOUTH);

		// the panel in the center that shows mp3 songs
		centerPanel = new JScrollPane();
		this.add(centerPanel, BorderLayout.CENTER );

		// file chooser: set the default directory to the current directory 
		fc.setCurrentDirectory(new File("."));

	}


	/** A inner listener class for buttons, textfield and checkbox **/
	class MyButtonListener implements ActionListener {

		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == loadMp3Button) {
				System.out.println("Load mp3 button");
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fc.setDialogTitle("Select a directory with mp3 songs");
				// open a window to select a directory
				int returnVal = fc.showOpenDialog(MPlayerPanel.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					//takes the selected directory and runs the library's
					//inputFromDT method to create the library of songs
					File dir = fc.getSelectedFile();
					String filePath = dir.getPath();
					boolean result = lib.inputFromDT(filePath);
					
					if (result == true)
					{
						lib.sortSongsTitle();
						displaySongs();
					}				

				}
			}
			else if (e.getSource() == saveButton) {
				// save the song catalog into a file
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fc.setDialogTitle("Select a file to save the library file");
				int returnVal = fc.showSaveDialog(MPlayerPanel.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					lib.saveLibrary(file);  //uses the library's save method
				}
			}

			else if (e.getSource() == openButton) {
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fc.setDialogTitle("Select a file to save the library file");
				int returnVal = fc.showSaveDialog(MPlayerPanel.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					lib.loadLibrary(file); //uses the library's load method
					displaySongs();
				}
			}

			else if (e.getSource() == playButton) {

				selectedSong = table.getSelectedRow();
				String filePath = lib.getFileAtIndex(selectedSong);
				
				if (currentThread != null)
					currentThread.stop();
				
				currentThread = new PlayerThread(filePath);
				currentThread.start();
			}
			
			else if (e.getSource() == stopButton) {
				if (currentThread != null)
				{
					currentThread.stop();
				}
			}
			
			else if (e.getSource() == exitButton) {
				System.exit(0);
			}

			else if (e.getSource() == sortBox) {
				if (sortBox.isSelected())
				{
				lib.sortSongsArtist();
				displaySongs();
				}
				
				else
				{
					lib.sortSongsTitle();
					displaySongs();
				}
				

			}
			else if (e.getSource() == searchBox) {
				String searchT = searchBox.getText();
				// FILL IN CODE


			}
			updateUI();
		} // actionPerformed
		
		public void displaySongs()
		{
			System.out.println("In displaySongs");
			
			String[][] tableElems = new String[lib.getSize()][2];
			String[] columnNames = {"Title", "Artist"};
			
			Iterator<Song> it = lib.libraryIterator();
			int counter = 0;
			while (it.hasNext())
			{
				System.out.println("Adding a song.");
				Song current = it.next();
				tableElems[counter][0] = current.getName();
				tableElems[counter][1] = current.getArtist();
				counter++;
			}
			
			// creating a table and adding it to the centerPanel
			table = new JTable(tableElems, columnNames );
			centerPanel.getViewport().add (table);
			updateUI();
		}
	} // ButtonListener





}
