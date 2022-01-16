package dad.pruebas.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import dad.pruebas.tile.GamePane;

public class Jugador extends Entity {

	GamePane gp;
	Teclado teclado;

	public Jugador(GamePane gp, Teclado teclado) {
		this.gp = gp;
		this.teclado = teclado;
		setDefault();
		getimage();
	}

	public void setDefault() {
		posicionx = 100;
		posiciony = 100;
		velocidad = 4;
		direccion = "arriba";

	}

	public void getimage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("../tiles/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("../tiles/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("../tiles/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("../tiles/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("../tiles/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("../tiles/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("../tiles/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("../tiles/boy_right_2.png"));

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void update() {

		if (teclado.delantepressed == true) {
			direccion = "arriba";
			posiciony -= velocidad;
			movimiento++;

		} else if (teclado.detraspresed == true) {
			direccion = "abajo";
			posiciony += velocidad;
			movimiento++;

		} else if (teclado.izquierdapresed == true) {
			direccion = "izquierda";
			posicionx -= velocidad;
			movimiento++;

		} else if (teclado.derechapresed == true) {
			direccion = "derecha";
			posicionx += velocidad;
			movimiento++;
		}
		
		if (movimiento > 1) {
			movimiento = 0;
		}
		
	}

	public void draw(Graphics2D g2) {

		// g2.setColor(Color.black);
		// g2.fillRect(posicionx, posiciony, gp.tileSize, gp.tileSize);
		BufferedImage image = null;
		switch (direccion) {
		case "arriba":
			if (movimiento == 0) {
				image = up1;
			} else {
				image = up2;
			}
			break;
		case "abajo":
			if (movimiento == 0) {
				image = down1;
			} else {
				image = down2;
			}
			break;
		case "izquierda":
			if (movimiento == 0) {
				image = left1;
			} else {
				image = left2;
			}
			break;
		case "derecha":
			if (movimiento == 0) {
				image = right1;
			} else {
				image = right2;
			}
			break;
		}
		g2.drawImage(image, posicionx, posiciony, gp.tileSize, gp.tileSize, null);

	}

}
