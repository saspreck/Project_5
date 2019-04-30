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
		JTextField hammDistInfo = new JTextField();
		hammDistInfo.setEditable(false);
		hammDistInfo.setText("2");
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
		leftSideConst.gridy = 2;
		//leftSideConst.insets = new Insets(50, 50, 50, 50);
		leftSide.add(compDist, leftSideConst);
		
		//button to shows stations at the selected hamming distance
		JButton showStation = new JButton("Show Station");
		showStation.setSize(new Dimension(20, 50));
		leftSideConst = new GridBagConstraints();
		leftSideConst.gridx = 0;
		leftSideConst.gridy = 3;
		//leftSideConst.insets = new Insets(50, 50, 50, 50);
		leftSide.add(showStation, leftSideConst);
		
		//text area to show stations with the selected hamming distance
		JTextArea matchingStations = new JTextArea(15, 15);
		JScrollPane scroller = new JScrollPane(matchingStations);
		matchingStations.setVisible(true);
		leftSideConst = new GridBagConstraints();
		leftSideConst.gridx = 0;
		leftSideConst.gridy = 4;
		//leftSideConst.insets = new Insets(50, 50, 50, 50);
		leftSide.add(scroller, leftSideConst);
		
		//label for bombo box to select station to compare to
		JLabel compareWith = new JLabel("Compare with:");
		compareWith.setVisible(true);
		leftSideConst = new GridBagConstraints();
		leftSideConst.gridx = 0;
		leftSideConst.gridy = 5;
		leftSideConst.insets = new Insets(10, 10, 10, 10);
		leftSide.add(compareWith, leftSideConst);
		
		//Combo box to select which station to compare to
		DefaultComboBoxModel<String> model = getComboBoxModel();
		JComboBox<String> allStations = new JComboBox<String>(model);
		allStations.setVisible(true);
		leftSideConst = new GridBagConstraints();
		leftSideConst.gridx = 1;
		leftSideConst.gridy = 5;
		//leftSideConst.insets = new Insets(10, 10, 10, 10);
		leftSide.add(allStations, leftSideConst);
		
		JButton calcHD = new JButton("Calculate HD");
		calcHD.setSize(new Dimension(20, 50));
		leftSideConst = new GridBagConstraints();
		leftSideConst.gridx = 0;
		leftSideConst.gridy = 6;
		//leftSideConst.insets = new Insets(50, 50, 50, 50);
		leftSide.add(calcHD, leftSideConst);
		
		
		JPanel distances = new JPanel(new GridLayout(6, 2));
		distances.setVisible(true);
		leftSide.add(distances);
		
		//adds panel to frame
		this.add(leftSide);
		
		//makes everything visible
		this.setVisible(true);
		
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	@Override
	public void stateChanged(ChangeEvent event) {
		
	}
	
	public static void main (String [] args) throws IOException {
		new HammingDistanceFrame();
	}

}
