package com.arnesi.wumpus.application;

import static com.arnesi.wumpus.utils.UserInterfaceUtils.drawBanner;
import static com.arnesi.wumpus.utils.UserInterfaceUtils.drawCommands;
import static com.arnesi.wumpus.utils.UserInterfaceUtils.printString;
import static com.arnesi.wumpus.utils.UserInterfaceUtils.drawInstructions;

import java.util.Scanner;

import com.arnesi.wumpus.gamelogic.BoardGame;
import com.arnesi.wumpus.gamelogic.HunterActionsEnum;

/**
 * Hunt the Wumpus v1.0 - Airliquide Healthcare Evaluation
 * @author Martin Arnesi - March 2021 - Rosario - SF - Argentina
 *
 */
public class WumpusGame {

	private static final String INVALID_COMMAND = "Invalid Command";
	private static final String QUIT_GAME = "Exiting game! goodbye....";
	private static final String HUNTER_WIN = "CONGRATULATIONS!!! YOU WIN. GAME OVER";
	private static final String HUNTER_TRIES_TO_EXIT = "ACTION: Hunter tries to exit....";
	private static final String HUNTER_SHOOT = "ACTION: Hunter shoot one arrow!";
	private static final String HUNTER_TURN_RIGHT = "ACTION: Hunter turn 90º to the right...";
	private static final String HUNTER_TURN_LEFT = "ACTION: Hunter turn 90º to the left...";
	private static final String YOUR_ARE_DEAD = "Your are DEAD! Game Over";
	private static final String HUNTER_GO_FORWARD = "ACTION: Hunter go forward...";
	private static final String CANT_EXIT = "You can't leave the cave. (no Gold or not at cave exit)";
	private static final String ENDGAME = "ENDGAME";
	private static final String INSTRUCTIONS = "INST";
	private static final String COMMANDS = "CMD";
	private static final String EXIT = "EXIT";
	private static final String SHOOT = "SHOOT";
	private static final String TURN_RIGHT = "TR";
	private static final String TURN_LEFT = "TL";
	private static final String GO_FORWARD = "GF";
	private static final String COMMAND_SYMBOL = "$> ";

	public static void main(String[] args) {
		new WumpusGame().startGame();
	}

	/**
	 * Game StartPoint
	 */
	public void startGame() {
		final Scanner userInput = new Scanner(System.in);
		final BoardGame board = new BoardGame();

		board.initializeBoardGame();

		drawBanner();
		drawCommands();
		
		boolean gameRunning = true;
		
		while (gameRunning) {
			System.out.print(COMMAND_SYMBOL);
			String consoleInput = userInput.nextLine().toUpperCase();

			switch (consoleInput) {
			case GO_FORWARD:
				printString(HUNTER_GO_FORWARD);
				if (!board.boardActions(HunterActionsEnum.GO_FORWARD)) {
					printString(YOUR_ARE_DEAD);
					gameRunning = false;
					userInput.close();
				}
				break;
				
			case TURN_LEFT:
				printString(HUNTER_TURN_LEFT);
				board.boardActions(HunterActionsEnum.TURN_LEFT);
				break;
				
			case TURN_RIGHT:
				printString(HUNTER_TURN_RIGHT);
				board.boardActions(HunterActionsEnum.TURN_RIGHT);
				break;
				
			case SHOOT:
				printString(HUNTER_SHOOT);
				board.boardActions(HunterActionsEnum.SHOOT);
				break;
				
			case EXIT:
				printString(HUNTER_TRIES_TO_EXIT);
				if (board.boardActions(HunterActionsEnum.EXIT)) {
					printString(HUNTER_WIN);
					gameRunning = false;
					userInput.close();
				} else {
					printString(CANT_EXIT);
				}
				break;
				
			case COMMANDS:
				drawCommands();
				break;
				
			case INSTRUCTIONS:
				drawInstructions();
				break;
				
			case ENDGAME:
				printString(QUIT_GAME);
				gameRunning = false;
				userInput.close();
				break;
				
			default:
				printString(INVALID_COMMAND);
				break;
			}
		}
	}
}