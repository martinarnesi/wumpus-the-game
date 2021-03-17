package com.arnesi.wumpus.gamelogic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.arnesi.wumpus.exception.InvalidCavePositionException;
import com.arnesi.wumpus.exception.InvalidEntityException;
import com.arnesi.wumpus.model.Hunter;
import com.arnesi.wumpus.model.Wumpus;

class ArrowActionsTest {
	private static final String NO_ARROWS = "You don't have more arrows.";
	private static final String WUMPUS_SCREAM = "Wumpus scream";
	private static final String WALL_SHOCK_PERCEPTION = "Arrow hit the cave wall!!";

	private int boardWidth;
	private int boardHeight;

	private ArrowActions arrowActions;

	@BeforeEach
	public void setUp() {
		boardWidth = 4;
		boardHeight = 4;
		arrowActions = new ArrowActions();
	}

	@AfterEach
	public void tearDown() {
		arrowActions = null;
	}

	@Test
	@DisplayName("Test shootArrow() method - kill Wumpus Happy Path.")
	void shootArrowTestHappyPath() {

		Hunter hunter = new Hunter(3, 0);
		Wumpus wumpus = new Wumpus(1, 0);

		String arrowShoot1 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);
		String arrowShoot2 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);
		String arrowShoot3 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);
		String arrowShoot4 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);

		assertEquals(WUMPUS_SCREAM, arrowShoot1);
		assertEquals(WALL_SHOCK_PERCEPTION, arrowShoot2);
		assertEquals(WALL_SHOCK_PERCEPTION, arrowShoot3);
		assertEquals(NO_ARROWS, arrowShoot4);
	}

	@Test
	@DisplayName("Test shootArrow() method - Wumpus is not in front of Hunter.")
	void shootArrowWumpusIsNotInFront() {

		Hunter hunter = new Hunter(3, 1);
		Wumpus wumpus = new Wumpus(1, 0);

		String arrowShoot1 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);
		String arrowShoot2 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);
		String arrowShoot3 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);
		String arrowShoot4 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);

		assertEquals(WALL_SHOCK_PERCEPTION, arrowShoot1);
		assertEquals(WALL_SHOCK_PERCEPTION, arrowShoot2);
		assertEquals(WALL_SHOCK_PERCEPTION, arrowShoot3);
		assertEquals(NO_ARROWS, arrowShoot4);
	}

	@Test
	@DisplayName("Test shootArrow() method - Hunter is looking to the right.")
	void shootArrowTestHunterLookingRight() {

		Hunter hunter = new Hunter(3, 0);
		Wumpus wumpus = new Wumpus(1, 0);

		hunter.setDirection(HunterDirectionEnum.RIGHT);

		String arrowShoot1 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);
		String arrowShoot2 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);
		String arrowShoot3 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);
		String arrowShoot4 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);

		assertEquals(WALL_SHOCK_PERCEPTION, arrowShoot1);
		assertEquals(WALL_SHOCK_PERCEPTION, arrowShoot2);
		assertEquals(WALL_SHOCK_PERCEPTION, arrowShoot3);
		assertEquals(NO_ARROWS, arrowShoot4);
	}

	@Test
	@DisplayName("Test shootArrow() method - Hunter is looking to the Left.")
	void shootArrowTestHunterLookingLeft() {

		Hunter hunter = new Hunter(3, 0);
		Wumpus wumpus = new Wumpus(1, 0);

		hunter.setDirection(HunterDirectionEnum.LEFT);

		String arrowShoot1 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);
		String arrowShoot2 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);
		String arrowShoot3 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);
		String arrowShoot4 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);

		assertEquals(WALL_SHOCK_PERCEPTION, arrowShoot1);
		assertEquals(WALL_SHOCK_PERCEPTION, arrowShoot2);
		assertEquals(WALL_SHOCK_PERCEPTION, arrowShoot3);
		assertEquals(NO_ARROWS, arrowShoot4);
	}

	@Test
	@DisplayName("Test shootArrow() method - Hunter is looking down.")
	void shootArrowTestHunterLookingDown() {

		Hunter hunter = new Hunter(3, 0);
		Wumpus wumpus = new Wumpus(1, 0);

		hunter.setDirection(HunterDirectionEnum.DOWN);

		String arrowShoot1 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);
		String arrowShoot2 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);
		String arrowShoot3 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);
		String arrowShoot4 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);

		assertEquals(WALL_SHOCK_PERCEPTION, arrowShoot1);
		assertEquals(WALL_SHOCK_PERCEPTION, arrowShoot2);
		assertEquals(WALL_SHOCK_PERCEPTION, arrowShoot3);
		assertEquals(NO_ARROWS, arrowShoot4);
	}

	@Test
	@DisplayName("Test shootArrow() method - Hunter is in a cell above the wumpues, looking down.")
	void shootArrowTestHunterAbove() {

		Hunter hunter = new Hunter(0, 0);
		Wumpus wumpus = new Wumpus(2, 0);

		hunter.setDirection(HunterDirectionEnum.DOWN);

		String arrowShoot1 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);
		String arrowShoot2 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);
		String arrowShoot3 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);
		String arrowShoot4 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);

		assertEquals(WUMPUS_SCREAM, arrowShoot1);
		assertEquals(WALL_SHOCK_PERCEPTION, arrowShoot2);
		assertEquals(WALL_SHOCK_PERCEPTION, arrowShoot3);
		assertEquals(NO_ARROWS, arrowShoot4);
	}

	@Test
	@DisplayName("Test shootArrow() method - Hunter is in an adjacent cell, looking left.")
	void shootArrowTestHunterAside() {

		Hunter hunter = new Hunter(2, 1);
		Wumpus wumpus = new Wumpus(2, 0);

		hunter.setDirection(HunterDirectionEnum.LEFT);

		String arrowShoot1 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);
		String arrowShoot2 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);
		String arrowShoot3 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);
		String arrowShoot4 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);

		assertEquals(WUMPUS_SCREAM, arrowShoot1);
		assertEquals(WALL_SHOCK_PERCEPTION, arrowShoot2);
		assertEquals(WALL_SHOCK_PERCEPTION, arrowShoot3);
		assertEquals(NO_ARROWS, arrowShoot4);
	}

	@Test
	@DisplayName("Test shootArrow() method - Hunter kill kills the Wumpus with its last arrow")
	void shootArrowTestHunterLastHope() {

		Hunter hunter = new Hunter(2, 1);
		Wumpus wumpus = new Wumpus(2, 0);

		hunter.setDirection(HunterDirectionEnum.RIGHT);
		String arrowShoot1 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);

		hunter.setDirection(HunterDirectionEnum.UP);
		String arrowShoot2 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);

		hunter.setDirection(HunterDirectionEnum.LEFT);
		String arrowShoot3 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);
		String arrowShoot4 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);

		assertEquals(WALL_SHOCK_PERCEPTION, arrowShoot1);
		assertEquals(WALL_SHOCK_PERCEPTION, arrowShoot2);
		assertEquals(WUMPUS_SCREAM, arrowShoot3);
		assertEquals(NO_ARROWS, arrowShoot4);
	}

	@Test
	@DisplayName("Test shootArrow() method - Hunter and Wumpus in the same Position.")
	void shootArrowTestHunterAndWumpusSamePosition() {

		/*
		 * This is a edge case and it is not possible to kill the Wumpus shooting in the
		 * same position. There is a core logic that kills the hunter if he is in the
		 * same position, even before he shoots.
		 */
		Hunter hunter = new Hunter(3, 1);
		Wumpus wumpus = new Wumpus(3, 1);

		String arrowShoot1 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);
		String arrowShoot2 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);
		String arrowShoot3 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);
		String arrowShoot4 = arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight);

		assertEquals(WALL_SHOCK_PERCEPTION, arrowShoot1);
		assertEquals(WALL_SHOCK_PERCEPTION, arrowShoot2);
		assertEquals(WALL_SHOCK_PERCEPTION, arrowShoot3);
		assertEquals(NO_ARROWS, arrowShoot4);
	}

	@Test
	@DisplayName("Test shootArrow() method - null Hunter object")
	void shootArrowTestHunterNullHunter() {
		Hunter hunter = null;
		Wumpus wumpus = new Wumpus(3, 1);

		assertThrows(InvalidEntityException.class,
				() -> arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight));
	}

	@Test
	@DisplayName("Test shootArrow() method - null Wumpus object")
	void shootArrowTestHunterNullWumpus() {
		Hunter hunter = new Hunter(3, 1);
		Wumpus wumpus = null;

		assertThrows(InvalidEntityException.class,
				() -> arrowActions.shootArrow(hunter, wumpus, boardWidth, boardHeight));
	}

	@Test
	@DisplayName("Test shootArrow() method - Invalid board dimensions")
	void shootArrowTestInvalidBoardDimensions() {
		Hunter hunter = new Hunter(3, 1);
		Wumpus wumpus = new Wumpus(3, 2);

		assertThrows(InvalidCavePositionException.class, () -> arrowActions.shootArrow(hunter, wumpus, 0, -15));
	}

	@Test
	@DisplayName("Test shootArrow() method - Invalid Hunter location")
	void shootArrowTestInvalidHunterLocation() {
		Hunter hunter = new Hunter(-5, 0);
		Wumpus wumpus = new Wumpus(3, 2);

		assertThrows(InvalidCavePositionException.class, () -> arrowActions.shootArrow(hunter, wumpus, 0, -15));
	}
}