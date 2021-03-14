package com.arnesi.wumpus.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.arnesi.wumpus.exception.CreationException;
import com.arnesi.wumpus.model.CaveExit;
import com.arnesi.wumpus.model.EmptyTile;
import com.arnesi.wumpus.model.Gold;
import com.arnesi.wumpus.model.Hole;
import com.arnesi.wumpus.model.HoleBreeze;
import com.arnesi.wumpus.model.Hunter;
import com.arnesi.wumpus.model.Tile;
import com.arnesi.wumpus.model.Wumpus;
import com.arnesi.wumpus.model.WumpusStench;

public class PopulateBoardGame {

	public boolean resetBoardGame(Tile[][] tileCave) {
		for (int i = 0; i < tileCave.length; i++) {
			Tile[] tiles = tileCave[i];
			for (int j = 0; j < tiles.length; j++) {
				Tile tile = new Tile(i, j);
				tile.addEntity(new EmptyTile(i, j));
				tiles[j] = tile;
			}
		}

		// TODO Validate that reset was ok (check array tiles arr all emptytiles

		return true;

	}

	public Hunter setHunterInitialPosition(Hunter hunter, Tile[][] tile) {
		if (Objects.nonNull(hunter)) {
			throw new CreationException("Hunter creation error.");
		}

		hunter = new Hunter(3, 0);
		tile[hunter.getxPosition()][hunter.getyPosition()].addEntity(hunter);

		return hunter;
	}

	public CaveExit setCaveExitPosition(CaveExit caveExit, Tile[][] tile) {
		if (Objects.nonNull(caveExit)) {
			throw new CreationException("CaveExit creation error.");
		}

		caveExit = new CaveExit(3, 0);
		tile[caveExit.getxPosition()][caveExit.getyPosition()].addEntity(caveExit);

		return caveExit;
	}
	
	public Gold setGoldPosition(Gold gold, Tile[][] tile) {
		if (Objects.nonNull(gold)) {
			throw new CreationException("Gold creation error.");
		}

		gold = new Gold(1, 1);
		tile[gold.getxPosition()][gold.getyPosition()].addEntity(gold);

		return gold;
	}
	
	public List<Hole> setHolePosition(List<Hole> holeList, int holeQuantity, Tile[][] tile) {
		if (Objects.nonNull(holeList)) {
			throw new CreationException("Hole creation error.");
		}
		
		Hole hole;
		holeList = new ArrayList<>();

		switch(holeQuantity) {
		case 3:
			hole = new Hole(3, 2);
			tile[hole.getxPosition()][hole.getyPosition()].addEntity(hole);
			tile[3][1].addEntity(new HoleBreeze());
			tile[2][2].addEntity(new HoleBreeze());
			tile[3][3].addEntity(new HoleBreeze());
			holeList.add(hole);
		case 2:
			hole = new Hole(1, 2);
			tile[hole.getxPosition()][hole.getyPosition()].addEntity(hole);
			tile[0][2].addEntity(new HoleBreeze());
			tile[1][1].addEntity(new HoleBreeze());
			tile[1][3].addEntity(new HoleBreeze());
			tile[2][2].addEntity(new HoleBreeze());
			holeList.add(hole);
		case 1:
			hole = new Hole(0, 3);
			tile[hole.getxPosition()][hole.getyPosition()].addEntity(hole);
			tile[0][2].addEntity(new HoleBreeze());
			tile[1][3].addEntity(new HoleBreeze());
			holeList.add(hole);
			break;
		default:
			break;
		}

		return holeList;
	}

	public Wumpus setWumpusPosition(Wumpus wumpus, Tile[][] tile) {
		if (Objects.nonNull(wumpus)) {
			throw new CreationException("Wumpus creation error.");
		}

		wumpus = new Wumpus(1, 0);
		tile[wumpus.getxPosition()][wumpus.getyPosition()].addEntity(wumpus);

		return wumpus;
	}
	
	public WumpusStench setWumpusStenchPosition(WumpusStench wumpusStench, Tile[][] tile) {
		if (Objects.nonNull(wumpusStench)) {
			throw new CreationException("WumpusStench creation error.");
		}

		wumpusStench = new WumpusStench(2, 0);
		tile[wumpusStench.getxPosition()][wumpusStench.getyPosition()].addEntity(wumpusStench);

		return wumpusStench;
	}
	
	
	

	
	
	
	//	public Entity setEntityPosition(Entity entity, int xPosition, int yPosition, Tile[][] tile) {
//		if (Objects.nonNull(entity)) {
//			throw new CreationException("Entity creation error.");
//		}
//
//		if (entity instanceof Gold ) {
//			entity = (Gold)new Gold(1, 1);
//			tile[entity.getxPosition()][((Gold) entity).getyPosition()].addEntity(gold);
//		}
//		
//
//		return entity;
//	}
	
}