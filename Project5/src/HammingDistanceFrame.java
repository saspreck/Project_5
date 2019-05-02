import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * Class to create a GUI for calculating Hamming Distance
 * @author skylersprecker
 * @version 2019-04-28
 */
public class HammingDistanceFrame extends JFrame implements ChangeListener {
	
	private static final int FRAME_WIDTH = 700;
	private static final int FRAME_HEIGHT = 700;
	
	//all components that go on the left side of the window
	JPanel hammDistPanel;
	JLabel hammDist;
	JTextField hammDistInfo;
	JSlider compDist;
	JButton showStation;
	JTextArea matchingStations;
	JPanel comboPanel;
	JLabel compareWith;
	JComboBox<String> allStations;
	JButton calcHD;
	JPanel distances;
	JLabel distance0;
	JLabel distance1;
	JLabel distance2;
	JLabel distance3;
	JLabel distance4;
	JTextField numDist0;
	JTextField numDist1;
	JTextField numDist2;
	JTextField numDist3;
	JTextField numDist4;
	JButton addStation;
	JTextField addStationField;
	ArrayList<String> comboBoxStations;
	
	/*
	 * Method that reads in all stations from the file and stores them in an arrayList
	 */
	private ArrayList<String> readInFromFile() throws IOException, FileNotFoundException {
		
			ArrayList<String> stations = new ArrayList<String>();
			
			BufferedReader bf = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/" + "Project5" + "/" + "Mesonet.txt"));
			//reads in each line from the file and adds it to the arraylist
			while (bf.ready())
			{	
				stations.add(bf.readLine());
			}
;
			return stations;
	}
	
