package com.arnesi.wumpus.utils;

public final class UserInterfaceUtils {

	public static void drawBanner() {
		System.out.println("Hunt the Wumpus!"); //Create in View
		System.out.println("");
	}

	public static void drawCommands() {
		System.out.println("************Console Commands***********"); 
		System.out.println("Type GF		--> Hunter go forward.");
		System.out.println("Type TL		--> Hunter turn to the left.");
		System.out.println("Type TR		--> Hunter turn to the right.");
		System.out.println("Type SHOOT	--> Hunter shoot one arrow.");
		System.out.println("Type EXIT	--> Hunter exit.");
		System.out.println("Type CMD	--> Show actions.");
		System.out.println("Type INST	--> Show game instructions.");
		System.out.println("Type ENDGAME--> Quit Game.");
	}
	
	public static void printString(String string) {
		System.out.println("Message: "+ string);
	}

	private UserInterfaceUtils() {

	}
}