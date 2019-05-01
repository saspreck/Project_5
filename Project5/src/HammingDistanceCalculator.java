/**
 * Class to calculate appropriate hamming distances
 * @author skylersprecker
 * @version 2019-30-19
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class HammingDistanceCalculator {
	
	String selectedStation;
	int selectedDistance;
	int[] stationHammDist;
	
	public HammingDistanceCalculator(String selectedStation, int selectedDistance) {
		this.selectedStation = selectedStation;
		this.selectedDistance = selectedDistance;
		stationHammDist = new int[4];
	}

}
