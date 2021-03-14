package com.arnesi.wumpus.application;

import java.util.Scanner;

import com.arnesi.wumpus.gamelogic.BoardGame;
import com.arnesi.wumpus.gamelogic.HunterActionsEnum;
import static com.arnesi.wumpus.utils.UserInterfaceUtils.drawBanner;
import static com.arnesi.wumpus.utils.UserInterfaceUtils.drawCommands;
import static com.arnesi.wumpus.utils.UserInterfaceUtils.printString;




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

		final BoardGame board = new BoardGame();
		board.initializeBoardGame();

		drawBanner();
		drawCommands();

		boolean gameRunning = true;
		while (gameRunning) {
			System.out.print("$> ");
			String input = userInput.nextLine().toUpperCase();

			switch (input) {
			case "GF":
				printString("ACTION: Hunter go forward...");
				board.boardActions(HunterActionsEnum.GO_FORWARD);
				break;
			case "TL":
				printString("ACTION: Hunter turn 90º to the left...");
				board.boardActions(HunterActionsEnum.TURN_LEFT);
				break;
			case "TR":
				printString("ACTION: Hunter turn 90º to the right...");
				board.boardActions(HunterActionsEnum.TURN_RIGHT);
				break;
			case "SHOOT":
				printString("ACTION: Hunter shoot one arrow!");
				board.boardActions(HunterActionsEnum.SHOOT);
				break;
			case "EXIT":
				printString("ACTION: Hunter tries to exit....");
				if (board.boardActions(HunterActionsEnum.EXIT)) {
					printString("Tou WIN. Game Over");
					gameRunning = false;
					userInput.close();
				}
				break;
			case "COMMANDS":
				drawCommands();
				break;
			case "INSTRUCTIONS":
				break;
			case "ENDGAME":
				printString("Exiting game! goodbye....");
				gameRunning = false;
				userInput.close();
				break;
			default:
				printString("Invalid Command");
				break;
			}
			
			board.printBoard();
		}
	}
}