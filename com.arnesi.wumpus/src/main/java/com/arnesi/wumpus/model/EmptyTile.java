package com.arnesi.wumpus.model;

import com.arnesi.wumpus.parameters.GameParameters.EntityType;

public class EmptyTile extends Entity {
	private int xPosition;
	private int yPosition;

	public EmptyTile(int xPosition, int yPosition) {
		super(EntityType.EMPTY);
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}

	@Override
	public String toString() {
		return "X="+ xPosition +"|Y="+ yPosition;
	}
}