package com.arnesi.wumpus.view;

public final class DrawUserInterface {

	public static void drawBanner() {
		System.out.println("Hunt the Wumpus!"); //Create in View
		System.out.println("");
	}

	public static void drawCommands() {
		System.out.println("Console Commands"); 
		System.out.println("GF --> Hunter go forward.");
		System.out.println("TL --> Hunter turn to the left.");
		System.out.println("TR --> Hunter turn to the right.");
		System.out.println("SHOOT --> Hunter shoot one arrow.");
		System.out.println("EXIT --> Hunter exit.");
		System.out.println("ACTIONS --> Show actions.");
		System.out.println("INSTRUCTIONS --> Show game instructions.");
		System.out.println("ENDGAME --> Quit Game.");
	}

	private DrawUserInterface() {

	}
}