	/*
	 * Method to set up the combo box
	 */
	private void comboBoxSetup() {
		
		try {
			//gets an arraylist with all the stations from the file
			comboBoxStations = readInFromFile();
			
			//adds each station to the combo box
			for(String stn : comboBoxStations) {
				allStations.addItem(stn);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	/*
	 * Method to add a new station to the combo box in the appropriate place
	 */
	public void addNewStation(String StID) {
		//adds the new station to the arraylist
		comboBoxStations.add(StID);
		//sorts the arraylist
		Collections.sort(comboBoxStations);
		//clears the combo box
		allStations.removeAllItems();
		
		//adds each station, including the new one, back into the combo box
		for(String stn : comboBoxStations) {
			allStations.addItem(stn);
		}
	}

	/**
	 * Interactive panel that allows the user to calculate various things 
	 * based on a selected station's hamming distance
	 * @throws IOException 
	 */
	public HammingDistanceFrame() throws IOException {
		//calls super constructors and sets size, visibility, close operation, and the layout manager
		super("Hamming Distance Frame");
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(1,2));

		//creates panel for the left side of the display
		JPanel leftSide = new JPanel(new GridBagLayout());
		leftSide.setPreferredSize(new Dimension(FRAME_WIDTH - 400, FRAME_HEIGHT));
		
		//declares new constraints for the panel
		GridBagConstraints leftSideConst = null;
		
		/*===================================================================================
		 * The following are components for the left side of the window,
		 * where hamming distances are calculated according to the station the user selects
		 ===================================================================================*/
		
		/*===================================================================================
		 * Panel to hold the label and text field for the 
		 * hamming distance selected on the slider
		 ===================================================================================*/
		hammDistPanel = new JPanel(new GridLayout(1, 2));
		hammDistPanel.setVisible(true);
		leftSideConst = new GridBagConstraints();
		leftSideConst.gridx = 0;
		leftSideConst.gridy = 0;
		leftSideConst.insets = new Insets(10, 10, 10, 10);
		
		//label for box to set hamming distance
		hammDist = new JLabel();
		hammDist.setText("Enter Hamming Distance:");
		hammDist.setVisible(true);
		
		//text field for hamming distance
		hammDistInfo = new JTextField("2", 2);
		hammDistInfo.setEditable(false);
		hammDistInfo.setVisible(true);
		
		//adds components to panel and panel to frame
		hammDistPanel.add(hammDist);
		hammDistPanel.add(hammDistInfo);
		leftSide.add(hammDistPanel, leftSideConst);
		
		//slider to select the hamming distance to compare
		compDist = new JSlider(0, 4);
		compDist.addChangeListener(this);
		compDist.setMajorTickSpacing(1);
		compDist.setPaintTicks(true);
		compDist.setPaintLabels(true);
		compDist.setSnapToTicks(true);
		compDist.setVisible(true);
		compDist.addChangeListener(this);
		leftSideConst = new GridBagConstraints();
		leftSideConst.gridx = 0;
		leftSideConst.gridy = 1;
		leftSide.add(compDist, leftSideConst);
		
		
		//button to shows stations at the selected hamming distance
		showStation = new JButton("Show Station");
		showStation.setSize(new Dimension(20, 50));
		leftSideConst = new GridBagConstraints();
		leftSideConst.gridx = 0;
		leftSideConst.gridy = 2;
		leftSide.add(showStation, leftSideConst);
		
		//text area to show stations with the selected hamming distance
		matchingStations = new JTextArea(10, 10);
		JScrollPane scroller = new JScrollPane(matchingStations);
		matchingStations.setEditable(false);
		matchingStations.setVisible(true);
		leftSideConst = new GridBagConstraints();
		leftSideConst.gridx = 0;
		leftSideConst.gridy = 3;
		leftSide.add(scroller, leftSideConst);
		
		
		/*======================================================================
		 * Panel to hold combo box, label, and button
		 ======================================================================*/
		comboPanel = new JPanel(new GridLayout(2, 2, 30, 10));
		comboPanel.setVisible(true);
		leftSideConst = new GridBagConstraints();
		leftSideConst.gridx = 0;
		leftSideConst.gridy = 4;
		leftSideConst.insets = new Insets(10, 10, 10, 10);
		
		//label for combo box to select station to compare to
		compareWith = new JLabel("Compare with:");
		compareWith.setVisible(true);
		
		//Combo box to select which station to compare to
		allStations = new JComboBox<String>();
		this.comboBoxSetup();
		
		//button to calculate the hamming distances of the selected station
		calcHD = new JButton("Calculate HD");
		calcHD.setSize(new Dimension(20, 50));
		
		//adds components to panel and panel to frame
		comboPanel.add(compareWith);
		comboPanel.add(allStations);
		comboPanel.add(calcHD);
		leftSide.add(comboPanel, leftSideConst);
		
		
		
		/*==================================================================================
		 * Panel to hold the labels and text fields showing the number of stations with the 
		 * corresponding hamming distance to the selected station
		 ==================================================================================*/
		distances = new JPanel(new GridLayout(6, 2, 10, 10));
		distances.setVisible(true);
		leftSideConst = new GridBagConstraints();
		leftSideConst.gridx = 0;
		leftSideConst.gridy = 6;
		leftSideConst.insets = new Insets(10, 10, 10, 10);
		
		/**
		 * Labels and fields to display the numbers
		 */
		distance0 = new JLabel("Distance 0");

		distance1 = new JLabel("Distance 1");
		
		distance2 = new JLabel("Distance 2");
		
		distance3 = new JLabel("Distance 3");
		
		distance4 = new JLabel("Distance 4");
		
		numDist0 = new JTextField(" ");
		numDist0.setEditable(false);

		numDist1 = new JTextField(" ");
		numDist1.setEditable(false);

		numDist2 = new JTextField(" ");
		numDist2.setEditable(false);

		numDist3 = new JTextField(" ");
		numDist3.setEditable(false);

		numDist4 = new JTextField(" ");
		numDist4.setEditable(false);
		
		addStation = new JButton("Add Station");
		addStation.setSize(new Dimension(20, 50));
		
		addStationField = new JTextField("", 5);
		addStationField.setEditable(true);

		//adds all the components to the panel in the correct order
		distances.add(distance0);
		distances.add(numDist0);
		distances.add(distance1);
		distances.add(numDist1);
		distances.add(distance2);
		distances.add(numDist2);
		distances.add(distance3);
		distances.add(numDist3);
		distances.add(distance4);
		distances.add(numDist4);
		distances.add(addStation);
		distances.add(addStationField);

		//adds the panel to the larger panel according to the constraints
		leftSide.add(distances, leftSideConst);
		
		
		//adds panel to frame
		this.add(leftSide);
		
		
		/**==================================================================================================
		 * The following are action and change listeners for the buttons and combo box in the window
		=====================================================================================================*/
		
		/**
		 * Action listener for the showstation button
		 * Updates the text area to show all stations with a hamming distance equal to the slider's value
		 */
		showStation.addActionListener((e) -> {
			
			ArrayList<String> equalStations = new ArrayList<String>();
			HammingDistanceCalculator station = new HammingDistanceCalculator((String) allStations.getSelectedItem(), compDist.getValue());
			
			//tries to run the method and catches an IOException if it is thrown
			try {
				equalStations = station.selectedDistanceStations();
			}
			catch (IOException ioE){
				System.out.println("Error executing method.");
			}
			//clears the text area
			matchingStations.setText("");
			//adds each station into the text area on a new line
			for(String stn : equalStations) {
				
				matchingStations.append(stn + "\n");
			}
			
		});
		
		/**
		 * Action listener for the calcHD button
		 * Updates each text field with the number of stations with the corresponding hamming distance
		 */
		calcHD.addActionListener((e) -> {
			int[] distances = new int[5];
			HammingDistanceCalculator station = new HammingDistanceCalculator((String) allStations.getSelectedItem(), compDist.getValue());
			
			//tries to run the method and catches an IOException if it is thrown
			try {
				distances = station.calcHammingDistance();
			}
			catch (IOException ioE) {
				System.out.println("Error executing method.");
			}
			
			//sets each text field to the appropriate value from the array
			numDist0.setText(String.valueOf(distances[0]));
			numDist1.setText(String.valueOf(distances[1]));
			numDist2.setText(String.valueOf(distances[2]));
			numDist3.setText(String.valueOf(distances[3]));
			numDist4.setText(String.valueOf(distances[4]));
			
		});
		
		/*
		 * Action listener for the addStation button
		 * Adds the new station to the list in the JComboBox and clears the text field
		 */
		addStation.addActionListener((e) -> {
			//gets the text in the text field
			String newStation = addStationField.getText();
			//makes sure the new station is the right length and not already in the list
			if(newStation.length() == 4 && !comboBoxStations.contains(newStation)) {
				//adds the new station and clears the text box
				addNewStation(newStation);
				addStationField.setText("");
			}
				
		});
		
		
		/*===========================================================================
		 * The following are components for the creative side of the project
		 ===========================================================================*/
		
		//panel to hold everything on the right side
		JPanel rightSide = new JPanel(new GridBagLayout());
		rightSide.setPreferredSize(new Dimension(FRAME_WIDTH - 400, FRAME_HEIGHT));
		GridBagConstraints rightSideConst = null;
		
		//first part of the description
		JLabel description = new JLabel("This side backwards alphabetizes the selected");
		rightSideConst = new GridBagConstraints();
		rightSideConst.gridx = 0;
		rightSideConst.gridy = 0;
		rightSide.add(description, rightSideConst);
		
		//second part of the description
		JLabel description2 = new JLabel("station and calculates its hamming distances.");
		rightSideConst = new GridBagConstraints();
		rightSideConst.gridx = 0;
		rightSideConst.gridy = 1;
		rightSide.add(description2, rightSideConst);
		
		/*================================================================
		 * Panel to hold the selected station label and text field
		 ================================================================*/
		
		JPanel selectedStationPanel = new JPanel(new GridLayout(1, 2));
		rightSideConst = new GridBagConstraints();
		rightSideConst.gridx = 0;
		rightSideConst.gridy = 2;
		rightSideConst.insets = new Insets(10, 10, 10, 10);
		
		//label for the selected station
		JLabel selStation = new JLabel("Selected Station:");
		selectedStationPanel.add(selStation);
		
		//text field showing the selected station
		JTextField selStationField = new JTextField((String) allStations.getSelectedItem());
		selStationField.setEditable(false);
		selectedStationPanel.add(selStationField);
		
		//adds panel to larger panel
		rightSide.add(selectedStationPanel, rightSideConst);
		
		/*================================================================
		 * Panel to hold backwards alphabet label and text field
		 ================================================================*/
		
		JPanel backwardsAlphabetPanel = new JPanel(new GridLayout(1, 2));
		rightSideConst = new GridBagConstraints();
		rightSideConst.gridx = 0;
		rightSideConst.gridy = 3;
		rightSideConst.insets = new Insets(10, 10, 10, 10);
		
		//label for the scrambled station
		JLabel backwardsStation = new JLabel("Scrambled Station:");
		backwardsAlphabetPanel.add(backwardsStation);
		
		//field to display the scrambled station
		JTextField backwardsField = new JTextField("");
		backwardsField.setEditable(false);
		backwardsAlphabetPanel.add(backwardsField);
		rightSide.add(backwardsAlphabetPanel, rightSideConst);
		
		
		/*
		 * Following are not on a subpanel of the rightSide panel
		 */
		
		//button to backwards alphabetize the station
		JButton scramble = new JButton("Backwards Alphabetize");
		rightSideConst = new GridBagConstraints();
		rightSideConst.gridx = 0;
		rightSideConst.gridy = 4;
		rightSide.add(scramble, rightSideConst);
		
		
		//button to add the new station to the list
		JButton addToList = new JButton("Add to list");
		rightSideConst = new GridBagConstraints();
		rightSideConst.gridx = 0;
		rightSideConst.gridy = 5;
		rightSide.add(addToList, rightSideConst);
		
		//adds the panel to the frame
		this.add(rightSide);
		
		//makes everything visible
		this.setVisible(true);
		
		/*===============================================================
		 * Following are action listeners for the combo box 
		 * and scramble and addToList buttons
		 ===============================================================*/
		
		/*
		 * Action listener for the combo box, sets the value of the text field to the selected item in the combo box
		 * updates the text field to reflect the value of the combo box
		 */
		allStations.addActionListener((e) -> {
			selStationField.setText((String) allStations.getSelectedItem()); 
		});
		
		/*
		 * Action listener for the scramble button 
		 * scrambles the selected station
		 */
		scramble.addActionListener((e) -> {
			String chosenStation = selStationField.getText();
			HammingDistanceCalculator hdc = new HammingDistanceCalculator("", 0);
			String scrambledStation = hdc.backwardsAlphabetize(chosenStation);
			backwardsField.setText(scrambledStation);
		});
		
		/*
		 * Action listener for the addToList button
		 * adds the scrambled station to the list of stations
		 */
		addToList.addActionListener((e) -> {
			String scrambledStation = backwardsField.getText();
			addNewStation(scrambledStation);
			backwardsField.setText("");
		});
	}
	
	/**
	 * Method to update the hammDistInfo text field to the value of the slider
	 * @param event
	 */
	@Override
	public void stateChanged(ChangeEvent event) {
		hammDistInfo.setText(String.valueOf(compDist.getValue())); 
	}
	
	/**
	 * Main method
	 * @param args
	 * @throws IOException
	 */
	public static void main (String [] args) throws IOException {
		new HammingDistanceFrame();
	}

}
