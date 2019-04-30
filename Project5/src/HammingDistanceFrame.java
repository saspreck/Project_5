import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.GridLayout;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Class to create a GUI for calculating Hamming Distance
 * @author skylersprecker
 * @version 2019-04-28
 */
public class HammingDistanceFrame extends JFrame implements MouseListener, ChangeListener {
	
	private static final int FRAME_WIDTH = 700;
	private static final int FRAME_HEIGHT = 700;
	
	/**
	 * Interactive panel that allows the user to 
	 */
	public HammingDistanceFrame() {
		//calls super constructors and sets size, visibility, and close operation
		super("Hamming Distance Frame");
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		JLabel hammDist = new JLabel();
		hammDist.setText("Enter Hamming Distance");
		hammDist.setBounds(100, 100, 200, 20);
		hammDist.setVisible(true);
		
		JTextField hammDistInfo = new JTextField();
		hammDistInfo.setEditable(false);
		hammDistInfo.setText("2");
		hammDistInfo.setBounds(325, 100, 50, 20);
		hammDistInfo.setVisible(true);
		
		JSlider compDist = new JSlider(0, 4);
		compDist.addChangeListener(this);
		compDist.setMajorTickSpacing(1);
		compDist.setPaintTicks(true);
		compDist.setPaintLabels(true);
		compDist.setSnapToTicks(true);
		compDist.setVisible(true);
		
		this.add(hammDist);
		this.add(hammDistInfo);
		this.add(compDist);
		
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
	
	public static void main (String [] args) {
		new HammingDistanceFrame();
	}

}
