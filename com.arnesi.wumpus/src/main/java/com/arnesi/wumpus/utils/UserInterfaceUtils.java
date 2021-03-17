package com.arnesi.wumpus.utils;

/**
 * Class in charge of containing the Banner and the main menu of the game.
 * 
 * @author Martin
 *
 */
public final class UserInterfaceUtils {
	private static final String BANNER = ""
			+ " _     _     _      _____    _____  _     _____   _      _     _      ____  _     ____ \r\n"
			+ "/ \\ /|/ \\ /\\/ \\  /|/__ __\\  /__ __\\/ \\ /|/  __/  / \\  /|/ \\ /\\/ \\__/|/  __\\/ \\ /\\/ ___\\\r\n"
			+ "| |_||| | ||| |\\ ||  / \\      / \\  | |_|||  \\    | |  ||| | ||| |\\/|||  \\/|| | |||    \\\r\n"
			+ "| | ||| \\_/|| | \\||  | |      | |  | | |||  /_   | |/\\||| \\_/|| |  |||  __/| \\_/|\\___ |\r\n"
			+ "\\_/ \\|\\____/\\_/  \\|  \\_/      \\_/  \\_/ \\|\\____\\  \\_/  \\|\\____/\\_/  \\|\\_/   \\____/\\____/\r\n"
			+ " by Martin Arnesi.";

	private static final String COMMANDS = "" + " 		HUNT THE WUMPUS - GAME COMMANDS\n" + "| Options:\n"
			+ "|        1. Type GF: 	  Hunter go forward.\n"
			+ "|        2. Type TL: 	  Hunter turn to the left.\n"
			+ "|        3. Type TR: 	  Hunter turn to the right.\n" + "|        4. Type SHOOT:   Shoot 1 arrow.\n"
			+ "|        5. Type EXIT:	  Hunter exit.\n" + "|        6. Type CMD:	  Show this list of actions.\n"
			+ "|        7. Type INST	  Show game instructions.\n" + "|        8. Type ENDGAME: Quit Game." + "";

	private static final String INSTRUCTIONS = "Humt the Wumpus by Martin Arnesi\r\n"
			+ "\r\n"
			+ "You are a daring hunter in search of a gold bar in a dark cavern with 4 x 4 cells.\r\n"
			+ "\r\n"
			+ "As the hunter, you may encounter dangers along the way.\r\n"
			+ "\r\n"
			+ "Bottomless pits, in which you will meet certain death and a fearsome monster called \"The Wumpus\"\r\n"
			+ "who will tear you apart as soon as he gets the first chance.\r\n"
			+ "\r\n"
			+ "Luckily, the hunter is armed with his fearsome bow, but he only has 3 arrows at his disposal.\r\n"
			+ "\r\n"
			+ "The hunter can turn 90 degrees to the left or right, and from that angle, he can advance.\r\n"
			+ "\r\n"
			+ "He can also shoot at anything in front of his line of sight.\r\n"
			+ "\r\n"
			+ "The final goal of the game is to get out of the cave alive with the gold bar.  \r\n"
			+ "You can eliminate the Wumpus to be able to move more easily through the dark spaces of this place.\r\n"
			+ "\r\n"
			+ "If you don't have the gold bar at the exit, you will not be able to leave the cave.\r\n"
			+ "Either you fall into a pit, and you meet the Wumpus, the game ends.\r\n"
			+ "\r\n"
			+ "Good luck!\r\n";
	
	private static final String MAP = ""
			+ "+--------++--------++--------++--------+                \r\n"
			+ "|        ||        ||        ||        |                \r\n"
			+ "|        ||        ||        ||        |                \r\n"
			+ "|        ||        ||        ||        |                \r\n"
			+ "+--------++--------++--------++--------+                \r\n"
			+ "|        ||        ||        ||        |                \r\n"
			+ "|        ||        ||        ||        |                \r\n"
			+ "|        ||        ||        ||        |                \r\n"
			+ "+--------++--------++--------++--------+                \r\n"
			+ "|        ||        ||        ||        |                \r\n"
			+ "|        ||        ||        ||        |                \r\n"
			+ "|        ||        ||        ||        |                \r\n"
			+ "+--------++--------++--------++--------+                \r\n"
			+ "| HUNTER ||        ||        ||        |                \r\n"
			+ "|CAVEEXIT||        ||        ||        |                \r\n"
			+ "| x=3,Y=0||        ||        ||        |                \r\n"
			+ "+--------++--------++--------++--------+                ";
			
	public static void drawBanner() {
		System.out.println(BANNER);
	}

	public static void drawCommands() {
		System.out.println(COMMANDS);
	}
	
	public static void drawInstructions() {
		System.out.println(INSTRUCTIONS);
		System.out.println(MAP);
	}

	public static void printString(String string) {
		System.out.println("Message: " + string);
	}

	private UserInterfaceUtils() {
	}
}