package extendedRound;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	final List<Ride> rides = new ArrayList<Ride>();
	final List<Car> cars = new ArrayList<Car>();
	int bonus;
	int finalTime;
	int totalRidesGiven;
	
	public Main(String fileName) {
		try {
			readFile(new File(fileName));
			magicHappens();
			writeFile(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void magicHappens() {
		// TODO
	}
	
	// TODO
	private int getTotalRidesGiven(){
		return totalRidesGiven;
	}
	
	private void readFile(File file) throws FileNotFoundException {
		Scanner scn = new Scanner(file);
		String[] s = scn.nextLine().split(" ");
		bonus = Integer.parseInt(s[4]);
		finalTime = Integer.parseInt(s[5]);
		int numbCars = Integer.parseInt(s[2]);
		for (int i = 0; i < numbCars; i++) {
			Car c = new Car(this);
			cars.add(c);
		}

		for (int i = 0; scn.hasNextLine(); i++) {
			String[] r = scn.nextLine().split(" ");
			rides.add(new Ride(i, new int[] { Integer.parseInt(r[0]), Integer.parseInt(r[1]) },
					new int[] { Integer.parseInt(r[2]), Integer.parseInt(r[3]) }, Integer.parseInt(r[4]),
					Integer.parseInt(r[5])));
		}
		scn.close();
	}

	private void writeFile(String filename) throws IOException {
		String s = "";
		String new_line = System.getProperty("line.separator");

		for (int i = 0; i < cars.size(); i++) {
			s += cars.get(i).printRidesGiven() + new_line;
		}

		File file = new File("res_" + filename);
		file.createNewFile();
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
			out.write(s);
			out.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) throws IOException {
		int algorithmTotal = 0;
		Main hc = new Main("a_example.in");
		System.out.println("a total: " + hc.getTotalRidesGiven());
		algorithmTotal += hc.getTotalRidesGiven();
		
		hc = new Main("b_should_be_easy.in");
		System.out.println("b total: " + hc.getTotalRidesGiven());
		algorithmTotal += hc.getTotalRidesGiven();
		
		hc = new Main("c_no_hurry.in");
		System.out.println("c total: " + hc.getTotalRidesGiven());
		algorithmTotal += hc.getTotalRidesGiven();
		
		hc = new Main("d_metropolis.in");
		System.out.println("d total: " + hc.getTotalRidesGiven());
		algorithmTotal += hc.getTotalRidesGiven();
		
		hc = new Main("e_high_bonus.in");
		System.out.println("e total: " + hc.getTotalRidesGiven());
		algorithmTotal += hc.getTotalRidesGiven();
		
		System.out.println("Total rides given: " + algorithmTotal);
	}
}
