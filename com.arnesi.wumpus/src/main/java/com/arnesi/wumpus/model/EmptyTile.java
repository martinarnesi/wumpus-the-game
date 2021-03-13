package com.arnesi.wumpus.model;

import com.arnesi.wumpus.parameters.GameParameters.EntityType;

public class EmptyTile extends Entity {
	public EmptyTile() {
		super(EntityType.EMPTY);
	}

	@Override
	public String toString() {
		return "EmptyTile";
	}
}