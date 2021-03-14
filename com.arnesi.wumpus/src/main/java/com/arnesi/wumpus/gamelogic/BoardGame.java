package com.arnesi.wumpus.gamelogic;

import static com.arnesi.wumpus.utils.UserInterfaceUtils.printString;

import java.util.Arrays;
import java.util.List;

import com.arnesi.wumpus.exception.CreationException;
import com.arnesi.wumpus.model.CaveExit;
import com.arnesi.wumpus.model.Entity;
import com.arnesi.wumpus.model.Gold;
import com.arnesi.wumpus.model.Hole;
import com.arnesi.wumpus.model.Hunter;
import com.arnesi.wumpus.model.Tile;
import com.arnesi.wumpus.model.Wumpus;
import com.arnesi.wumpus.model.WumpusStench;
import com.arnesi.wumpus.parameters.GameParameters;
import com.arnesi.wumpus.utils.PopulateBoardGame;

public class BoardGame {

	private static final int boardWidth = GameParameters.PARAMETRISED_BOARD_WIDTH;
	private static final int boardHeight = GameParameters.PARAMETRISED_BOARD_HEIGHT;
	private static final int holesQuantity = GameParameters.PARAMETRISED_HOLES_QUANTITY;
	
	private final Tile[][] tileCave;
	private final HunterActions hunterActions;
	private final PopulateBoardGame populateBoardGame;
	
	private Hunter hunter;
	private CaveExit caveExit;
	private Gold gold;
	private List<Hole> holeList;
	private Wumpus wumpus;
	private WumpusStench wumpusStench;

	public BoardGame() {
		this.tileCave = new Tile[boardWidth][boardHeight];
		this.hunterActions = new HunterActions();
		this.populateBoardGame = new PopulateBoardGame();
	}

	public void initializeBoardGame() {
		if(populateBoardGame.resetBoardGame(tileCave)) {
			hunter = populateBoardGame.setHunterInitialPosition(hunter, tileCave);
			caveExit = populateBoardGame.setCaveExitPosition(caveExit, tileCave);
			gold = populateBoardGame.setGoldPosition(gold, tileCave);
			holeList = populateBoardGame.setHolePosition(holeList, holesQuantity, tileCave);
			wumpus = populateBoardGame.setWumpusPosition(wumpus, tileCave);
			wumpusStench = populateBoardGame.setWumpusStenchPosition(wumpusStench, tileCave);
		} else {
			throw new CreationException("Error when try to reset board game");
		}
	}

	public Boolean boardActions(HunterActionsEnum action) {
		switch (action) {
		case GO_FORWARD:
			tileCave[hunter.getxPosition()][hunter.getyPosition()].removeEntity(hunter);
			printString(hunterActions.moveHunterForward(hunter, boardWidth, boardHeight));
			tileCave[hunter.getxPosition()][hunter.getyPosition()].addEntity(hunter);
			break;
		case TURN_LEFT:
			hunterActions.turnHunter(action, hunter);
			printString("Hunter Direction: " + hunter.getDirection());
			break;
		case TURN_RIGHT:
			hunterActions.turnHunter(action, hunter);
			printString("Hunter Direction: " + hunter.getDirection());
			break;
		case SHOOT:
			break;
		case EXIT:
			if (exitCave()) {
				return true;
			} else {
				printString("Hunter can't leave the cave.... ");
			}
			break;

		default:
			break;
		}
		return false;
	}

	public boolean exitCave() {
		List<Entity> entityList = tileCave[3][0].getEntity();

		if (entityList.contains(caveExit) && entityList.contains(hunter)) {
			return true;
		} else {
			return false;
		}
	}

	public void printBoard() {
		// Loop through all rows
		for (Tile[] row : tileCave) {
			printString(Arrays.toString(row));
		}
	}
}