package com.arnesi.wumpus.gamelogic;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardGameTest {

	private BoardGame boardGame;

	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@BeforeEach
	public void setUp() {
		System.setOut(new PrintStream(outputStreamCaptor));

		boardGame = new BoardGame();
		boardGame.initializeBoardGame();
	}

	@AfterEach
	public void tearDown() {
		boardGame = null;
	}

	@Test
	@DisplayName("Test boardActions() method - Happy Path, take gold and exit cave.")
	void boardActionsTestHappyPath1() {
		// Expected actions
		List<String> expectedActions = Arrays.asList("X=2|Y=0", "X=2|Y=1", "X=1|Y=1", "Gold", "Exit");

		// Crumb road for found gold and exit cave with gold.
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.TURN_RIGHT);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.TURN_LEFT);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.TURN_RIGHT);
		boardGame.boardActions(HunterActionsEnum.TURN_RIGHT);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.TURN_RIGHT);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		List<String> performedActions = Arrays.asList(outputStreamCaptor.toString().split(" "));

		boolean actions = checkExpectedActions(expectedActions, performedActions);
		boolean caveExit = boardGame.boardActions(HunterActionsEnum.EXIT);

		assertTrue(actions);
		assertTrue(caveExit);
	}
	
	@Test
	@DisplayName("Test boardActions() method - Happy Path, kill Wumpus, take gold and exit cave.")
	void boardActionsTestHappyPath2() {
		// Expected actions
		List<String> expectedActions = Arrays.asList("X=2|Y=0", "X=2|Y=1", "X=1|Y=1","Wumpus","scream", "Gold", "Exit");

		// Crumb road for found gold and exit cave with gold.
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.SHOOT);
		boardGame.boardActions(HunterActionsEnum.TURN_RIGHT);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.TURN_LEFT);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.TURN_RIGHT);
		boardGame.boardActions(HunterActionsEnum.TURN_RIGHT);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.TURN_RIGHT);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		List<String> performedActions = Arrays.asList(outputStreamCaptor.toString().split(" "));

		boolean actions = checkExpectedActions(expectedActions, performedActions);
		boolean caveExit = boardGame.boardActions(HunterActionsEnum.EXIT);

		assertTrue(actions);
		assertTrue(caveExit);
	}

	@Test
	@DisplayName("Test boardActions() method - Dead Hole at 3|2")
	void boardActionsTestDeadHole1() {
		// Expected actions
		List<String> expectedActions = Arrays.asList("RIGHT", "X=3|Y=1", "Breeze...", "X=3|Y=2", "falling",
				"Ahhhhhhhhhhh!!!!");

		// Crumb road for found gold and exit cave with gold.
		boardGame.boardActions(HunterActionsEnum.TURN_RIGHT);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);

		List<String> performedActions = Arrays.asList(outputStreamCaptor.toString().split(" "));

		boolean actions = checkExpectedActions(expectedActions, performedActions);
		boolean caveExit = boardGame.boardActions(HunterActionsEnum.EXIT);

		assertTrue(actions);
		assertFalse(caveExit);
	}

	@Test
	@DisplayName("Test boardActions() method - Dead Hole at 1|2")
	void boardActionsTestDeadHole2() {
		// Expected actions
		List<String> expectedActions = Arrays.asList("RIGHT", "X=3|Y=1", "Breeze...", "UP", "X=2|Y=1", "X=2|Y=2",
				"falling", "Ahhhhhhhhhhh!!!!");

		// Crumb road for found gold and exit cave with gold.
		boardGame.boardActions(HunterActionsEnum.TURN_RIGHT);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.TURN_LEFT);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.TURN_RIGHT);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.TURN_LEFT);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);

		List<String> performedActions = Arrays.asList(outputStreamCaptor.toString().split(" "));

		boolean actions = checkExpectedActions(expectedActions, performedActions);
		boolean caveExit = boardGame.boardActions(HunterActionsEnum.EXIT);

		assertTrue(actions);
		assertFalse(caveExit);
	}

	@Test
	@DisplayName("Test boardActions() method - Dead Hole at 0|3")
	void boardActionsTestDeadHole3() {
		// Expected actions
		List<String> expectedActions = Arrays.asList("RIGHT", "X=3|Y=1", "Breeze...", "UP", "X=2|Y=1", "X=1|Y=1",
				"Gold", "X=0|Y=1", "X=0|Y=2", "X=0|Y=3", "falling", "Ahhhhhhhhhhh!!!!");

		// Crumb road for found gold and exit cave with gold.
		boardGame.boardActions(HunterActionsEnum.TURN_RIGHT);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.TURN_LEFT);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.TURN_RIGHT);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);

		List<String> performedActions = Arrays.asList(outputStreamCaptor.toString().split(" "));

		boolean actions = checkExpectedActions(expectedActions, performedActions);
		boolean caveExit = boardGame.boardActions(HunterActionsEnum.EXIT);

		assertTrue(actions);
		assertFalse(caveExit);
	}

	@Test
	@DisplayName("Test boardActions() method - Dead by Wumpus at 1|0")
	void boardActionsTestDeadWumpus() {
		// Expected actions
		List<String> expectedActions = Arrays.asList("X=2|Y=0", "smell", "X=1|Y=0", "God");
		// Crumb road for found gold and exit cave with gold.
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);

		List<String> performedActions = Arrays.asList(outputStreamCaptor.toString().split(" "));

		boolean actions = checkExpectedActions(expectedActions, performedActions);
		boolean caveExit = boardGame.boardActions(HunterActionsEnum.EXIT);

		assertTrue(actions);
		assertFalse(caveExit);
	}

	@Test
	@DisplayName("Test boardActions() method - Try to exit without gold.")
	void boardActionsTestExitWithoutGold() {
		// Expected actions
		List<String> expectedActions = Arrays.asList("X=2|Y=0", "Wunpus", "X=2|Y=1", "X=2|Y=2", "Breeze...", "Exit...");

		// Crumb road for found gold and exit cave with gold.
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.TURN_RIGHT);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.TURN_LEFT);
		boardGame.boardActions(HunterActionsEnum.TURN_LEFT);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.TURN_LEFT);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.EXIT);

		List<String> performedActions = Arrays.asList(outputStreamCaptor.toString().split(" "));

		boolean actions = checkExpectedActions(expectedActions, performedActions);
		boolean caveExit = boardGame.boardActions(HunterActionsEnum.EXIT);

		assertTrue(actions);
		assertFalse(caveExit);
	}
	
	@Test
	@DisplayName("Test boardActions() method - Try to leave the cave without being at the exit")
	void boardActionsTestExitWithoutBeingAtExit() {
		// Expected actions
		List<String> expectedActions = Arrays.asList("X=2|Y=0", "Wunpus", "direction:UP");

		// Crumb road for found gold and exit cave with gold.
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.EXIT);

		List<String> performedActions = Arrays.asList(outputStreamCaptor.toString().split(" "));

		boolean actions = checkExpectedActions(expectedActions, performedActions);
		boolean caveExit = boardGame.boardActions(HunterActionsEnum.EXIT);

		assertTrue(actions);
		assertFalse(caveExit);
	}
	
	@Test
	@DisplayName("Test boardActions() method - Try to exit without any move.")
	void boardActionsTestExitWithoutAnyMove() {
		// Expected actions
		List<String> expectedActions = Arrays.asList("");

		// Crumb road for found gold and exit cave with gold.
		boardGame.boardActions(HunterActionsEnum.EXIT);

		List<String> performedActions = Arrays.asList(outputStreamCaptor.toString().split(" "));

		boolean actions = checkExpectedActions(expectedActions, performedActions);
		boolean caveExit = boardGame.boardActions(HunterActionsEnum.EXIT);

		assertTrue(actions);
		assertFalse(caveExit);
	}
	
	@Test
	@DisplayName("Test boardActions() method - Breeze perception at 2|2")
	void boardActionsTestBreezePerception() {
		// Expected actions
		List<String> expectedActions = Arrays.asList("X=2|Y=0", "Wunpus", "X=2|Y=1", "X=2|Y=2", "Breeze...");

		// Crumb road for found gold and exit cave with gold.
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.TURN_RIGHT);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);

		List<String> performedActions = Arrays.asList(outputStreamCaptor.toString().split(" "));

		boolean actions = checkExpectedActions(expectedActions, performedActions);
		boolean caveExit = boardGame.boardActions(HunterActionsEnum.EXIT);

		assertTrue(actions);
		assertFalse(caveExit);
	}
	
	@Test
	@DisplayName("Test boardActions() method - Wumpus Stench perception at 2|0")
	void boardActionsTestWumpusStench() {
		// Expected actions
		List<String> expectedActions = Arrays.asList("X=2|Y=0", "smell", "Wunpus", "Stench...");
		
		// Crumb road for found gold and exit cave with gold.
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);

		List<String> performedActions = Arrays.asList(outputStreamCaptor.toString().split(" "));

		boolean actions = checkExpectedActions(expectedActions, performedActions);
		boolean caveExit = boardGame.boardActions(HunterActionsEnum.EXIT);

		assertTrue(actions);
		assertFalse(caveExit);
	}
	
	@Test
	@DisplayName("Test boardActions() method - Hunter rotation 360º Right")
	void boardActionsTestHunterRotationRight() {
		// Expected actions
		List<String> expectedActions = Arrays.asList("Direction:", "RIGHT", "DOWN", "LEFT", "UP");

		// Crumb road for found gold and exit cave with gold.
		boardGame.boardActions(HunterActionsEnum.TURN_RIGHT);
		boardGame.boardActions(HunterActionsEnum.TURN_RIGHT);
		boardGame.boardActions(HunterActionsEnum.TURN_RIGHT);
		boardGame.boardActions(HunterActionsEnum.TURN_RIGHT);

		List<String> performedActions = Arrays.asList(outputStreamCaptor.toString().split(" "));

		boolean actions = checkExpectedActions(expectedActions, performedActions);
		boolean caveExit = boardGame.boardActions(HunterActionsEnum.EXIT);

		assertTrue(actions);
		assertFalse(caveExit);
	}
	
	@Test
	@DisplayName("Test boardActions() method - Hunter rotation 360º Left")
	void boardActionsTestHunterRotationLeft() {
		// Expected actions
		List<String> expectedActions = Arrays.asList("Direction:", "RIGHT", "DOWN", "LEFT", "UP");

		// Crumb road for found gold and exit cave with gold.
		boardGame.boardActions(HunterActionsEnum.TURN_LEFT);
		boardGame.boardActions(HunterActionsEnum.TURN_LEFT);
		boardGame.boardActions(HunterActionsEnum.TURN_LEFT);
		boardGame.boardActions(HunterActionsEnum.TURN_LEFT);

		List<String> performedActions = Arrays.asList(outputStreamCaptor.toString().split(" "));

		boolean actions = checkExpectedActions(expectedActions, performedActions);
		boolean caveExit = boardGame.boardActions(HunterActionsEnum.EXIT);

		assertTrue(actions);
		assertFalse(caveExit);
	}
	
	@Test
	@DisplayName("Test boardActions() method - Wall perception at 2|3")
	void boardActionsTestWallPerception() {
		// Expected actions
		List<String> expectedActions = Arrays.asList("X=2|Y=0", "Wunpus", "X=2|Y=1", "X=2|Y=2", "Breeze...", "hit", "wall!!");

		// Crumb road for found gold and exit cave with gold.
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.TURN_RIGHT);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);
		boardGame.boardActions(HunterActionsEnum.GO_FORWARD);

		List<String> performedActions = Arrays.asList(outputStreamCaptor.toString().split(" "));

		boolean actions = checkExpectedActions(expectedActions, performedActions);
		boolean caveExit = boardGame.boardActions(HunterActionsEnum.EXIT);

		assertTrue(actions);
		assertFalse(caveExit);
	}

	private static boolean checkExpectedActions(List<String> expectedActions, List<String> performedActions) {
		return CollectionUtils.containsAny(expectedActions, performedActions);
	}
}