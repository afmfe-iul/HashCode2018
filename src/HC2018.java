import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HC2018 {
	final List<Viagem> rides = new ArrayList<Viagem>();
	final PriorityQueue<Carro> cars = new PriorityQueue<Carro>();
	final List<Carro> praImprimir = new ArrayList<Carro>();
	static int TIME_FINAL;
	static int BONUS;
	static int TIME_ATUAL;

	public HC2018(String filename) {
		TIME_ATUAL = 1;
		try {
			readFile(new File(filename));
			fazMagia(filename);
			escreveFicheiro(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void fazMagia(String filename) {
		int ridesGiven = 0;
		for (; TIME_ATUAL < TIME_FINAL && !rides.isEmpty(); TIME_ATUAL++) {
			List<Carro> prontos = new ArrayList<Carro>();
			while(!cars.isEmpty() && cars.peek().readyAtTime == TIME_ATUAL){
				prontos.add(cars.remove());
			}
			
			for(int i = 0; i < prontos.size() && !rides.isEmpty(); i++){
				Carro c = prontos.get(i);
				Viagem best = rides.get(0);
				int dist = best.distanciaAteMim(c.pos);
				int lowestLifeTime = best.margemDeManobra() - dist;
//				int bestScore = best.distanciaViagem + BONUS * (best.earliestStart - (HC2018.TIME_ATUAL + dist) >= 0 ? 1 : 0);
				int index = 0;
				for(int j = 1; j < rides.size(); j++){
					Viagem v = rides.get(j);
					dist = v.distanciaAteMim(c.pos);
					int lifeTime = v.margemDeManobra() - dist;
					if(lifeTime <= 0){
						rides.remove(j);
					}else{
//						int score = v.distanciaViagem + BONUS * (v.earliestStart - (HC2018.TIME_ATUAL + dist) >= 0 ? 1 : 0);
						if(lifeTime <= lowestLifeTime){
							best = v;
							lowestLifeTime = lifeTime;
//							bestScore = score;
							index = j;
						}
					}
				}
				ridesGiven++;
				c.adicionarViagem(best);
				rides.remove(index);
				cars.add(c);
			}
		}
		System.out.println(filename.charAt(0) + " rides given: " + ridesGiven);
	}

	public void readFile(File file) throws FileNotFoundException {
		Scanner scn = new Scanner(file);
		String[] s = scn.nextLine().split(" ");
		BONUS = Integer.parseInt(s[4]);
		TIME_FINAL = Integer.parseInt(s[5]);
		int numbCars = Integer.parseInt(s[2]);
		for (int i = 0; i < numbCars; i++) {
			Carro c = new Carro(this, i);
			cars.add(c);
			praImprimir.add(c);
		}

		for (int i = 0; scn.hasNextLine(); i++) {
			String[] r = scn.nextLine().split(" ");
			rides.add(new Viagem(i, new int[] { Integer.parseInt(r[0]), Integer.parseInt(r[1]) },
					new int[] { Integer.parseInt(r[2]), Integer.parseInt(r[3]) }, Integer.parseInt(r[4]),
					Integer.parseInt(r[5])));
		}
		scn.close();
	}

	public void escreveFicheiro(String filename) throws IOException {
		String s = "";
		String new_line = System.getProperty("line.separator");

		for (int i = 0; i < praImprimir.size(); i++) {
			s += praImprimir.get(i).printViagens() + new_line;
		}

		File file = new File("resultado_" + filename);
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
		@SuppressWarnings("unused")
		HC2018 hc = new HC2018("a_example.in");
		hc = new HC2018("b_should_be_easy.in");
		hc = new HC2018("c_no_hurry.in");
		hc = new HC2018("d_metropolis.in");
		hc = new HC2018("e_high_bonus.in");
	}
}
