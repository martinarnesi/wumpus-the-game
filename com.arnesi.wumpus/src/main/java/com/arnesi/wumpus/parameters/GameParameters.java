package com.arnesi.wumpus.parameters;

public final class GameParameters {
	/* 
	 * Basic Board Parameters 
	 */ 
	public static final int PARAMETRISED_BOARD_WIDTH= 4;
	public static final int PARAMETRISED_BOARD_HEIGHT= 4;
	public static final int PARAMETRISED_HOLES_QUANTITY = 3;
	
	/* 
	 * Basic Hunter Parameters 
	 */
	public static final boolean HUNTER_ALIVE = true;
	public static final boolean HUNTER_HAVE_GOLD = false;
	public static final int HUNTER_AMOUT_ARROWS = 2;

    /* 
	 * Game Entities 
	 */
    public enum EntityType {
		HUNTER,
		WUMPUS,
		HOLE,
		GOLD,
		ARROW,
		WUNPUS_STENCH_PERCEPTION,
		HOLES_BREEZE_PERCEPTION,
		WUMPUS_SCREAM_PERCEPTION,
		CAVE_EXIT,
		EMPTY
	}

    private GameParameters() {}
}