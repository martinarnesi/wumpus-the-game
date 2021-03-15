package com.arnesi.wumpus.gamelogic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.arnesi.wumpus.model.Hunter;
import com.arnesi.wumpus.model.HunterEnum;

class HunterActionsTest {
	private static final String MOVE = "Moving...";
	private static final String WALL_SHOCK_PERCEPTION = "Hunter say: I hit the cave wall!!";
	private static final String INVALID = "Invalid movement.";
	
	private int boardWidth;
	private int boardHeight;

	private HunterActions hunterActions;
	
	@BeforeEach
	public void setUp() {
		boardWidth = 4;
		boardHeight = 4;
		hunterActions = new HunterActions();
	}

	@AfterEach
	public void tearDown() {
		hunterActions = null;
	}

	@Test
	@DisplayName("Test moveHunterForward() method - Happy Path")
	void moveHunterForwardTestHappyPath() {
		
		Hunter hunter = new Hunter(1,1);
		hunter.setDirection(HunterEnum.UP);
		String hunterMoveUp = hunterActions.moveHunterForward(hunter, boardWidth, boardHeight);
		
		hunter = new Hunter(1,1);
		hunter.setDirection(HunterEnum.DOWN);
		String hunterMoveDown = hunterActions.moveHunterForward(hunter, boardWidth, boardHeight);
		
		hunter = new Hunter(1,1);
		hunter.setDirection(HunterEnum.LEFT);
		String hunterMoveLeft = hunterActions.moveHunterForward(hunter, boardWidth, boardHeight);

		hunter = new Hunter(1,0);
		hunter.setDirection(HunterEnum.RIGHT);
		String hunterMoveRight = hunterActions.moveHunterForward(hunter, boardWidth, boardHeight);
		
		assertEquals(MOVE, hunterMoveUp);
		assertEquals(MOVE, hunterMoveDown);
		assertEquals(MOVE, hunterMoveLeft);
		assertEquals(MOVE, hunterMoveRight);
	}
	
	
	@Test
	@DisplayName("Test turnHunter() method - Happy Path")
	void turnHunterTestHappyPath() {
		assertTrue(true);
	}
	
	@Test
	@DisplayName("Test hunterExitCave() method - Happy Path")
	void hunterExitCaveTestHappyPath() {
		assertTrue(true);
	}
	
	@Test
	@DisplayName("Test updateHunterStatus() method - Happy Path")
	void updateHunterStatusTestHappyPath() {
		assertTrue(true);
	}
	
	@Test
	@DisplayName("Test hunterPerception() method - Happy Path")
	void hunterPerceptionTestHappyPath() {
		assertTrue(true);
	}
}