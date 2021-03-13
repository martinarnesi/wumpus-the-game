package com.arnesi.wumpus.model;

import com.arnesi.wumpus.parameters.GameParameters.EntityType;

public class WunpusStench extends Entity {
	public WunpusStench() {
		super(EntityType.WUNPUS_STENCH_PERCEPTION);
	}

	@Override
	public String toString() {
		return "WunpusStench";
	}
}