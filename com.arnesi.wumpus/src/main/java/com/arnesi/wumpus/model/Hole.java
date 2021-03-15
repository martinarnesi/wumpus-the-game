package com.arnesi.wumpus.model;

import com.arnesi.wumpus.parameters.GameParameters.EntityType;

public class Hole extends Entity {
	private int xPosition;
	private int yPosition;

	public Hole(int xPosition, int yPosition) {
		super(EntityType.HOLE);
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}

	public int getxPosition() {
		return xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public EntityType getEntityType() {
		return super.entityType;
	}

	@Override
	public String toString() {
		return "I'm falling into a Hole!!! Ahhhhhhhhhhh!!!!";
	}
}