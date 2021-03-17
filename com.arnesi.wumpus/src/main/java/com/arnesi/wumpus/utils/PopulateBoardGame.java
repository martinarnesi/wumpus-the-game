package com.arnesi.wumpus.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.arnesi.wumpus.exception.CreationException;
import com.arnesi.wumpus.model.CaveExit;
import com.arnesi.wumpus.model.EmptyTile;
import com.arnesi.wumpus.model.Entity;
import com.arnesi.wumpus.model.Gold;
import com.arnesi.wumpus.model.Hole;
import com.arnesi.wumpus.model.HoleBreeze;
import com.arnesi.wumpus.model.Hunter;
import com.arnesi.wumpus.model.Tile;
import com.arnesi.wumpus.model.Wumpus;
import com.arnesi.wumpus.model.WumpusStench;

/**
 * Constructor class in charge of initializing the game board
 * with static locations for the entities in this first version of the game.
 * 
 * @author Martin Arnesi
 *
 */
public class PopulateBoardGame {

	public boolean resetBoardGame(Tile[][] tileCave) {
		for (int i = 0; i < tileCave.length; i++) {
			Tile[] tiles = tileCave[i];
			for (int j = 0; j < tiles.length; j++) {
				if (Objects.nonNull(tiles[j])) {
					return false;
				}
				Tile tile = new Tile(i, j);
				tile.addEntity(new EmptyTile(i, j));
				tiles[j] = tile;
			}
		}
		return true;
	}

	/**
	 * Default position of the Hunter
	 * 
	 * @param hunter
	 * @param tile
	 * @return
	 */
	public Hunter setHunterInitialPosition(Hunter hunter, Tile[][] tile) {
		checkIsEmpty(hunter);

		hunter = new Hunter(3, 0);
		tile[hunter.getxPosition()][hunter.getyPosition()].addEntity(hunter);

		return hunter;
	}

	/**
	 * Default position of the CaveExit
	 * 
	 * @param caveExit
	 * @param tile
	 * @return
	 */
	public CaveExit setCaveExitPosition(CaveExit caveExit, Tile[][] tile) {
		checkIsEmpty(caveExit);

		caveExit = new CaveExit(3, 0);
		tile[caveExit.getxPosition()][caveExit.getyPosition()].addEntity(caveExit);

		return caveExit;
	}

	/**
	 * Default position of the Gold
	 * 
	 * @param gold
	 * @param tile
	 * @return
	 */
	public Gold setGoldPosition(Gold gold, Tile[][] tile) {
		checkIsEmpty(gold);

		gold = new Gold(1, 1);
		tile[gold.getxPosition()][gold.getyPosition()].addEntity(gold);

		return gold;
	}

	/**
	 * Default position of the Holes.
	 * 
	 * @param holeList
	 * @param holeQuantity
	 * @param tile
	 * @return
	 */
	public List<Hole> setHolePosition(List<Hole> holeList, int holeQuantity, Tile[][] tile) {
		if (Objects.nonNull(holeList) || holeQuantity < 0) {
			throw new CreationException("Hole creation error.");
		}

		Hole hole;
		holeList = new ArrayList<>();

		switch (holeQuantity) {
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
			hole = new Hole(0, 3);
			tile[hole.getxPosition()][hole.getyPosition()].addEntity(hole);
			tile[0][2].addEntity(new HoleBreeze());
			tile[1][3].addEntity(new HoleBreeze());
			holeList.add(hole);
			break;
		}

		return holeList;
	}

	/**
	 * Default position of the Wumpues
	 * 
	 * @param wumpus
	 * @param tile
	 * @return
	 */
	public Wumpus setWumpusPosition(Wumpus wumpus, Tile[][] tile) {
		checkIsEmpty(wumpus);

		wumpus = new Wumpus(1, 0);
		tile[wumpus.getxPosition()][wumpus.getyPosition()].addEntity(wumpus);
		tile[0][0].addEntity(new WumpusStench());
		tile[1][1].addEntity(new WumpusStench());
		tile[2][0].addEntity(new WumpusStench());

		return wumpus;
	}

	/**
	 * Validate that the entity is not instantiated.
	 * 
	 * @param entity
	 */
	private void checkIsEmpty(Entity entity) {
		if (Objects.nonNull(entity)) {
			throw new CreationException("Entity already exits. Creation error.");
		}
	}
}