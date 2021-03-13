package com.arnesi.wumpus.parameters;

public final class GameParameters {
	/* 
	 * Basic Board Parameters 
	 */ 
	public static final int PARAMETRISED_GOLD = 1;
	public static final int PARAMETRISED_WUMPUS = 1;
	public static final int PARAMETRISED_HOLES = 2;
	
	/* 
	 * Basic Hunter Parameters 
	 */
	public static final boolean HUNTER_ALIVE = true;
	public static final boolean HUNTER_HAVE_GOLD = false;
	public static final int HUNTER_AMOUT_ARROWS = 3;

    /* 
	 * Game Entities 
	 */
    public enum EntityType {
		HUNTER,
		WUMPUS,
		HOLE,
		GOLD,
		WUNPUS_STENCH_PERCEPTION,
		HOLES_BREEZE_PERCEPTION,
		WALL_SHOCK_PERCEPTION,
		WUMPUS_SCREAM_PERCEPTION,
		EMPTY
	}

    /* 
   	 * Hunter Actions (TBD) 
   	 */
	public enum ActionEnum {
		GO_FORWARD,
		TURN_LEFT,
		TURN_RIGHT,
		SHOOT_ARROW,
		EXIT
	}

	private GameParameters() {}
}