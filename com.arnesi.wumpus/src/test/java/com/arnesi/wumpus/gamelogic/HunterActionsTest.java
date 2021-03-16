package com.arnesi.wumpus.gamelogic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.arnesi.wumpus.exception.InvalidCavePositionException;
import com.arnesi.wumpus.exception.InvalidEntityException;
import com.arnesi.wumpus.model.CaveExit;
import com.arnesi.wumpus.model.Entity;
import com.arnesi.wumpus.model.Gold;
import com.arnesi.wumpus.model.Hole;
import com.arnesi.wumpus.model.Hunter;
import com.arnesi.wumpus.model.HunterDirectionEnum;
import com.arnesi.wumpus.model.Tile;
import com.arnesi.wumpus.model.Wumpus;
import com.arnesi.wumpus.model.WumpusStench;

class HunterActionsTest {
	private static final String MOVE = "Moving...";
	private static final String WALL_SHOCK_PERCEPTION = "Hunter say: I hit the cave wall!!";

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
	@DisplayName("Test moveHunterForward() method - Happy Path.")
	void moveHunterForwardTestHappyPath() {

		Hunter hunter = new Hunter(1, 1);
		hunter.setDirection(HunterDirectionEnum.UP);
		String hunterMoveUp = hunterActions.moveHunterForward(hunter, boardWidth, boardHeight);

		hunter = new Hunter(1, 1);
		hunter.setDirection(HunterDirectionEnum.DOWN);
		String hunterMoveDown = hunterActions.moveHunterForward(hunter, boardWidth, boardHeight);

		hunter = new Hunter(1, 1);
		hunter.setDirection(HunterDirectionEnum.LEFT);
		String hunterMoveLeft = hunterActions.moveHunterForward(hunter, boardWidth, boardHeight);

		hunter = new Hunter(1, 0);
		hunter.setDirection(HunterDirectionEnum.RIGHT);
		String hunterMoveRight = hunterActions.moveHunterForward(hunter, boardWidth, boardHeight);

		assertEquals(MOVE, hunterMoveUp);
		assertEquals(MOVE, hunterMoveDown);
		assertEquals(MOVE, hunterMoveLeft);
		assertEquals(MOVE, hunterMoveRight);
	}

	@Test
	@DisplayName("Test moveHunterForward() method - Null Hunter.")
	void moveHunterForwardTestNullHunter() {
		assertThrows(InvalidEntityException.class,
				() -> hunterActions.moveHunterForward(null, boardWidth, boardHeight));
	}

	@Test
	@DisplayName("Test moveHunterForward() method - Invalid board sizes Width.")
	void moveHunterForwardTestInvalidBoardSizesWidth() {
		Hunter hunter = new Hunter(1, 1);
		assertThrows(InvalidCavePositionException.class, () -> hunterActions.moveHunterForward(hunter, -15, 0));
	}

	@Test
	@DisplayName("Test moveHunterForward() method - Invalid board sizes Height")
	void moveHunterForwardTestInvalidBoardSizesHeight() {
		Hunter hunter = new Hunter(1, 1);
		assertThrows(InvalidCavePositionException.class, () -> hunterActions.moveHunterForward(hunter, 4, -5));
	}

	@Test
	@DisplayName("Test moveHunterForward() method - Invalid Hunter position X.")
	void moveHunterForwardTestInvalidHunterPositionX() {
		Hunter hunter = new Hunter(-5, 1);
		assertThrows(InvalidCavePositionException.class,
				() -> hunterActions.moveHunterForward(hunter, boardWidth, boardHeight));
	}

	@Test
	@DisplayName("Test moveHunterForward() method - Invalid Hunter position Y.")
	void moveHunterForwardTestInvalidHunterPositionY() {
		Hunter hunter = new Hunter(4, -2);
		assertThrows(InvalidCavePositionException.class,
				() -> hunterActions.moveHunterForward(hunter, boardWidth, boardHeight));
	}

