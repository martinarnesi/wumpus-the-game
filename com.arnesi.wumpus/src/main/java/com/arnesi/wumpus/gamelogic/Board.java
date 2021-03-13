package com.arnesi.wumpus.gamelogic;

import java.util.Arrays;

import com.arnesi.wumpus.exception.CreationException;
import com.arnesi.wumpus.model.EmptyTile;
import com.arnesi.wumpus.model.Hunter;
import com.arnesi.wumpus.model.Hunter.Direction;
import com.arnesi.wumpus.model.Tile;
import com.arnesi.wumpus.parameters.GameParameters;
import com.arnesi.wumpus.parameters.GameParameters.EntityType;

public class Board {
	
	private final int boardWidth;
	private final int boardHeight;
	private final int startingPosition;
	private final Tile[][] tileMatrix;
	private final Hunter hunter;
	
	private static final int gold = GameParameters.PARAMETRISED_GOLD;
	private static final int wumpus = GameParameters.PARAMETRISED_WUMPUS;
	private static final int holes = GameParameters.PARAMETRISED_HOLES;
	
	/**
	 * Build the board with given dimensions and create a Hunter with default values.
	 * @param boardWidth
	 * @param boardHeight
	 * @param startingPosition
	 */
	public Board(int boardWidth, int boardHeight, int startingPosition) {
		
		if (boardWidth <= 2 && boardHeight <= 2) {
            throw new CreationException(
            		String.format(
            				"The board size %s x %s is too small and cannot be less than 3x3. "
            				+ "Negative numbers are not allowed.",
            				boardWidth, boardHeight));
        }
		
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
		this.startingPosition = startingPosition;
		this.tileMatrix = new Tile[boardWidth][boardHeight];
		this.hunter = new Hunter(3,0);
	}
	
	public void initializeBoard() {
		resetBoardAndFillWithEmptyTiles();
		setHunterStartPositionPosition(hunter, tileMatrix);
		
		//populate board with entities [GOLD, WUMPUS, HOLES, STENCH and BREEZE]
	}
	
	
	public void setHunterStartPositionPosition(Hunter hunter, Tile[][] tile) {
		tile[hunter.getxPosition()][hunter.getyPosition()].addEntity(hunter);
	}
	
	public String hunterActions(String action) {
		hunter.getDirection();
	
		switch(action) {
			case "GO_FORWARD":
				tileMatrix[hunter.getxPosition()][hunter.getyPosition()].removeEntity(hunter);
				moveHunterForward();
				tileMatrix[hunter.getxPosition()][hunter.getyPosition()].addEntity(hunter);
				break;
			case "TURN_LEFT":
				turnHunter("TURN_LEFT");
				System.out.println("Hunter Direction: " + hunter.getDirection());
				break;	
			case "TURN_RIGHT":
				turnHunter("TURN_RIGHT");
				System.out.println("Hunter Direction: " + hunter.getDirection()); 
				break;	
			default:
				break;
		}
		
		return "GO_FORWARD";
	}
	
	public void moveHunterForward() {

		String direction = hunter.getDirection().toString();

		switch(direction) {
		case "UP":
			if(hunter.getxPosition() > 0) {
				hunter.setxPosition(hunter.getxPosition() - 1);
			} else {
				System.out.println(EntityType.WALL_SHOCK_PERCEPTION.toString());
			}
			break;
		case "DOWN":
			if(hunter.getxPosition() < boardWidth - 1) {
				hunter.setxPosition(hunter.getxPosition() + 1);
			} else {
				System.out.println(EntityType.WALL_SHOCK_PERCEPTION.toString());
			}
			break;
		case "LEFT":
			if(hunter.getyPosition() > 0) {
				hunter.setyPosition(hunter.getyPosition() - 1);
			} else {
				System.out.println(EntityType.WALL_SHOCK_PERCEPTION.toString());
			}
			break;
		case "RIGHT":
			if(hunter.getyPosition() < boardHeight - 1) {
				hunter.setyPosition(hunter.getyPosition() + 1);

			} else {
				System.out.println(EntityType.WALL_SHOCK_PERCEPTION.toString());
			}
			break;
		default:
			System.out.println("Invalid");
			break;
		}
	}
	
	public void turnHunter(String direction) {
		switch(direction) {

		case "TURN_LEFT":
			// Mover counter clockwise
			switch (hunter.getDirection().toString()) {
				case "UP": hunter.setDirection(Direction.LEFT); break;
				case "LEFT": hunter.setDirection(Direction.DOWN); break;
				case "DOWN": hunter.setDirection(Direction.RIGHT); break;
				case "RIGHT": hunter.setDirection(Direction.UP); break;
			}
			break;
		case "TURN_RIGHT":
			// Mover clockwise
			switch (hunter.getDirection().toString()) {
				case "UP": hunter.setDirection(Direction.RIGHT); break;
				case "LEFT": hunter.setDirection(Direction.UP); break;
				case "DOWN": hunter.setDirection(Direction.LEFT); break;
				case "RIGHT": hunter.setDirection(Direction.DOWN); break;
			}
			break;
		}
	}
	
	public String hunterPerception(Tile tile) {
		return null;
	}
	
	public void printBoard() {
        // Loop through all rows
        for (Tile[] row : tileMatrix) {
            System.out.println(Arrays.toString(row));
        }
    }
	
//	private void fillBoard() {
//		for (Tile[] tiles : tileMatrix) {
//			Entity hunter = new Hunter(true, false, 3);
//			Arrays.fill(tiles, new Tile(hunter, 1, 1));
//
//			Entity emptyTitle = new EmptyTile();
//			Arrays.fill(tiles, new Tile(emptyTitle, 1, 1));
//		}
//	}
	
	private void resetBoardAndFillWithEmptyTiles() {
		for (int i = 0; i < tileMatrix.length; i++) {
			Tile[] tiles = tileMatrix[i];
			for (int j = 0; j < tiles.length; j++) {
				Tile tile = new Tile(i, j);
				tile.addEntity(new EmptyTile());
				tiles[j] = tile;
			}
		}
	}
	
//	public String gameStatus(ActionEnum action) {
//	
//	return null;
//}
}