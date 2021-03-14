package com.arnesi.wumpus.model;

import com.arnesi.wumpus.parameters.GameParameters.EntityType;

public class HoleBreeze extends Entity {
	public HoleBreeze() {
		super(EntityType.HOLES_BREEZE_PERCEPTION);
	}

	@Override
	public String toString() {
		return "HoleBreeze";
	}
}