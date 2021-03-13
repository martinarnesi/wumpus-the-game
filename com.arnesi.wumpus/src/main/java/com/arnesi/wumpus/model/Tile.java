package com.arnesi.wumpus.model;

import com.arnesi.wumpus.model.GameConstants.PerceptionsEnum;

public class Tile {
	private PerceptionsEnum perceptionsEnum;

	public Tile(PerceptionsEnum perceptionsEnum) {
		this.perceptionsEnum = perceptionsEnum;
	}

	@Override
	public String toString() {
		return "perception=" + perceptionsEnum;
	}
}
