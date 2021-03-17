package com.arnesi.wumpus.gamelogic;

import static com.arnesi.wumpus.utils.UserInterfaceUtils.printString;

import java.util.List;
import java.util.Objects;

import com.arnesi.wumpus.exception.InvalidCavePositionException;
import com.arnesi.wumpus.exception.InvalidEntityException;
import com.arnesi.wumpus.model.CaveExit;
import com.arnesi.wumpus.model.Entity;
import com.arnesi.wumpus.model.Gold;
import com.arnesi.wumpus.model.Hole;
import com.arnesi.wumpus.model.Hunter;
import com.arnesi.wumpus.model.Tile;
import com.arnesi.wumpus.model.Wumpus;

/**
 * This class contains all actions and events related to the hunter's movement,
 * state and perceptions.
 * 
 * @author Martin Arnesi
 */
public class HunterActions {
	private static final String MOVE = "Moving...";
	private static final String WALL_SHOCK_PERCEPTION = "Hunter say: I hit the cave wall!!";
	private static final String INVALID = "Invalid movement.";
	private static final String HUNTER_ERROR = "Hunter Exception Error.";
	private static final String ENTITY_ERROR = "Entity Exception Error.";
	private static final String BOARD_ERROR = "Board dimentions Error.";

	/**
	 * This method is responsible for coordinating the hunter's forward movement. It
	 * also verifies collisions against walls.
	 * 
	 * @param hunter
	 * @param boardWidth
	 * @param boardHeight
	 * @return String with the movement result
	 */
	public String moveHunterForward(Hunter hunter, int boardWidth, int boardHeight) {
		if (Objects.isNull(hunter)) {
			throw new InvalidEntityException(HUNTER_ERROR);
		}

		if (boardWidth <= 1 || boardHeight <= 1) {
			throw new InvalidCavePositionException(BOARD_ERROR);
		}

		if (hunter.getxPosition() < 0 || hunter.getyPosition() < 0) {
			throw new InvalidCavePositionException(BOARD_ERROR);
		}

		switch (hunter.getDirection()) {
		case UP:
			if (hunter.getxPosition() > 0) {
				hunter.setxPosition(hunter.getxPosition() - 1);
				return MOVE;
			} else {
				return WALL_SHOCK_PERCEPTION;
			}

		case DOWN:
			if (hunter.getxPosition() < boardWidth - 1) {
				hunter.setxPosition(hunter.getxPosition() + 1);
				return MOVE;
			} else {
				return WALL_SHOCK_PERCEPTION;
			}

		case LEFT:
			if (hunter.getyPosition() > 0) {
				hunter.setyPosition(hunter.getyPosition() - 1);
				return MOVE;
			} else {
				return WALL_SHOCK_PERCEPTION;
			}

		case RIGHT:
			if (hunter.getyPosition() < boardHeight - 1) {
				hunter.setyPosition(hunter.getyPosition() + 1);
				return MOVE;
			} else {
				return WALL_SHOCK_PERCEPTION;
			}
		default:
			break;
		}
		return INVALID;
	}

	/**
	 * This method is responsible for rotating the hunter, both to the left and to
	 * the right.
	 * 
	 * @param hunterAction
	 * @param hunter
	 */
	public void turnHunter(HunterActionsEnum hunterAction, Hunter hunter) {
		if (Objects.isNull(hunterAction) || Objects.isNull(hunter)) {
			throw new InvalidEntityException(HUNTER_ERROR);
		}

		switch (hunterAction) {
		case TURN_LEFT:
			switch (hunter.getDirection()) {
			case UP:
				hunter.setDirection(HunterDirectionEnum.LEFT);
				break;
			case LEFT:
				hunter.setDirection(HunterDirectionEnum.DOWN);
				break;
			case DOWN:
				hunter.setDirection(HunterDirectionEnum.RIGHT);
				break;
			case RIGHT:
				hunter.setDirection(HunterDirectionEnum.UP);
				break;
			}
			break;
		case TURN_RIGHT:
			switch (hunter.getDirection()) {
			case UP:
				hunter.setDirection(HunterDirectionEnum.RIGHT);
				break;
			case LEFT:
				hunter.setDirection(HunterDirectionEnum.UP);
				break;
			case DOWN:
				hunter.setDirection(HunterDirectionEnum.LEFT);
				break;
			case RIGHT:
				hunter.setDirection(HunterDirectionEnum.DOWN);
				break;
			}
			break;
		}
	}

	/**
	 * Verify all the necessary conditions that the hunter needs to successfully
	 * leave the cave.
	 * 
	 * @param hunter
	 * @param tileCave
	 * @param caveExit
	 * @return
	 */
	public HunterActionsEnum hunterExitCave(Hunter hunter, Tile[][] tileCave, CaveExit caveExit) {
		if (Objects.isNull(hunter) || Objects.isNull(caveExit)) {
			throw new InvalidEntityException(ENTITY_ERROR);
		}

		if (hunter.getxPosition() < 0 || hunter.getyPosition() < 0) {
			throw new InvalidCavePositionException(BOARD_ERROR);
		}

		List<Entity> entityList = tileCave[hunter.getxPosition()][hunter.getyPosition()].getEntity();

		if (entityList.contains(caveExit) && entityList.contains(hunter) && hunter.isHaveGold()) {
			return HunterActionsEnum.GOLD_FOUND;
		} else if (entityList.contains(caveExit) && entityList.contains(hunter)) {
			return HunterActionsEnum.GOLD_NOT_FOUND;
		} else {
			return HunterActionsEnum.NO_EXIT;
		}
	}

	/**
	 * Updates the list of hunter's perceptions
	 * 
	 * @param hunter
	 * @param perceptions
	 * @param wumpus
	 * @param gold
	 * @return
	 */
	public Hunter updateHunterStatus(Hunter hunter, List<Entity> perceptions, Wumpus wumpus, Gold gold) {
		if (Objects.isNull(hunter) || Objects.isNull(perceptions) || Objects.isNull(wumpus) || Objects.isNull(gold)) {
			throw new InvalidEntityException(ENTITY_ERROR);
		}

		if (perceptions.contains(wumpus) && wumpus.isAlive()) {
			hunter.setAlive(false);
		}

		if (perceptions.stream().anyMatch(c -> c instanceof Hole)) {
			hunter.setAlive(false);
		}

		if (perceptions.contains(gold)) {
			hunter.setHaveGold(true);
		}

		return hunter;
	}

	/**
	 * Displays the list of the hunter's perceptions
	 * 
	 * @param hunter
	 * @param tileCave
	 * @return
	 */
	public List<Entity> hunterPerception(Hunter hunter, Tile[][] tileCave) {
		if (Objects.isNull(hunter)) {
			throw new InvalidEntityException(HUNTER_ERROR);
		}

		if (hunter.getxPosition() < 0 || hunter.getyPosition() < 0) {
			throw new InvalidCavePositionException(BOARD_ERROR);
		}

		List<Entity> perception = tileCave[hunter.getxPosition()][hunter.getyPosition()].getEntity();
		for (Entity entity : perception) {
			printString("Hunter say: " + entity);
		}

		return perception;
	}
}