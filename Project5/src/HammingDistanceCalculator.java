import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
/**
 * Class to calculate appropriate hamming distances
 * @author skylersprecker
 * @version 2019-30-19
 */
public class HammingDistanceCalculator {
	
	String selectedStation;
	int selectedDistance;
	int[] stationHammDist;
	
	/**
	 * Constructor for a HammingDistanceCalculator object
	 * @param selectedStation The station selected by the user
	 * @param selectedDistance The distance selected by the user
	 */
	public HammingDistanceCalculator(String selectedStation, int selectedDistance) {
		this.selectedStation = selectedStation;
		this.selectedDistance = selectedDistance;
		stationHammDist = new int[5];
	}
	
	/**
	 * Method to calculate the number of stations with respective hamming distances to the selected one
	 * @return stationHammDist
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public int[] calcHammingDistance() throws FileNotFoundException, IOException {
		
		//buffered reader to read in from the file
		BufferedReader bf = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/" + "Project5" + "/" + "Mesonet.txt"));
		
		//loops through the file reading in each line
		while(bf.ready()) {
			String currStation = bf.readLine();
			int count = 0;
			
			//iterates through the string char by char to find differences
			for (int i = 0; i < selectedStation.length(); i++) {
				if(selectedStation.charAt(i) != currStation.charAt(i)) {
					count++;
				}
			}
				
			/* checks if hamming distance equals values and 
			 * if so increases number the corresponding spot in the array
			 * to keep track of total number of times that distance appears
			 */
			if(count == 0) {
				stationHammDist[0]++;
			}
			if(count == 1) {
				stationHammDist[1]++;
			}
			if(count == 2) {
				stationHammDist[2]++;
			}
			if(count == 3) { 
				stationHammDist[3]++;
			}
			if(count == 4) {
				stationHammDist[4]++;
			}
			
			return stationHammDist;

		}
		bf.close();
		return stationHammDist;

	}
	
	/**
	 * Method to collect all stations with a hamming distance equal to the selected distance
	 * @return equalDistanceStations
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public ArrayList<String> selectedDistanceStations() throws FileNotFoundException, IOException {
		
		ArrayList<String> equalDistanceStations = new ArrayList<String>();
		//buffered reader to read in from the file
		BufferedReader bf = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/" + "Project5" + "/" + "Mesonet.txt"));
				
		//loops through the file reading in each line
		while(bf.ready()) {
			String currStation = bf.readLine();
			int count = 0;
			
			//iterates through the string char by char to find differences
			for (int i = 0; i < selectedStation.length(); i++) {
				if(selectedStation.charAt(i) != currStation.charAt(i)) {
					count++;
				}
			}
			
			if(count == selectedDistance) {
				equalDistanceStations.add(currStation);
			}
		}
		bf.close();
		return equalDistanceStations;
	}
	
}
