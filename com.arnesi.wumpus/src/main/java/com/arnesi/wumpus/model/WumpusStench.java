package com.arnesi.wumpus.model;

import com.arnesi.wumpus.parameters.GameParameters.EntityType;

public class WumpusStench extends Entity {
	private int xPosition;
	private int yPosition;

	public WumpusStench(int xPosition, int yPosition) {
		super(EntityType.WUNPUS_STENCH_PERCEPTION);
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}

	public int getxPosition() {
		return xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	@Override
	public String toString() {
		return "WunpusStench";
	}
}