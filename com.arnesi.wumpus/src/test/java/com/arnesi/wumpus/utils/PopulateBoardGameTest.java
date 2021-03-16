package com.arnesi.wumpus.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.arnesi.wumpus.exception.CreationException;
import com.arnesi.wumpus.model.CaveExit;
import com.arnesi.wumpus.model.EmptyTile;
import com.arnesi.wumpus.model.Gold;
import com.arnesi.wumpus.model.Hole;
import com.arnesi.wumpus.model.Hunter;
import com.arnesi.wumpus.model.Tile;
import com.arnesi.wumpus.model.Wumpus;
import com.arnesi.wumpus.parameters.GameParameters;

class PopulateBoardGameTest {

	private static final int BOARD_WIDTH = GameParameters.PARAMETRISED_BOARD_WIDTH;
	private static final int BOARD_HEIGHT = GameParameters.PARAMETRISED_BOARD_HEIGHT;

	private PopulateBoardGame populateBoardGame;
	private Tile[][] tileCave;

	@BeforeEach
	public void setUp() {
		populateBoardGame = new PopulateBoardGame();
		tileCave = new Tile[BOARD_WIDTH][BOARD_HEIGHT];
	}

	@AfterEach
	public void tearDown() {
		populateBoardGame = null;
		tileCave = null;
	}

	@Test
	@DisplayName("Test method resetBoardGame()- Happy Path.")
	void resetBoardGameTest() {
		boolean boardReset = populateBoardGame.resetBoardGame(tileCave);
		assertTrue(boardReset);
	}

	@Test
	@DisplayName("Test method resetBoardGame()- Board reset fail and have entities.")
	void resetBoardGameTestWithEntities() {
		populateBoardGame.resetBoardGame(tileCave);
		tileCave[2][2].addEntity(new EmptyTile(2, 2));

		boolean boardReset = populateBoardGame.resetBoardGame(tileCave);
		assertFalse(boardReset);
	}

	@Test
	@DisplayName("Test method setHunterInitialPosition()- Happy Path.")
	void setHunterInitialPositionTest() {
		boolean boardReset = populateBoardGame.resetBoardGame(tileCave);

		Hunter hunter = null;
		hunter = populateBoardGame.setHunterInitialPosition(hunter, tileCave);

		assertTrue(boardReset);
		assertNotNull(hunter);
		assertTrue(hunter.isAlive());
		assertFalse(hunter.isHaveGold());
		assertEquals(3, hunter.getxPosition());
		assertEquals(0, hunter.getyPosition());
		assertEquals(3, hunter.getAmountOfArrows());
	}

	@Test
	@DisplayName("Test method setHunterInitialPosition()- Hunter already exists.")
	void setHunterInitialPositionTestNotNull() {
		populateBoardGame.resetBoardGame(tileCave);
		Hunter hunter = new Hunter(3, 0);

		assertThrows(CreationException.class, () -> populateBoardGame.setHunterInitialPosition(hunter, tileCave));
	}

	@Test
	@DisplayName("Test method setCaveExitPosition()- Happy Path.")
	void setCaveExitPositionTest() {
		boolean boardReset = populateBoardGame.resetBoardGame(tileCave);

		CaveExit caveExit = null;
		caveExit = populateBoardGame.setCaveExitPosition(caveExit, tileCave);

		assertTrue(boardReset);
		assertNotNull(caveExit);
		assertEquals(3, caveExit.getxPosition());
		assertEquals(0, caveExit.getyPosition());
	}

	@Test
	@DisplayName("Test method setCaveExitPosition()- CaveExit already exists.")
	void setCaveExitPositionTestNotNull() {
		populateBoardGame.resetBoardGame(tileCave);
		CaveExit caveExit = new CaveExit(3, 0);

		assertThrows(CreationException.class, () -> populateBoardGame.setCaveExitPosition(caveExit, tileCave));
	}

