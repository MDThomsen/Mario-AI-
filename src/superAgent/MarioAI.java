package superAgent;

import org.hamcrest.core.IsNot;

import ch.idsia.agents.Agent;
import ch.idsia.agents.controllers.BasicMarioAIAgent;
import ch.idsia.benchmark.mario.engine.sprites.Mario;
import ch.idsia.benchmark.mario.environments.Environment;

public class MarioAI extends BasicMarioAIAgent implements Agent {

	public MarioAI(String s) {
		super(s);
	}

	@Override
	public boolean[] getAction() {
		// TODO Auto-generated method stub
		action[Mario.KEY_RIGHT] = true;
		if(enemiesInfront()) {
			action[Mario.KEY_RIGHT] = false;
			action[Mario.KEY_SPEED] = true; 
		}
		else if((isMarioAbleToJump || !isMarioOnGround) && shouldJump()) {
			action[Mario.KEY_JUMP] = true;
		} 
		else {
			action[Mario.KEY_JUMP] = false;
		}
//		for(int i = 0; i < mergedObservation.length; i++) {
//			for(int j = 0; j < mergedObservation[i].length; j++) {
//				System.out.printf("%3d",mergedObservation[i][j]);
//			}
//			System.out.println();
//		}
//		System.out.printf("x=%2f, y=%2f, %2d",marioFloatPos[0],marioFloatPos[1],getMarioYPos());
		System.out.print("\n\n\n");
		return action;
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
		if(mergedObservation[mergedObservation.length/2][mergedObservation.length/2+1] == 1 ||
				mergedObservation[mergedObservation.length/2][mergedObservation.length/2+2] == 1) return false;
		else if(mergedObservation[mergedObservation.length/2][mergedObservation.length/2+2] != 0 ||
			mergedObservation[mergedObservation.length/2][mergedObservation.length/2+1] != 0) {
					return true;
				}
		else if(mergedObservation[mergedObservation.length/2+1][mergedObservation.length/2+1] == 0) return true;
		else return false;
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
