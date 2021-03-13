package com.arnesi.wumpus.model;

import com.arnesi.wumpus.parameters.GameParameters.EntityType;

public class Gold extends Entity {

	public Gold() {
		super(EntityType.GOLD);
	}

	public EntityType getEntityType() {
		return super.entityType;
	}

	@Override
	public String toString() {
		return "Gold";
	}
}