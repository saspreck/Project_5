import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
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
import javax.swing.DefaultComboBoxModel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
/**
 * Class to create a GUI for calculating Hamming Distance
 * @author skylersprecker
 * @version 2019-04-28
 */
public class HammingDistanceFrame extends JFrame implements MouseListener, ChangeListener {
	
	private static final int FRAME_WIDTH = 700;
	private static final int FRAME_HEIGHT = 700;
	
	
	/**
	 * Function used for refreshing the combo box contents. Populates the box with the stations.
	 */
	private DefaultComboBoxModel<String> getComboBoxModel() throws IOException, FileNotFoundException
	{
		BufferedReader bf = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/" + "Project5" + "/" + "Mesonet.txt"));
		
		ArrayList<String> stations = new ArrayList<String>();
		while (bf.ready())
		{
			stations.add(bf.readLine());
		}
		String[] comboBoxModel = stations.toArray(new String[stations.size()]);
		bf.close();
	    return new DefaultComboBoxModel<>(comboBoxModel);
	}
	/**
	 * Interactive panel that allows the user to 
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
		 ====================================================================================*/
		
		//label for box to set hamming distance
		JLabel hammDist = new JLabel();
		hammDist.setText("Enter Hamming Distance:");
		hammDist.setVisible(true);
		leftSideConst = new GridBagConstraints();
		leftSideConst.gridx = 0;
		leftSideConst.gridy = 0;
		//leftSideConst.insets = new Insets(50, 50, 50, 50);
		leftSide.add(hammDist, leftSideConst);
		
		//text field for hamming distance
		JTextField hammDistInfo = new JTextField("2", 5);
		hammDistInfo.setEditable(false);
		hammDistInfo.setVisible(true);
		leftSideConst = new GridBagConstraints();
		leftSideConst.gridx = 1;
		leftSideConst.gridy = 0;
		//leftSideConst.insets = new Insets(50, 50, 50, 50);
		leftSide.add(hammDistInfo, leftSideConst);
		
		//slider to select the hamming distance to compare
		JSlider compDist = new JSlider(0, 4);
		compDist.addChangeListener(this);
		compDist.setMajorTickSpacing(1);
		compDist.setPaintTicks(true);
		compDist.setPaintLabels(true);
		compDist.setSnapToTicks(true);
		compDist.setVisible(true);
		leftSideConst = new GridBagConstraints();
		leftSideConst.gridx = 0;
		leftSideConst.gridy = 1;
		//leftSideConst.insets = new Insets(50, 50, 50, 50);
		leftSide.add(compDist, leftSideConst);
		
		//button to shows stations at the selected hamming distance
		JButton showStation = new JButton("Show Station");
		showStation.setSize(new Dimension(20, 50));
		leftSideConst = new GridBagConstraints();
		leftSideConst.gridx = 0;
		leftSideConst.gridy = 2;
		//leftSideConst.insets = new Insets(50, 50, 50, 50);
		leftSide.add(showStation, leftSideConst);
		
		//text area to show stations with the selected hamming distance
		JTextArea matchingStations = new JTextArea(10, 10);
		JScrollPane scroller = new JScrollPane(matchingStations);
		matchingStations.setEditable(false);
		matchingStations.setVisible(true);
		leftSideConst = new GridBagConstraints();
		leftSideConst.gridx = 0;
		leftSideConst.gridy = 3;
		//leftSideConst.insets = new Insets(50, 50, 50, 50);
		leftSide.add(scroller, leftSideConst);
		
		//label for combo box to select station to compare to
		JLabel compareWith = new JLabel("Compare with:");
		compareWith.setVisible(true);
		leftSideConst = new GridBagConstraints();
		leftSideConst.gridx = 0;
		leftSideConst.gridy = 4;
		leftSideConst.insets = new Insets(10, 10, 10, 10);
		leftSide.add(compareWith, leftSideConst);
		
		//Combo box to select which station to compare to
		DefaultComboBoxModel<String> model = getComboBoxModel();
		JComboBox<String> allStations = new JComboBox<String>(model);
		allStations.setVisible(true);
		leftSideConst = new GridBagConstraints();
		leftSideConst.gridx = 1;
		leftSideConst.gridy = 4;
		//leftSideConst.insets = new Insets(10, 10, 10, 10);
		leftSide.add(allStations, leftSideConst);
		
		//button to calculate the hamming distances of the selected station
		JButton calcHD = new JButton("Calculate HD");
		calcHD.setSize(new Dimension(20, 50));
		leftSideConst = new GridBagConstraints();
		leftSideConst.gridx = 0;
		leftSideConst.gridy = 5;
		//leftSideConst.insets = new Insets(50, 50, 50, 50);
		leftSide.add(calcHD, leftSideConst);
		
		/*==================================================================================
		 * Panel to hold the labels and text fields showing the number of stations with the 
		 * corresponding hamming distance to the selected station
		 ===================================================================================*/
		JPanel distances = new JPanel(new GridLayout(6, 2, 30, 10));
		distances.setVisible(true);
		leftSideConst = new GridBagConstraints();
		leftSideConst.gridx = 0;
		leftSideConst.gridy = 6;
		leftSideConst.insets = new Insets(10, 10, 10, 10);
		
		/**
		 * Labels and fields to display the numbers
		 */
		JLabel distance0 = new JLabel("Distance 0");

		JLabel distance1 = new JLabel("Distance 1");
		
		JLabel distance2 = new JLabel("Distance 2");
		
		JLabel distance3 = new JLabel("Distance 3");
		
		JLabel distance4 = new JLabel("Distance 4");
		
		JTextField numDist0 = new JTextField(" ");
		numDist0.setEditable(false);

		JTextField numDist1 = new JTextField(" ");
		numDist1.setEditable(false);

		JTextField numDist2 = new JTextField(" ");
		numDist2.setEditable(false);

		JTextField numDist3 = new JTextField(" ");
		numDist3.setEditable(false);

		JTextField numDist4 = new JTextField(" ");
		numDist4.setEditable(false);
		
		JButton addStation = new JButton("Add Station");
		addStation.setSize(new Dimension(20, 50));
		
		JTextField addStationField = new JTextField(" ", 5);
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
		
		/**======================================
		 * Back adding components to larger panel
		=========================================*/
		
		//button to let the user add a station to the file/list
		
		/*leftSideConst = new GridBagConstraints();
		leftSideConst.gridx = 0;
		leftSideConst.gridy = 8;
		//leftSideConst.insets = new Insets(50, 50, 50, 50);
		leftSide.add(addStation, leftSideConst);*/
		
		//text field where the user can type in a station to add
		
		/*leftSideConst = new GridBagConstraints();
		leftSideConst.gridx = 1;
		leftSideConst.gridy = 8;
		//leftSideConst.insets = new Insets(50, 50, 50, 50);
		leftSide.add(addStationField, leftSideConst);*/
		
		//adds panel to frame
		this.add(leftSide);

		
		//makes everything visible
		this.setVisible(true);
		
		/**==================================================================================================
		 * The following are action and change listeners for the buttons, slider, and combo box in the window
		=====================================================================================================*/
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}
	
	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	@Override
	public void stateChanged(ChangeEvent event) {}
	
	public static void main (String [] args) throws IOException {
		new HammingDistanceFrame();
	}

}