	@Test
	@DisplayName("Test moveHunterForward() method - Hunter Wall Perception.")
	void moveHunterForwardTestWallPerception() {

		Hunter hunter = new Hunter(1, 1);
		hunter.setDirection(HunterDirectionEnum.UP);
		hunterActions.moveHunterForward(hunter, boardWidth, boardHeight);
		hunterActions.moveHunterForward(hunter, boardWidth, boardHeight);
		String hunterMoveUp = hunterActions.moveHunterForward(hunter, boardWidth, boardHeight);

		hunter = new Hunter(1, 1);
		hunter.setDirection(HunterDirectionEnum.DOWN);
		hunterActions.moveHunterForward(hunter, boardWidth, boardHeight);
		hunterActions.moveHunterForward(hunter, boardWidth, boardHeight);
		String hunterMoveDown = hunterActions.moveHunterForward(hunter, boardWidth, boardHeight);

		hunter = new Hunter(1, 1);
		hunter.setDirection(HunterDirectionEnum.LEFT);
		hunterActions.moveHunterForward(hunter, boardWidth, boardHeight);
		hunterActions.moveHunterForward(hunter, boardWidth, boardHeight);
		String hunterMoveLeft = hunterActions.moveHunterForward(hunter, boardWidth, boardHeight);

		hunter = new Hunter(1, 1);
		hunter.setDirection(HunterDirectionEnum.RIGHT);
		hunterActions.moveHunterForward(hunter, boardWidth, boardHeight);
		hunterActions.moveHunterForward(hunter, boardWidth, boardHeight);
		String hunterMoveRight = hunterActions.moveHunterForward(hunter, boardWidth, boardHeight);

		assertEquals(WALL_SHOCK_PERCEPTION, hunterMoveUp);
		assertEquals(WALL_SHOCK_PERCEPTION, hunterMoveDown);
		assertEquals(WALL_SHOCK_PERCEPTION, hunterMoveLeft);
		assertEquals(WALL_SHOCK_PERCEPTION, hunterMoveRight);
	}

	@Test
	@DisplayName("Test turnHunter() method - Happy Path Anticlockwise Rotation.")
	void turnHunterTestHappyPathAnticlockwise() {

		Hunter hunter = new Hunter(1, 1);
		hunter.setDirection(HunterDirectionEnum.UP);

		hunterActions.turnHunter(HunterActionsEnum.TURN_LEFT, hunter);
		assertEquals(HunterDirectionEnum.LEFT, hunter.getDirection());

		hunterActions.turnHunter(HunterActionsEnum.TURN_LEFT, hunter);
		assertEquals(HunterDirectionEnum.DOWN, hunter.getDirection());

		hunterActions.turnHunter(HunterActionsEnum.TURN_LEFT, hunter);
		assertEquals(HunterDirectionEnum.RIGHT, hunter.getDirection());

		hunterActions.turnHunter(HunterActionsEnum.TURN_LEFT, hunter);
		assertEquals(HunterDirectionEnum.UP, hunter.getDirection());
	}

	@Test
	@DisplayName("Test turnHunter() method - Null HunterActionsEnum.")
	void turnHunterTestNullHunterActionsEnum() {
		Hunter hunter = new Hunter(1, 1);
		assertThrows(InvalidEntityException.class, () -> hunterActions.turnHunter(null, hunter));
	}

	@Test
	@DisplayName("Test turnHunter() method - Null Hunter.")
	void turnHunterTestNullHunter() {
		assertThrows(InvalidEntityException.class, () -> hunterActions.turnHunter(HunterActionsEnum.TURN_LEFT, null));
	}

	@Test
	@DisplayName("Test turnHunter() method - Happy Path Clockwise Rotation.")
	void turnHunterTestHappyPathClockwise() {

		Hunter hunter = new Hunter(1, 1);
		hunter.setDirection(HunterDirectionEnum.UP);

		hunterActions.turnHunter(HunterActionsEnum.TURN_RIGHT, hunter);
		assertEquals(HunterDirectionEnum.RIGHT, hunter.getDirection());

		hunterActions.turnHunter(HunterActionsEnum.TURN_RIGHT, hunter);
		assertEquals(HunterDirectionEnum.DOWN, hunter.getDirection());

		hunterActions.turnHunter(HunterActionsEnum.TURN_RIGHT, hunter);
		assertEquals(HunterDirectionEnum.LEFT, hunter.getDirection());

		hunterActions.turnHunter(HunterActionsEnum.TURN_RIGHT, hunter);
		assertEquals(HunterDirectionEnum.UP, hunter.getDirection());
	}

