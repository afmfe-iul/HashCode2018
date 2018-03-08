package extendedRound;

import java.util.ArrayList;
import java.util.List;

public class Car {
	final Main main;
	final int[] pos;
	int currTime;
	List<Integer> ridesGiven;
	
	public Car(Main main) {
		this.main = main;
		pos = new int[]{0,0};
		currTime = 0;
		ridesGiven = new ArrayList<Integer>();
	}

	public String printRidesGiven() {
		String s = ridesGiven.size() + "";
		for(int i = 0; i< ridesGiven.size(); i++){
			s += " " + ridesGiven.get(i);
		}
		return s;
	}
}