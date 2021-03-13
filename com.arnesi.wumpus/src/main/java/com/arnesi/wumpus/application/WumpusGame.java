package com.arnesi.wumpus.application;

import java.util.Scanner;

import com.arnesi.wumpus.model.Board;
import com.arnesi.wumpus.model.Hunter;
import com.arnesi.wumpus.view.DrawUserInterface;

public class WumpusGame {
	private final Board board;
	private final Hunter hunter;

	public WumpusGame(Board board, Hunter hunter) {
		this.board = board;
		this.hunter = hunter;
	}

	public void startGame() {
		boolean gameRunning = true;
		Scanner userInput = new Scanner(System.in);

		DrawUserInterface.drawBanner();
		DrawUserInterface.drawCommands();

		while (gameRunning) {

			System.out.print("$> ");
			String input = userInput.nextLine().toUpperCase();

			switch (input) {
			case "GF":
				System.out.println("ACTION: Hunter go forward...");
				break;
			case "TL":
				System.out.println("ACTION: Hunter turn 90º to the left...");
				break;
			case "TR":
				System.out.println("ACTION: Hunter turn 90º to the right...");
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
				//TODO Add game Instructions
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
