package com.arnesi.wumpus.model;

import com.arnesi.wumpus.parameters.GameParameters.EntityType;

public class Hole extends Entity {
	public Hole() {
		super(EntityType.HOLE);
	}

	public EntityType getEntityType() {
		return super.entityType;
	}

	@Override
	public String toString() {
		return "Hole";
	}
}