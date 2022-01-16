package dad.pruebas.tile;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import dad.pruebas.entity.Jugador;
import dad.pruebas.entity.Teclado;

public class GamePane extends JPanel implements Runnable {

	final int originaltilesize = 16;
	final int scale = 3;
	public final int tileSize = originaltilesize * scale;
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int alto = tileSize * maxScreenCol;
	final int ancho = tileSize * maxScreenRow;

	int FPS = 60;
	TileManager tilem=new TileManager(this);
	Teclado teclado = new Teclado();
	Thread gameThread;
	Jugador jugador = new Jugador(this, teclado);

	public GamePane() {
		this.setPreferredSize(new Dimension(alto, ancho));
		this.setBackground(Color.yellow);
		this.setDoubleBuffered(true);
		this.addKeyListener(teclado);
		this.setFocusable(true);
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();

	}

	@Override
	public void run() {
		while (gameThread != null) {
			update();
			repaint();
			moverpersonaje();
		}
	}

	public void update() {
		jugador.update();
	}

	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g2 = (Graphics2D) graphics;
		tilem.draw(g2);
		jugador.draw(g2);
		g2.dispose();
	}

	private void moverpersonaje() {
		double intervalodibujar = 1000000000 / FPS;
		double siguientedibujo = System.nanoTime() + intervalodibujar;
		try {
			double tiemporequerido = siguientedibujo - System.nanoTime();
			tiemporequerido = tiemporequerido / 1000000;
			if (tiemporequerido < 0) {
				tiemporequerido = 0;
			}
			Thread.sleep((long) tiemporequerido);
			siguientedibujo += intervalodibujar;

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
