package superAgent;

public class Action {
	private int Dir,Dist;
	 
	public Action(int Dir, int toDist){
		this.setDir(Dir);
		this.setDist(toDist);
	}

	public int getDir() {
		return Dir;
	}

	public void setDir(int dir) {
		Dir = dir;
	}

	public int getDist() {
		return Dist;
	}

	public void setDist(int toDist) {
		this.Dist = toDist;
	}


	
	
}
