package com.arnesi.wumpus.model;

public final class GameConstants {
    public static final int PARAMETRISED_GOLD = 1;
    public static final int PARAMETRISED_WUMPUS = 1;
    public static final int PARAMETRISED_HOLES = 2;
    
    public enum PerceptionsEnum {
    	EMPTY,
    	WUMPUS,
    	WUNPUS_STENCH,
    	HOLES_BREEZE,
    	GOLD,
    	WALL_SHOCK,
    	WUMPUS_SCREAM
    }
    
    public enum ActionEnum {
    	GO_FORWARD(),
    	TURN_LEFT,
    	TURN_RIGHT,
    	SHOOT_ARROW,
    	EXIT,
    	END_GAME
    }
        
	private GameConstants() {
		
	}
}