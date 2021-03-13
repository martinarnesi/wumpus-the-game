package com.arnesi.wumpus.model;

import java.util.Arrays;

import com.arnesi.wumpus.model.GameConstants.PerceptionsEnum;

public class Board {
	
	private int boardWidth;
	private int boardHeight;
	private int startingPosition;
	private Tile[][] tileMatrix;
	
	private static final int gold = GameConstants.PARAMETRISED_GOLD;
	private static final int wumpus = GameConstants.PARAMETRISED_WUMPUS;
	private static final int holes = GameConstants.PARAMETRISED_HOLES;
	
	private Hunter hunter;
	
	public Board(int boardWidth, int boardHeight, int startPosition) {
		this.tileMatrix = new Tile[boardWidth][boardHeight];
		this.hunter = new Hunter();
		fillBoard();
		
	}
	
	public void printBoard() {
        // Loop through all rows
        for (Object[] row : tileMatrix) {
            System.out.println(Arrays.toString(row));
        }
    }
	
	private void fillBoard() {
		for (Tile[] tiles : tileMatrix) {
			Arrays.fill(tiles, new Tile(PerceptionsEnum.EMPTY));
		}
	}
}
