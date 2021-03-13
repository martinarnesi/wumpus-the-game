package com.arnesi.wumpus.model;

import com.arnesi.wumpus.parameters.GameParameters.EntityType;

public class HolesBreeze extends Entity {

	public HolesBreeze() {
		super(EntityType.HOLES_BREEZE_PERCEPTION);
	}

	@Override
	public String toString() {
		return "HolesBreeze";
	}
}