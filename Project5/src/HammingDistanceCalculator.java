import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
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
	
	/**
	 * Method to create a new station from the given one using a backwards alphabet
	 * @param station The given station
	 */
	public String backwardsAlphabetize(String station) {
		
		//puts all the letters and the letters in the opposite position into a hash map
		HashMap<String, String> letters = new HashMap<String, String>();
		letters.put("A", "Z");
		letters.put("B", "Y");
		letters.put("C", "X");
		letters.put("D", "W");
		letters.put("E", "V");
		letters.put("F", "U");
		letters.put("G", "T");
		letters.put("H", "S");
		letters.put("I", "R");
		letters.put("J", "Q");
		letters.put("K", "P");
		letters.put("L", "O");
		letters.put("M", "N");
		letters.put("N", "M");
		letters.put("O", "L");
		letters.put("P", "K");
		letters.put("Q", "J");
		letters.put("R", "I");
		letters.put("S", "H");
		letters.put("T", "G");
		letters.put("U", "F");
		letters.put("V", "E");
		letters.put("W", "D");
		letters.put("X", "C");
		letters.put("Y", "B");
		letters.put("Z", "A");
		letters.put("0", "9");
		letters.put("1", "8");
		letters.put("2", "7");
		letters.put("3", "6");
		letters.put("4", "5");
		letters.put("5", "4");
		letters.put("6", "3");
		letters.put("7", "2");
		letters.put("8", "1");
		letters.put("9", "1");
		
		//iterates through the given string and adds the opposite letter to a new string
		String scrambledString = "";
		for(int i = 0; i < station.length(); i++) {
			if(letters.containsKey(String.valueOf(station.charAt(i)))) {
				scrambledString += letters.get(String.valueOf(station.charAt(i)));
			}
		}
		//returns the new strong
		return scrambledString;
	}
	
}
