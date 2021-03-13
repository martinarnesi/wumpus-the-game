package com.arnesi.wumpus.application;

import com.arnesi.wumpus.model.Board;

/**
 * Hunt the Wumpus Coding Exercise
 * Application start point
 * 	
 * @author MArnesi
 *
 */

public class Main {
	public static void main(String[] args) {
		
		//create all objects
		Board board = new Board(4, 4, 1);
		
		 WumpusGame wg = new WumpusGame(board, null);
		 wg.startGame();
	}
}
