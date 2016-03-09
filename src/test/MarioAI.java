package test;


import ch.idsia.agents.Agent;
import ch.idsia.agents.controllers.BasicMarioAIAgent;
import ch.idsia.benchmark.mario.engine.sprites.Mario;
import datastructures.Graph;

public class MarioAI extends BasicMarioAIAgent implements Agent {
	
	float prevFloatPosX;
	private int iteration;
	private int prevLength;

	public MarioAI(String s) {
		super(s);
		prevFloatPosX = 0;
		iteration = 0;
		prevLength = 0;
	}
	
	@Override
	public boolean[] getAction() {
		
		if(iteration > 100) {
			
			if((int) marioFloatPos[0]/16 != prevLength) {
				
				//printWorld();
			}
			prevLength = (int) marioFloatPos[0]/16;
			
		}
		iteration++;
		return action;
	}

//	@Override
//	public boolean[] getAction() {
//		
//		
//		
//		// TODO Auto-generated method stub
//		action[Mario.KEY_RIGHT] = true;
////		if(enemiesInfront()) {
////			action[Mario.KEY_RIGHT] = false;
////			action[Mario.KEY_SPEED] = true; 
////		}
//		if((isMarioAbleToJump || !isMarioOnGround) && shouldJump()) {
//			action[Mario.KEY_JUMP] = true;
//		} 
//		else {
//			action[Mario.KEY_JUMP] = false;
//		}
//		
//		
//		for(int i = 0; i < mergedObservation.length; i++) {
//			for(int j = 0; j < mergedObservation[i].length; j++) {
//				System.out.printf("%3d",mergedObservation[i][j]);
//			}
//			System.out.println();
//		}
////		System.out.printf("x=%2f, y=%2f, %2d",marioFloatPos[0],marioFloatPos[1],getMarioYPos());
//		System.out.print("\n\n\n");
//		prevFloatPosX = logPrevPos();
//		if(iteration > 100000) {
//			iteration = 0;
//		} else {
//			iteration++;
//		}
//		return action;
//	}
//	
//	private float logPrevPos() {
//		if(iteration % 30 == 0) {
//			return marioFloatPos[0];
//		} else {
//			return prevFloatPosX;
//		}
//	}

	private void printWorld() {
		for(int i = 0; i < mergedObservation.length; i++) {
			for(int j = 0; j < mergedObservation.length; j++) {
				System.out.printf("%3d",mergedObservation[i][j]);
			}
			System.out.println();
		}
	}

	private boolean enemiesInfront() {
		if(isMarioAbleToShoot) {
			for(int i = enemies.length/2; i < enemies.length; i++) {
				if(enemies[enemies.length/2][i] != 0) return true;
			}
		}
		return false;
	}

	private boolean shouldJump() {
		if(isStuck()) { /*System.out.println("Stuck");*/ return true; }
		else if(mergedObservation[mergedObservation.length/2][mergedObservation.length/2+1] == 1 ||
				mergedObservation[mergedObservation.length/2][mergedObservation.length/2+2] == 1) return false;
		else if(mergedObservation[mergedObservation.length/2][mergedObservation.length/2+2] != 0 ||
			mergedObservation[mergedObservation.length/2][mergedObservation.length/2+1] != 0) return true;
		else return false;
	}
	
	private boolean isStuck() {
//		System.out.println("PrevX = "+(int) prevFloatPosX+", MarioFloatPos = "+(int) marioFloatPos[0]);
		if(((int) prevFloatPosX == (int) marioFloatPos[0]) && (action[Mario.KEY_RIGHT] || action[Mario.KEY_LEFT])) {
			return true;
		}
		return false;
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
