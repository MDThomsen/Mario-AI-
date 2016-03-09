package ch.idsia.agents.controllers.human;

import ch.idsia.agents.Agent;
import ch.idsia.benchmark.mario.engine.sprites.Mario;
import ch.idsia.benchmark.mario.environments.Environment;
import datastructures.Graph;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA. User: Sergey Karakovskiy Date: Mar 29, 2009 Time:
 * 12:19:49 AM Package: ch.idsia.controllers.agents.controllers;
 */
public class HumanKeyboardAgent extends KeyAdapter implements Agent {
	List<boolean[]> history = new ArrayList<boolean[]>();
	private boolean[] Action = null;
	private String Name = "HumanKeyboardAgent";

	/* final */
	protected byte[][] levelScene;
	/* final */
	protected byte[][] enemies;
	protected byte[][] mergedObservation;

	protected float[] marioFloatPos = null;
	protected float[] enemiesFloatPos = null;

	protected int[] marioState = null;

	protected int marioStatus;
	protected int marioMode;
	protected boolean isMarioOnGround;
	protected boolean isMarioAbleToJump;
	protected boolean isMarioAbleToShoot;
	protected boolean isMarioCarrying;
	protected int getKillsTotal;
	protected int getKillsByFire;
	protected int getKillsByStomp;
	protected int getKillsByShell;
	// values of these variables could be changed during the Agent-Environment
	// interaction.
	// Use them to get more detailed or less detailed description of the level.
	// for information see documentation for the benchmark <link:
	// marioai.org/marioaibenchmark/zLevels
	int zLevelScene = 1;
	int zLevelEnemies = 0;

	private Graph g;

	public HumanKeyboardAgent() {
		this.reset();

		// RegisterableAgent.registerAgent(this);
	}

	public int counter = 0;

	public void addGraph() {
		g = new Graph();
		System.out.println(levelScene);
		g.initGraph(levelScene);
		g.printGraph();

	}

	private int prevXpos;
	private int prevYpos;
	private int startX;
	private int startY;

	public boolean[] getAction() {
		int limit = 40;
		if (counter < limit) {
			System.out.println("waiting");
			counter++;
		}
		else if (counter == limit) {
			System.out.println("graph");
			addGraph();
			startX = (int) marioFloatPos[0]/16;
			startY = (int) marioFloatPos[1]/16;
			System.out.println(startX + " "+ startY);
			counter++;
		}
		if(hasMoved(prevXpos,prevYpos) && counter > limit) {
			g.expandFrontier(levelScene, Math.abs((int) marioFloatPos[0]/16 - startX),(int) marioFloatPos[1]/16 - startY);
			g.printArrayWithNodes(levelScene, Math.abs((int) marioFloatPos[0]/16 - startX), (int) marioFloatPos[1]/16 - startY);
			System.out.println();
			g.printArray(levelScene);
			g.printGraph();
		}

		prevXpos=(int) marioFloatPos[0]/16;
		prevYpos=(int) marioFloatPos[1]/16;
		return Action;
	}

	private boolean hasMoved(int prevXpos, int prevYpos) {
		int xPos = (int) marioFloatPos[0]/16;
		int yPos = (int) marioFloatPos[1]/16;
		
		return (xPos != prevXpos || yPos != prevYpos);
	}
	public void integrateObservation(Environment environment) {
		levelScene = environment.getLevelSceneObservationZ(zLevelScene);
		enemies = environment.getEnemiesObservationZ(zLevelEnemies);
		mergedObservation = environment.getMergedObservationZZ(1, 0);

		this.marioFloatPos = environment.getMarioFloatPos();
		this.enemiesFloatPos = environment.getEnemiesFloatPos();
		this.marioState = environment.getMarioState();

		// It also possible to use direct methods from Environment interface.
		//
		marioStatus = marioState[0];
		marioMode = marioState[1];
		isMarioOnGround = marioState[2] == 1;
		isMarioAbleToJump = marioState[3] == 1;
		isMarioAbleToShoot = marioState[4] == 1;
		isMarioCarrying = marioState[5] == 1;
		getKillsTotal = marioState[6];
		getKillsByFire = marioState[7];
		getKillsByStomp = marioState[8];
		getKillsByShell = marioState[9];
	}

	public void giveIntermediateReward(float intermediateReward) {

	}

	public void reset() {
		// Just check you keyboard. Especially arrow buttons and 'A' and 'S'!
		Action = new boolean[Environment.numberOfButtons];
	}

	public boolean[] getAction(Environment observation) {
		float[] enemiesPos = observation.getEnemiesFloatPos();
		return Action;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public void keyPressed(KeyEvent e) {
		toggleKey(e.getKeyCode(), true);
	}

	public void keyReleased(KeyEvent e) {
		toggleKey(e.getKeyCode(), false);
	}

	private void toggleKey(int keyCode, boolean isPressed) {
		switch (keyCode) {
		case KeyEvent.VK_LEFT:
			Action[Mario.KEY_LEFT] = isPressed;
			break;
		case KeyEvent.VK_RIGHT:
			Action[Mario.KEY_RIGHT] = isPressed;
			break;
		case KeyEvent.VK_DOWN:
			Action[Mario.KEY_DOWN] = isPressed;
			break;
		case KeyEvent.VK_UP:
			Action[Mario.KEY_UP] = isPressed;
			break;

		case KeyEvent.VK_S:
			Action[Mario.KEY_JUMP] = isPressed;
			break;
		case KeyEvent.VK_A:
			Action[Mario.KEY_SPEED] = isPressed;
			break;
		}
	}

	public List<boolean[]> getHistory() {
		return history;
	}
}
