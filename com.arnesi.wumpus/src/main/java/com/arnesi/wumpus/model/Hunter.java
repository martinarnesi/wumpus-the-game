package com.arnesi.wumpus.model;

import com.arnesi.wumpus.parameters.GameParameters;
import com.arnesi.wumpus.parameters.GameParameters.EntityType;

public class Hunter extends Entity{
	public enum Direction {
		UP,
		DOWN,
		LEFT,
		RIGHT
	}

	//Positions and direction in 2D the board.
	private int xPosition;
	private int yPosition;
	private Direction direction;


	private boolean isAlive = GameParameters.HUNTER_ALIVE;
	private boolean haveGold = GameParameters.HUNTER_HAVE_GOLD;
	private int amountOfArrows = GameParameters.HUNTER_AMOUT_ARROWS;

	public Hunter(int xPosition, int yPosition) {
		super(EntityType.HUNTER);
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.direction = Direction.UP;
	}

	public EntityType getEntityType() {
		return super.entityType;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public int getxPosition() {
		return xPosition;
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public boolean isHaveGold() {
		return haveGold;
	}

	public void setHaveGold(boolean haveGold) {
		this.haveGold = haveGold;
	}

	public int getAmountOfArrows() {
		return amountOfArrows;
	}

	public void setAmountOfArrows(int amountOfArrows) {
		this.amountOfArrows = amountOfArrows;
	}

	@Override
	public String toString() {
		return "Hunter is in X:" + getxPosition() + " Y:"+ getyPosition() + "Direction=" + getDirection();
	}
}