package dad.pruebas.tile;

import java.awt.Graphics2D;

import javax.imageio.ImageIO;

public class TileManager {

	GamePane gp;
	Tile[] tile;

	public TileManager(GamePane gp) {
		this.gp = gp;
		tile = new Tile[10];
		getimage();
	}

	public void getimage() {
		try {

			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("../tiles/water.png"));
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("../tiles/grass.png"));
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("../tiles/earth.png"));

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void draw(Graphics2D g2) {
		int alturacasilla = 0;
		for (int i = 0; i < gp.alto; i++) {
			g2.drawImage(tile[0].image, alturacasilla, 0, gp.tileSize, gp.tileSize, null);
			alturacasilla += 48;
		}
		int anchocasilla = 0;
		for (int i = 0; i < gp.alto; i++) {
			g2.drawImage(tile[0].image, 0, anchocasilla, gp.tileSize, gp.tileSize, null);
			anchocasilla += 48;
		}
	      anchocasilla = 48;
		for (int i = 0; i < gp.alto; i++) {
			g2.drawImage(tile[0].image, anchocasilla, 528, gp.tileSize, gp.tileSize, null);
			anchocasilla+=48;
		}

	}

}
