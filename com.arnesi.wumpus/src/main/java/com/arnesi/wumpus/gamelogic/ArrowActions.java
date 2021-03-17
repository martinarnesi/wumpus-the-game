package com.arnesi.wumpus.gamelogic;

import java.util.Objects;

import com.arnesi.wumpus.exception.InvalidCavePositionException;
import com.arnesi.wumpus.exception.InvalidEntityException;
import com.arnesi.wumpus.model.Hunter;
import com.arnesi.wumpus.model.Wumpus;

public class ArrowActions {
	private static final String ENTITY_ERROR = "Entity Exception Error.";
	private static final String BOARD_ERROR = "Board dimentions Error.";
	private static final String NO_ARROWS = "You don't have more arrows.";
	private static final String WUMPUS_SCREAM = "Wumpus scream";
	private static final String WALL_SHOCK_PERCEPTION = "Arrow hit the cave wall!!";

	/**
	 * Logic to count amount of arrows, shoot arrows, kill Wumpus or hit a wall.
	 * 
	 * @param hunter
	 * @param wumpus
	 * @param boardWidth
	 * @param boardHeight
	 * @return String with the result
	 */
	public String shootArrow(Hunter hunter, Wumpus wumpus, int boardWidth, int boardHeight) {
		shootArrowValidation(hunter, wumpus, boardWidth, boardHeight);

		if (hunter.getAmountOfArrows() <= 0) {
			return NO_ARROWS;
		} else {
			hunter.setAmountOfArrows(hunter.getAmountOfArrows() - 1);
		}

		if (!wumpus.isAlive()) {
			return WALL_SHOCK_PERCEPTION;
		}

		ArrowDirectionEnum arrowDirection = ArrowDirectionEnum.valueOf(hunter.getDirection().toString());

		switch (arrowDirection) {
		case UP:
			if ((wumpus.getxPosition() < hunter.getxPosition()) && (wumpus.getyPosition() == hunter.getyPosition())) {
				return killWumpus(wumpus);
			} else {
				return WALL_SHOCK_PERCEPTION;
			}
		case DOWN:
			if (wumpus.getxPosition() > hunter.getxPosition() && (wumpus.getyPosition() == hunter.getyPosition())) {
				return killWumpus(wumpus);
			} else {
				return WALL_SHOCK_PERCEPTION;
			}
		case LEFT:
			if (wumpus.getyPosition() < hunter.getyPosition() && (wumpus.getxPosition() == hunter.getxPosition())) {
				return killWumpus(wumpus);
			} else {
				return WALL_SHOCK_PERCEPTION;
			}
		case RIGHT:
			if (wumpus.getyPosition() > hunter.getyPosition() && (wumpus.getxPosition() == hunter.getxPosition())) {
				return killWumpus(wumpus);
			} else {
				return WALL_SHOCK_PERCEPTION;
			}
		default:
			return WALL_SHOCK_PERCEPTION;
		}
	}

	/**
	 * Before firing an arrow, check that everything is ok.
	 * 
	 * @param hunter
	 * @param wumpus
	 * @param boardWidth
	 * @param boardHeight
	 */
	private void shootArrowValidation(Hunter hunter, Wumpus wumpus, int boardWidth, int boardHeight) {
		if (Objects.isNull(hunter) || Objects.isNull(wumpus)) {
			throw new InvalidEntityException(ENTITY_ERROR);
		}

		if (boardWidth <= 1 || boardHeight <= 1) {
			throw new InvalidCavePositionException(BOARD_ERROR);
		}

		if (hunter.getxPosition() < 0 || hunter.getyPosition() < 0) {
			throw new InvalidCavePositionException(BOARD_ERROR);
		}
	}

	/**
	 * Just kill the Wumpus
	 * 
	 * @param wumpus
	 * @return
	 */
	private String killWumpus(Wumpus wumpus) {
		wumpus.setAlive(false);
		return WUMPUS_SCREAM;
	}
}