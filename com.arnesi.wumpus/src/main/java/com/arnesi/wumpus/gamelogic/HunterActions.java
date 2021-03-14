package com.arnesi.wumpus.gamelogic;

import com.arnesi.wumpus.model.Hunter;
import com.arnesi.wumpus.model.HunterDirectionEnum;
import com.arnesi.wumpus.model.Tile;


public class HunterActions{
	private static final String MOVE = "Moving...";
	private static final String WALL_SHOCK_PERCEPTION = "You hit the cave wall!!";
	private static final String INVALID = "Invalid movement.";
	
	public String moveHunterForward(Hunter hunter, int boardWidth, int boardHeight) {

		switch (hunter.getDirection()) {
			case UP:
				if (hunter.getxPosition() > 0) {
					hunter.setxPosition(hunter.getxPosition() - 1);
					return MOVE;
				} else {
					return WALL_SHOCK_PERCEPTION;
				}
				
			case DOWN:
				if (hunter.getxPosition() < boardWidth - 1) {
					hunter.setxPosition(hunter.getxPosition() + 1);
					return MOVE;
				} else {
					return WALL_SHOCK_PERCEPTION;
				}
				
			case LEFT:
				if (hunter.getyPosition() > 0) {
					hunter.setyPosition(hunter.getyPosition() - 1);
					return MOVE;
				} else {
					return WALL_SHOCK_PERCEPTION;
				}
				
			case RIGHT:
				if (hunter.getyPosition() < boardHeight - 1) {
					hunter.setyPosition(hunter.getyPosition() + 1);
					return MOVE;
				} else {
					return WALL_SHOCK_PERCEPTION;
				}
			default: break;
		}
		return INVALID;
	}
	
	public void turnHunter(HunterActionsEnum hunterAction, Hunter hunter) {
		switch (hunterAction) {
		case TURN_LEFT:
			switch (hunter.getDirection()) {
			case UP:
				hunter.setDirection(HunterDirectionEnum.LEFT);
				break;
			case LEFT:
				hunter.setDirection(HunterDirectionEnum.DOWN);
				break;
			case DOWN:
				hunter.setDirection(HunterDirectionEnum.RIGHT);
				break;
			case RIGHT:
				hunter.setDirection(HunterDirectionEnum.UP);
				break;
			}
			break;
		case TURN_RIGHT:
			switch (hunter.getDirection()) {
			case UP:
				hunter.setDirection(HunterDirectionEnum.RIGHT);
				break;
			case LEFT:
				hunter.setDirection(HunterDirectionEnum.UP);
				break;
			case DOWN:
				hunter.setDirection(HunterDirectionEnum.LEFT);
				break;
			case RIGHT:
				hunter.setDirection(HunterDirectionEnum.DOWN);
				break;
			}
			break;
		}
	}
	
	public String hunterPerception(Tile tile) {
		return null;
	}
}