	@Test
	@DisplayName("Test hunterExitCave() method - Happy Path Gold Found.")
	void hunterExitCaveTestHappyPathGoldFound() {
		Hunter hunter = new Hunter(3, 0);
		hunter.setHaveGold(true);
		CaveExit caveExit = new CaveExit(3, 0);

		Tile tile = new Tile(3, 0);
		tile.addEntity(hunter);
		tile.addEntity(caveExit);

		Tile[][] tileCave = new Tile[boardWidth][boardHeight];
		tileCave[3][0] = tile;

		HunterActionsEnum action = hunterActions.hunterExitCave(hunter, tileCave, caveExit);
		assertEquals(HunterActionsEnum.GOLD_FOUND, action);
	}

	@Test
	@DisplayName("Test hunterExitCave() method - Happy Path Gold Not Found.")
	void hunterExitCaveTestHappyPathGoldNotFound() {
		Hunter hunter = new Hunter(3, 0);
		hunter.setHaveGold(false);
		CaveExit caveExit = new CaveExit(3, 0);

		Tile tile = new Tile(3, 0);
		tile.addEntity(hunter);
		tile.addEntity(caveExit);

		Tile[][] tileCave = new Tile[boardWidth][boardHeight];
		tileCave[3][0] = tile;

		HunterActionsEnum action = hunterActions.hunterExitCave(hunter, tileCave, caveExit);
		assertEquals(HunterActionsEnum.GOLD_NOT_FOUND, action);
	}

	@Test
	@DisplayName("Test hunterExitCave() method - Happy Path Hunter Is Not At Exit.")
	void hunterExitCaveTestHappyPathNoExitPosition() {
		Hunter hunter = new Hunter(3, 0);
		hunter.setHaveGold(false);
		CaveExit caveExit = new CaveExit(3, 0);

		Tile tile = new Tile(3, 0);
		tile.addEntity(hunter);

		Tile[][] tileCave = new Tile[boardWidth][boardHeight];
		tileCave[3][0] = tile;

		HunterActionsEnum action = hunterActions.hunterExitCave(hunter, tileCave, caveExit);
		assertEquals(HunterActionsEnum.NO_EXIT, action);
	}

	@Test
	@DisplayName("Test hunterExitCave() method - Null Hunter Exception Error.")
	void hunterExitCaveTestNullHunter() {
		Hunter hunter = null;
		CaveExit caveExit = new CaveExit(3, 0);
		Tile[][] tileCave = new Tile[boardWidth][boardHeight];

		assertThrows(InvalidEntityException.class, () -> hunterActions.hunterExitCave(hunter, tileCave, caveExit));
	}

	@Test
	@DisplayName("Test hunterExitCave() method - Null CaveExit Exception Error.")
	void hunterExitCaveTestNullCaveExit() {
		Hunter hunter = new Hunter(3, 0);
		CaveExit caveExit = null;
		Tile[][] tileCave = new Tile[boardWidth][boardHeight];

		assertThrows(InvalidEntityException.class, () -> hunterActions.hunterExitCave(hunter, tileCave, caveExit));
	}

	@Test
	@DisplayName("Test hunterExitCave() method - Invalid Hunter position X.")
	void hunterExitCaveTestInvalidHunterpositionX() {
		Hunter hunter = new Hunter(-2, 0);
		CaveExit caveExit = new CaveExit(3, 0);
		Tile[][] tileCave = new Tile[boardWidth][boardHeight];

		assertThrows(InvalidCavePositionException.class,
				() -> hunterActions.hunterExitCave(hunter, tileCave, caveExit));
	}

	@Test
	@DisplayName("Test hunterExitCave() method - Invalid Hunter position Y.")
	void hunterExitCaveTestInvalidHunterpositionY() {
		Hunter hunter = new Hunter(3, -4);
		CaveExit caveExit = new CaveExit(3, 0);
		Tile[][] tileCave = new Tile[boardWidth][boardHeight];

		assertThrows(InvalidCavePositionException.class,
				() -> hunterActions.hunterExitCave(hunter, tileCave, caveExit));
	}

