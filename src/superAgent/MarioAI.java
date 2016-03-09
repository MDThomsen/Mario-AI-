package superAgent;

import java.awt.List;
import java.util.ArrayList;

import ch.idsia.agents.Agent;
import ch.idsia.agents.controllers.BasicMarioAIAgent;
import ch.idsia.benchmark.mario.engine.sprites.Mario;

public class MarioAI extends BasicMarioAIAgent implements Agent {
	float PrevX;
	int enemyPos;
	int timesStuck;
	ArrayList<Action> actionList;
	
	public MarioAI(String s) {
		super(s);
		actionList = new ArrayList<>();
		actionList.add(new Action(Mario.KEY_RIGHT,8));
		actionList.add(new Action(Mario.KEY_JUMP,20));
		
	}

	@Override
	public boolean[] getAction() {
		
		int length = (int) (marioFloatPos[0]/16);
		// TODO Auto-generated method stub
		
		//System.out.println(actionList.get(0).getDist());
		//System.out.println(length);
		//System.out.println("fake " +actionList.get(0).getDir());
		//System.out.println("real "+Mario.KEY_RIGHT);
		if(!actionList.isEmpty()){
			if(actionList.get(0).getDist() <= length){
				action[actionList.get(0).getDir()] = false;
				actionList.remove(0);
				
			} else {
				if(actionList.get(0).getDir() == Mario.KEY_JUMP && isMarioAbleToJump || !isMarioOnGround){
					action[actionList.get(0).getDir()] = true;					
				} else
					action[actionList.get(0).getDir()] = false;
				if(!(actionList.get(0).getDir() == Mario.KEY_JUMP)){
					action[actionList.get(0).getDir()] = true;
				}
			}			
		}
		
		
		/*boolean enemyInfront = enemiesInfront();
		System.out.println(enemyInfront);
		if(enemyInfront && isMarioAbleToShoot) {
			System.out.println("in here");
			action[Mario.KEY_JUMP] = isMarioAbleToJump;
			action[Mario.KEY_RIGHT] = false;
			action[Mario.KEY_SPEED] = isMarioAbleToShoot; 				
		}

		else if(((isMarioAbleToJump || !isMarioOnGround) && shouldJump())) {
			action[Mario.KEY_JUMP] = true;
			action[Mario.KEY_RIGHT] = true;
			action[Mario.KEY_SPEED] = false;
		} 
		else {
			action[Mario.KEY_JUMP] = false;
			action[Mario.KEY_RIGHT] = true;
			action[Mario.KEY_SPEED] = true;
		}
		
		for(int i = 0; i < enemies.length; i++) {
			for(int j = 0; j < enemies[i].length; j++) {
				System.out.printf("%3d",enemies[i][j]);
			}
			System.out.println();
		}
//		System.out.printf("x=%2f, y=%2f, %2d",marioFloatPos[0],marioFloatPos[1],getMarioYPos());
//		System.out.print("\n\n\n");
		PrevX = marioFloatPos[0];*/
		return action;
	}
	


	private boolean enemiesInfront() {
		
		boolean t = false;
			for(int j = 0; j < enemies.length; j++){
				for(int i = enemies[j].length/2; i < enemies[j].length/2+5; i++) {
					if(enemies[j][i] != 0 && enemies[j][i] != 25){
						t = true;
						timesStuck++;
					}
				}
			}
			if(timesStuck > 50){
				if(enemyBelow())
					t = false;
				else {
					timesStuck = 0;
					t = false;
				}
				
			}
		return t;
	}
	
	private boolean flowerAround(){
		for(int j = enemies.length/2; j < enemies.length; j++){
			for(int i = enemies[j].length/2; i < enemies[j].length/2+5; i++) {
				if(enemies[j][i] != 0 && enemies[j][i] != 25){
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean enemyBelow(){
		for(int j = enemies.length/2; j < enemies.length; j++){
			for(int i = enemies[j].length/2; i < enemies[j].length/2+5; i++) {
				if(enemies[j][i] != 0 && enemies[j][i] != 25){
					return true;
				}
			}
		}
		return false;
	}
	private boolean shouldJump() {
		if (isStuck()){
			return true;
		} else if(mergedObservation[mergedObservation.length/2][mergedObservation.length/2+1] == 1 ||
				mergedObservation[mergedObservation.length/2][mergedObservation.length/2+2] == 1) return false;
		else if(mergedObservation[mergedObservation.length/2][mergedObservation.length/2+2] != 0 ||
			mergedObservation[mergedObservation.length/2][mergedObservation.length/2+1] != 0) {
					return true;
				}
		else return false;
	}
	
	private boolean isStuck(){
		return ((int) marioFloatPos[0] == (int) PrevX);
		
	}

	@Override
	public void giveIntermediateReward(float intermediateReward) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		if(!name.isEmpty()) {			
			this.name = name;
		} else {
			throw new IllegalArgumentException("Empty Agent name");
		}
	}

}
