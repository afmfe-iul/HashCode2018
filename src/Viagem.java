public class Viagem implements Comparable<Viagem>{
	int id;
	int[] start;
	int[] end;
	int earliestStart;
	int latestFinish;
	int distanciaViagem;

//	a rides given: 3
//	b rides given: 200
//	c rides given: 5079
//	d rides given: 2113
//	e rides given: 5253
	public Viagem(int id, int[] start, int[] end, int earliestStart, int latestFinish) {
		super();
		this.id = id;
		this.start = start;
		this.end = end;
		this.earliestStart = earliestStart;
		this.latestFinish = latestFinish;
		distanciaViagem = Math.abs(start[0] - end[0]) + Math.abs(start[1] - end[1]);
	}

	public int margemDeManobra(){
		return latestFinish - (HC2018.TIME_ATUAL + distanciaViagem);
	}
	
	public int distanciaAteMim(int[] posDoCarro){
		return Math.abs(start[0] - posDoCarro[0]) + Math.abs(start[1] - posDoCarro[1]);
	}

	public int valorDaViagem(){
		return HC2018.TIME_ATUAL - earliestStart - 1 * margemDeManobra();
	}
	
	public int compareTo(Viagem o) {
//		return  valorDaViagem() - o.valorDaViagem();
		return o.margemDeManobra() - margemDeManobra();
	}
}