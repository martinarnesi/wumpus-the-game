package com.arnesi.wumpus.model;

import java.util.ArrayList;
import java.util.List;

public class Tile {
	private List<Entity> entity;
	private int rowPosition;
	private int columnPosition;

	public Tile(int rowPosition, int columnPosition) {
		this.entity = new ArrayList<>();
		this.rowPosition = rowPosition;
		this.columnPosition = columnPosition;
	}
	
	public List<Entity> getEntity() {
		return entity;
	}

	public void setEntity(List<Entity> entity) {
		this.entity = entity;
	}
	
	public void addEntity(Entity entity) {
		this.entity.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		Entity hunter = null;

		for (Entity ent : this.entity) {
			Entity entTmp = (Entity) ent;
			if (entTmp instanceof Hunter) {
				hunter = ent;
			}
		}

		if (hunter != null) {
			this.entity.remove(hunter);

		}
	}

	public int getRowPosition() {
		return rowPosition;
	}

	public void setRowPosition(int rowPosition) {
		this.rowPosition = rowPosition;
	}

	public int getColumnPosition() {
		return columnPosition;
	}

	public void setColumnPosition(int columnPosition) {
		this.columnPosition = columnPosition;
	}

	@Override
	public String toString() {
		return ""+ entity;
	}
}