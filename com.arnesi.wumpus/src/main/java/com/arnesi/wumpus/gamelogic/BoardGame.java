package com.arnesi.wumpus.gamelogic;

import static com.arnesi.wumpus.utils.UserInterfaceUtils.printString;

import java.util.List;

import com.arnesi.wumpus.exception.CreationException;
import com.arnesi.wumpus.model.CaveExit;
import com.arnesi.wumpus.model.Entity;
import com.arnesi.wumpus.model.Gold;
import com.arnesi.wumpus.model.Hole;
import com.arnesi.wumpus.model.Hunter;
import com.arnesi.wumpus.model.Tile;
import com.arnesi.wumpus.model.Wumpus;
import com.arnesi.wumpus.parameters.GameParameters;
import com.arnesi.wumpus.utils.PopulateBoardGame;

/**
 * BoardGame class contains the main logic of the game.
 * 
 * @author Martin
 *
 */
public class BoardGame {

	private static final int BOARD_WIDTH = GameParameters.PARAMETRISED_BOARD_WIDTH;
	private static final int BOARD_HEIGHT = GameParameters.PARAMETRISED_BOARD_HEIGHT;
	private static final int HOLES_QUANTITY = GameParameters.PARAMETRISED_HOLES_QUANTITY;

	private static final String HUNTER_DIRECTION = "Hunter Direction: ";
	private static final String CREATION_ERROR = "Error when try to reset board game";

	private final Tile[][] tileCave;
	private final HunterActions hunterActions;
	private final ArrowActions arrowActions;
	private final PopulateBoardGame populateBoardGame;

	private Hunter hunter;
	private CaveExit caveExit;
	private Gold gold;
	private List<Hole> holeList;
	private Wumpus wumpus;

	public BoardGame() {
		this.tileCave = new Tile[BOARD_WIDTH][BOARD_HEIGHT];
		this.hunterActions = new HunterActions();
		this.arrowActions = new ArrowActions();
		this.populateBoardGame = new PopulateBoardGame();
	}

	/**
	 * Initializes the game board in a consistent state and adds the entities in
	 * fixed positions.
	 */
	public void initializeBoardGame() {
		if (populateBoardGame.resetBoardGame(tileCave)) {
			hunter = populateBoardGame.setHunterInitialPosition(hunter, tileCave);
			caveExit = populateBoardGame.setCaveExitPosition(caveExit, tileCave);
			gold = populateBoardGame.setGoldPosition(gold, tileCave);
			holeList = populateBoardGame.setHolePosition(holeList, HOLES_QUANTITY, tileCave);
			wumpus = populateBoardGame.setWumpusPosition(wumpus, tileCave);
		} else {
			throw new CreationException(CREATION_ERROR);
		}
	}

	/**
	 * Receives actions from the user and coordinates the game logic.
	 * 
	 * @param HunterAction
	 * @return Game Status
	 */
	public Boolean boardActions(HunterActionsEnum hunterAction) {
		switch (hunterAction) {
		case GO_FORWARD:
			removeEntityPositionFromBoard(hunter);
			printString(hunterActions.moveHunterForward(hunter, BOARD_WIDTH, BOARD_HEIGHT));
			addEntityPositionToBoard(hunter);

			List<Entity> perceptions = hunterActions.hunterPerception(hunter, tileCave);
			hunter = hunterActions.updateHunterStatus(hunter, perceptions, wumpus, gold);

			return hunter.isAlive();

		case TURN_LEFT:
			hunterActions.turnHunter(hunterAction, hunter);
			printString(HUNTER_DIRECTION + hunter.getDirection());
			break;

		case TURN_RIGHT:
			hunterActions.turnHunter(hunterAction, hunter);
			printString(HUNTER_DIRECTION + hunter.getDirection());
			break;

		case SHOOT:
			// printString("Not implemted in this version.");
			printString(arrowActions.shootArrow(hunter, wumpus, BOARD_WIDTH, BOARD_HEIGHT));
			break;

		case EXIT:
			switch (hunterActions.hunterExitCave(hunter, tileCave, caveExit)) {
			case GOLD_FOUND:
				return true;
			case GOLD_NOT_FOUND:
			case NO_EXIT:
				return false;
			}
			break;
		}
		return false;
	}

	private void removeEntityPositionFromBoard(Entity entity) {
		tileCave[hunter.getxPosition()][hunter.getyPosition()].removeEntity(entity);
	}

	private void addEntityPositionToBoard(Entity entity) {
		tileCave[hunter.getxPosition()][hunter.getyPosition()].addEntity(entity);
	}
}