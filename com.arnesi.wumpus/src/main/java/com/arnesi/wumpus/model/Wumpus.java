package com.arnesi.wumpus.model;

import com.arnesi.wumpus.parameters.GameParameters.EntityType;

public class Wumpus extends Entity {

	private boolean isAlive;

	public Wumpus(boolean isAlive) {
		super(EntityType.WUMPUS);
		this.isAlive = isAlive;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	@Override
	public String toString() {
		return "Wumpus";
	}
}