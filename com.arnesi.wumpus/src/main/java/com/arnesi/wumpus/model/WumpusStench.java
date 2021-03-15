package com.arnesi.wumpus.model;

import com.arnesi.wumpus.parameters.GameParameters.EntityType;

public class WumpusStench extends Entity {
	public WumpusStench() {
		super(EntityType.WUNPUS_STENCH_PERCEPTION);
	}

	@Override
	public String toString() {
		return "I smell a Wunpus Stench...";
	}
}