	@Test
	@DisplayName("Test method setGoldPosition()- Happy Path.")
	void setGoldPositionTest() {
		boolean boardReset = populateBoardGame.resetBoardGame(tileCave);

		Gold gold = null;
		gold = populateBoardGame.setGoldPosition(gold, tileCave);

		assertTrue(boardReset);
		assertNotNull(gold);
		assertEquals(1, gold.getxPosition());
		assertEquals(1, gold.getyPosition());
	}

	@Test
	@DisplayName("Test method setGoldPosition()- Gold already exists.")
	void setGoldPositionTestNotNull() {
		populateBoardGame.resetBoardGame(tileCave);
		Gold gold = new Gold(1, 1);

		assertThrows(CreationException.class, () -> populateBoardGame.setGoldPosition(gold, tileCave));
	}

	@Test
	@DisplayName("Test method setHolePosition()- Happy Path with 3 Holes.")
	void setHolePositionTestWithThreeHoles() {
		int holeQuantity = 3;
		parameterizedHolePositionTest(holeQuantity);
	}

	@Test
	@DisplayName("Test method setHolePosition()- Happy Path with 2 Holes.")
	void setHolePositionTestWithTwoHoles() {
		int holeQuantity = 2;
		parameterizedHolePositionTest(holeQuantity);
	}

	@Test
	@DisplayName("Test method setHolePosition()- Happy Path with 1 Hole.")
	void setHolePositionTestWithOneHole() {
		int holeQuantity = 1;
		parameterizedHolePositionTest(holeQuantity);
	}

	@Test
	@DisplayName("Test method setHolePosition()- Happy Path with Default Hole.")
	void setHolePositionTestWithDefaultHole() {
		int holeQuantity = 999;
		parameterizedHolePositionTest(holeQuantity);
	}

	@Test
	@DisplayName("Test method setHolePosition()- Already exist Hole List.")
	void setHolePositionTestWithExistingHoleList() {
		populateBoardGame.resetBoardGame(tileCave);
		List<Hole> holeList = Arrays.asList(new Hole(1, 1));

		assertThrows(CreationException.class, () -> populateBoardGame.setHolePosition(holeList, 1, tileCave));
	}

	@Test
	@DisplayName("Test method setHolePosition()- with negative Hole Quantity.")
	void setHolePositionTestWithNegativeHoleQuantity() {
		populateBoardGame.resetBoardGame(tileCave);
		List<Hole> holeList = null;

		assertThrows(CreationException.class, () -> populateBoardGame.setHolePosition(holeList, -15, tileCave));
	}

	@Test
	@DisplayName("Test method setWumpusPosition()- Happy Path.")
	void setWumpusPositionTest() {
		boolean boardReset = populateBoardGame.resetBoardGame(tileCave);

		Wumpus wumpus = null;
		wumpus = populateBoardGame.setWumpusPosition(wumpus, tileCave);

		assertTrue(boardReset);
		assertNotNull(wumpus);
		assertTrue(wumpus.isAlive());
		assertEquals(1, wumpus.getxPosition());
		assertEquals(0, wumpus.getyPosition());
	}

	@Test
	@DisplayName("Test method setWumpusPosition()- Wumpus already exists.")
	void setWumpusPositionTestNotNull() {
		populateBoardGame.resetBoardGame(tileCave);
		Wumpus wumpus = new Wumpus(1, 0);

		assertThrows(CreationException.class, () -> populateBoardGame.setWumpusPosition(wumpus, tileCave));
	}

	private void parameterizedHolePositionTest(int holeQuantity) {
		if (holeQuantity >= 3) {
			holeQuantity = 1;
		}

		populateBoardGame.resetBoardGame(tileCave);

		List<Hole> holeList = null;
		holeList = populateBoardGame.setHolePosition(holeList, holeQuantity, tileCave);

		assertNotNull(holeList);
		assertFalse(holeList.isEmpty());
		assertEquals(holeQuantity, holeList.size());
	}
}