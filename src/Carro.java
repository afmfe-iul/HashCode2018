import java.util.ArrayList;
import java.util.List;

public class Carro implements Comparable<Carro>{
	final int[] pos;
	int readyAtTime;
	List<Integer> viagens;
	int id;
	 
	public Carro(HC2018 main, int i) {
		id = i;
		pos = new int[]{0,0};
		readyAtTime = 1;
		viagens = new ArrayList<Integer>();
	}
	
	public void adicionarViagem(Viagem v){
		pos[0] = v.end[0];
		pos[1] = v.end[1];
		int tempoEspera = Math.max(v.earliestStart - (HC2018.TIME_ATUAL + v.distanciaAteMim(pos)), 0);
		readyAtTime = HC2018.TIME_ATUAL + v.distanciaAteMim(pos) + v.distanciaViagem + tempoEspera;
		viagens.add(v.id);
	}
	
	public String printViagens(){
		String s = viagens.size() + "";
		for(int i = 0; i< viagens.size(); i++){
			s += " " + viagens.get(i);
		}
		return s;
	}
	
	public int valorDeFazerAViagem(Viagem v){
		int dist = v.distanciaAteMim(pos);
		if(v.margemDeManobra() - dist <= 0){
			return 0;
		}
		
		int tempoEspera = v.earliestStart - (HC2018.TIME_ATUAL + dist); 
		int haBonus = tempoEspera >= 0 ? 10 : 0;
		return HC2018.BONUS * haBonus + 1 * v.valorDaViagem() - 1 * tempoEspera; 
	}

	public int compareTo(Carro o) {
		return readyAtTime - o.readyAtTime;
	}
}
