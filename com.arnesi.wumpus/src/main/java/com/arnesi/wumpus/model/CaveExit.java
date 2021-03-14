package com.arnesi.wumpus.model;

import com.arnesi.wumpus.parameters.GameParameters.EntityType;

public class CaveExit extends Entity {
	private int xPosition;
	private int yPosition;

	public CaveExit(int xPosition, int yPosition) {
		super(EntityType.CAVE_EXIT);
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
		return "CaveExit";
	}
}