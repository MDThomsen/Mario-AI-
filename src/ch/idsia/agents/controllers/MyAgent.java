package ch.idsia.agents.controllers;

import ch.idsia.agents.Agent;
import ch.idsia.benchmark.mario.engine.sprites.Mario;
import ch.idsia.benchmark.mario.environments.Environment;

public class MyAgent extends BasicMarioAIAgent implements Agent {

	public MyAgent() {
		super("My agent");
		reset();
	}
	public int i= 0;
	public boolean[] getAction()
	{
		/*System.out.println("x: "+ (int) marioFloatPos[0]/16 + " y: "+ (int) marioFloatPos[1]/16);
		
		i++;
		System.out.println(i);
		if (i == 10 && isMarioOnGround ) {
			System.out.println((int) marioFloatPos[0]/16);
			action[Mario.KEY_JUMP] = true;
			
		}
		else if (i > 10 && isMarioOnGround) {
			System.out.println(marioFloatPos[0]/16);
			action[Mario.KEY_JUMP] = false;
			action[Mario.KEY_RIGHT] = false;
		}
	
		if (isMarioAbleToJump ||  !isMarioOnGround) {
			action[Mario.KEY_JUMP] = true;
		}
		else{
			action[Mario.KEY_JUMP] = false;
		}*/
		if ((isMarioAbleToJump || !isMarioOnGround) && (gapAhead() || obstacleAhead())) {
			
			action[Mario.KEY_JUMP] = true;
		}
		else {
			action[Mario.KEY_JUMP] = false;
			
		}/*
		for (int i = 0; i < mergedObservation[0].length; i++) {
		System.out.print(mergedObservation[(mergedObservation.length-1)/2][i] + " ");
		}
		System.out.println();
		System.out.println(mergedObservation[(mergedObservation.length-1)/2][ (mergedObservation[0].length-1)/2+1]);*/
		printArray (mergedObservation);
		return action;
			
		
	}
	public void reset()
    {
        action = new boolean[Environment.numberOfButtons];
		action[Mario.KEY_RIGHT] = true;
		action[Mario.KEY_SPEED] =true;
    }
	
	private boolean gapAhead() {
		boolean gap = true;
		for(int i=(mergedObservation.length) / 2; i < mergedObservation.length; i++) {
			if (mergedObservation[i][(mergedObservation.length) / 2 +1 ] != 0) {
				gap = false;
			}
		}
		return gap;
	}
	private boolean obstacleAhead() {
		boolean obstacle = false;
		for(int i= (mergedObservation.length)/2-1; i <= (mergedObservation.length)/2; i++) {
			for (int j = (mergedObservation[0].length)/2+1; j <= (mergedObservation[0].length)/2+2; j++) {
				if (mergedObservation[i][j] != 0 && mergedObservation[i][j] != 1) {
					obstacle = true;
					System.out.println("obstacle ahead");
					break;
				}
			}
		}
		
		return obstacle;
		
	}
	private boolean gapWithOneStepStairs() {
		boolean b = false;
		if (mergedObservation[mergedObservation.length/2-1][mergedObservation.length/2+2] != 0 && mergedObservation[mergedObservation.length/2-1][mergedObservation.length/2+1] == 0 ) {
			System.out.println("found step at " + (mergedObservation.length/2-1) + " , " + (mergedObservation.length/2+2));
			b = true;
			for (int j = (mergedObservation.length)/2 - 3; j < (mergedObservation.length); j++) {
				if (mergedObservation[j][(mergedObservation.length) / 2 +3 ] != 0) {
					System.out.println("not gap: "+ j + " , " + (mergedObservation.length / 2 +3));
					b = false;
					break;
				}
			}
		}
			
		return b;
		
	}
	

	
	
	
}
