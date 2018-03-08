package extendedRound;

public class Ride {
	int id;
	int[] start;
	int[] end;
	int earliestStart;
	int latestFinish;
	int rideDist;
	
	public Ride(int id, int[] start, int[] end, int earliestStart, int latestFinish) {
		super();
		this.id = id;
		this.start = start;
		this.end = end;
		this.earliestStart = earliestStart;
		this.latestFinish = latestFinish;
		rideDist = Math.abs(start[0] - end[0]) + Math.abs(start[1] - end[1]);
	}
	
	public int distanceFrom(int[] point){
		return Math.abs(start[0] - point[0]) + Math.abs(start[1] - point[1]);
	}

	public boolean canFinishOnTime(int[] point, int currTime){
		return latestFinish - (currTime + distanceFrom(point) + rideDist) > 0;
	}
	
	public boolean canCollectBonus(int[] point, int currTime){
		return earliestStart - (currTime + distanceFrom(point)) >= 0;
	}
}