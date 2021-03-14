package com.arnesi.wumpus.model;

import com.arnesi.wumpus.parameters.GameParameters.EntityType;

public class Wumpus extends Entity {
	private int xPosition;
	private int yPosition;
	private boolean isAlive;

	public Wumpus(int xPosition, int yPosition) {
		super(EntityType.WUMPUS);
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.isAlive = true;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public int getxPosition() {
		return xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	@Override
	public String toString() {
		return "Wumpus";
	}
}