package ch.idsia.agents.controllers;

import ch.idsia.agents.Agent;
import ch.idsia.benchmark.mario.engine.sprites.Mario;
import ch.idsia.benchmark.mario.environments.Environment;

public class TestAI extends BasicMarioAIAgent implements Agent {

	public TestAI() {
		super("TestAI");
		reset();
		
	}
	
    public boolean[] getAction()
    {
    	
    	for(int i = 0; i < mergedObservation.length; i++){
    		for(int j = 0; j < mergedObservation.length; j++){
    			System.out.printf("%3d", mergedObservation[i][j]);
    		}
    		System.out.println();
    	}
    	System.out.println();
    	
    	
    	action[Mario.KEY_RIGHT] = true;
        action[Mario.KEY_SPEED] = true;
       // action[Mario.KEY_JUMP] = isMarioOnGround && isMarioAbleToJump;
    	int x = mergedObservation.length/2;
    	System.out.println(enemiesInfront());
    	if(!enemiesInfront()){
    		if(levelScene[x][x+1] != 0 && (isMarioAbleToJump || !isMarioOnGround)){
    			action[Mario.KEY_JUMP] = true;
    			action[Mario.KEY_SPEED] = false;
    		} else {
    			action[Mario.KEY_JUMP] = false;
    			action[Mario.KEY_SPEED] = true;
    		}    		
    	}
    	if(enemiesInfront()&& isMarioAbleToShoot){
    		action[Mario.KEY_SPEED] = false;
    		action[Mario.KEY_RIGHT] = false;
    	} else {
    		action[Mario.KEY_SPEED] = true;
    		action[Mario.KEY_RIGHT] = true;
    	}
    	
        
        
        return action;
    }

    public void reset()
    {
        action = new boolean[Environment.numberOfButtons];
        action[Mario.KEY_RIGHT] = true;
        action[Mario.KEY_SPEED] = true;
    }
    
    private Boolean enemiesInfront(){
    	Boolean r = false;
    	for(int i = enemies.length/2; i < (enemies.length/2)+3; i++){
    		if(enemies[enemies.length/2][i] != 0){
    			r = true;
    		}
    	}
    	return r;
    }
    
}
