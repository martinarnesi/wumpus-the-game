package com.arnesi.wumpus.model;

import com.arnesi.wumpus.gamelogic.HunterDirectionEnum;
import com.arnesi.wumpus.parameters.GameParameters.EntityType;

public class Hunter extends Entity {
	// Positions and direction in 2D the board.
	private int xPosition;
	private int yPosition;
	private HunterDirectionEnum direction;

	private boolean isAlive;
	private boolean haveGold;
	private int amountOfArrows;

	public Hunter(int xPosition, int yPosition) {
		super(EntityType.HUNTER);
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.direction = HunterDirectionEnum.UP;
		this.isAlive = true;
		this.haveGold = false;
		this.amountOfArrows = 3;
	}

	public EntityType getEntityType() {
		return super.entityType;
	}

	public HunterDirectionEnum getDirection() {
		return direction;
	}

	public void setDirection(HunterDirectionEnum direction) {
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
		return "I'm looking at direction:" + getDirection();
	}
}