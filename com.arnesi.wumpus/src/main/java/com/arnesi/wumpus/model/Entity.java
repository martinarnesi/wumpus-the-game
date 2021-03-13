package com.arnesi.wumpus.model;

import com.arnesi.wumpus.parameters.GameParameters.EntityType;

public abstract class Entity {
	protected EntityType entityType;

	protected Entity(EntityType entityType) {
		this.entityType = entityType;
	}
}