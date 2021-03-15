package com.arnesi.wumpus.model;

import com.arnesi.wumpus.parameters.GameParameters.EntityType;

public class Gold extends Entity {
	private int xPosition;
	private int yPosition;

	public Gold(int xPosition, int yPosition) {
		super(EntityType.GOLD);
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}

	public int getxPosition() {
		return xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	@Override
	public String toString() {
		return "I found a Gold Ingot! Go to the exit to win!";
	}
}