	@Test
	@DisplayName("Test updateHunterStatus() method - Happy Path Hunter Update Live and Gold.")
	void updateHunterStatusTestHappyPathUpdateLiveAndGold() {
		Hunter hunter = new Hunter(1, 1);
		hunter.setAlive(true);
		hunter.setHaveGold(false);

		Wumpus wumpus = new Wumpus(1, 0);
		Gold gold = new Gold(1, 1);
		List<Entity> perceptions = Arrays.asList(hunter, wumpus, gold);

		Hunter updatedHunter = hunterActions.updateHunterStatus(hunter, perceptions, wumpus, gold);

		assertFalse(updatedHunter.isAlive());
		assertTrue(updatedHunter.isHaveGold());
	}

	@Test
	@DisplayName("Test updateHunterStatus() method - Happy Path Hunter Update Live and Gold.")
	void updateHunterStatusTestHappyPathUpdateLiveByHole() {
		Hunter hunter = new Hunter(1, 1);
		hunter.setAlive(true);
		hunter.setHaveGold(false);

		Wumpus wumpus = new Wumpus(1, 0);
		Gold gold = new Gold(2, 2);
		Hole hole = new Hole(1, 1);
		List<Entity> perceptions = Arrays.asList(hunter, hole);

		Hunter updatedHunter = hunterActions.updateHunterStatus(hunter, perceptions, wumpus, gold);

		assertFalse(updatedHunter.isAlive());
		assertFalse(updatedHunter.isHaveGold());
	}

	@Test
	@DisplayName("Test updateHunterStatus() method - Null Hunter Exception Error.")
	void updateHunterStatusTestNullHunterExceptionError() {
		Hunter hunter = null;
		Wumpus wumpus = new Wumpus(1, 0);
		Gold gold = new Gold(2, 2);
		List<Entity> perceptions = new ArrayList<>();

		assertThrows(InvalidEntityException.class,
				() -> hunterActions.updateHunterStatus(hunter, perceptions, wumpus, gold));
	}

	@Test
	@DisplayName("Test updateHunterStatus() method - Null Wumpus Exception Error.")
	void updateHunterStatusTestNullWumpusExceptionError() {
		Hunter hunter = new Hunter(1, 1);
		Wumpus wumpus = null;
		Gold gold = new Gold(2, 2);
		List<Entity> perceptions = new ArrayList<>();

		assertThrows(InvalidEntityException.class,
				() -> hunterActions.updateHunterStatus(hunter, perceptions, wumpus, gold));
	}

	@Test
	@DisplayName("Test hunterPerception() method - Happy Path Hunter Perceptions.")
	void hunterPerceptionTestHappyPath() {
		Hunter hunter = new Hunter(3, 0);

		Tile tile = new Tile(3, 0);
		tile.addEntity(hunter);
		tile.addEntity(new CaveExit(3, 0));
		tile.addEntity(new WumpusStench());

		Tile[][] tileCave = new Tile[boardWidth][boardHeight];
		tileCave[3][0] = tile;

		List<Entity> perception = hunterActions.hunterPerception(hunter, tileCave);

		assertNotNull(perception);
		assertTrue(CollectionUtils.containsAny(tile.getEntity(), perception));
	}

	@Test
	@DisplayName("Test hunterPerception() method - Null Hunter Exception Error.")
	void hunterPerceptionTestNullHunterExceptionError() {
		Hunter hunter = null;
		Tile[][] tileCave = new Tile[boardWidth][boardHeight];

		assertThrows(InvalidEntityException.class, () -> hunterActions.hunterPerception(hunter, tileCave));
	}

	@Test
	@DisplayName("Test hunterPerception() method - Invalid Hunter position X.")
	void hunterPerceptionTestInvalidHunterPositionX() {
		Hunter hunter = new Hunter(-5, 3);
		Tile[][] tileCave = new Tile[boardWidth][boardHeight];

		assertThrows(InvalidCavePositionException.class, () -> hunterActions.hunterPerception(hunter, tileCave));
	}

	@Test
	@DisplayName("Test hunterPerception() method - Invalid Hunter position Y.")
	void hunterPerceptionTestInvalidHunterPositionY() {
		Hunter hunter = new Hunter(2, -3);
		Tile[][] tileCave = new Tile[boardWidth][boardHeight];

		assertThrows(InvalidCavePositionException.class, () -> hunterActions.hunterPerception(hunter, tileCave));
	}
}