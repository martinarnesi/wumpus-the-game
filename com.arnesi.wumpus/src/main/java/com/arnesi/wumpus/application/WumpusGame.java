package com.arnesi.wumpus.application;

import java.util.Scanner;

import com.arnesi.wumpus.gamelogic.Board;
import com.arnesi.wumpus.view.DrawUserInterface;


/**
 * Hunt the Wumpus Coding Exercise
 * Application start point
 * 	
 * @author MArnesi
 *
 */

public class WumpusGame {

	public static void main(String[] args) {
		new WumpusGame().startGame();
	}

	private void startGame() {
		final Scanner userInput = new Scanner(System.in);

		final Board board = new Board(4, 4, 1);
		board.initializeBoard();

		DrawUserInterface.drawBanner();
		DrawUserInterface.drawCommands();

		boolean gameRunning = true;
		while (gameRunning) {
			System.out.print("$> ");
			String input = userInput.nextLine().toUpperCase();

			switch (input) {
			case "GF":
				System.out.println("ACTION: Hunter go forward...");
				board.hunterActions("GO_FORWARD");
				break;
			case "TL":
				System.out.println("ACTION: Hunter turn 90º to the left...");
				board.hunterActions("TURN_LEFT");
				break;
			case "TR":
				System.out.println("ACTION: Hunter turn 90º to the right...");
				board.hunterActions("TURN_RIGHT");
				break;
			case "SHOOT":
				System.out.println("ACTION: Hunter shoot one arrow!");
				break;
			case "EXIT":
				System.out.println("ACTION: Hunter exit");
				break;
			case "ACTIONS":
				DrawUserInterface.drawCommands();
				break;
			case "INSTRUCTIONS":
				break;
			case "ENDGAME":
				System.out.println("Exiting game! goodbye....");
				gameRunning = false;
				userInput.close();
				break;
			default:
				System.out.printf("Action %s is an invalid action! \n", input);
				break;
			}
			
			board.printBoard();
		}
	}
}