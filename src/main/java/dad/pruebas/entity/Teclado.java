package dad.pruebas.entity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {

	public boolean delantepressed, detraspresed, izquierdapresed, derechapresed;

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();
		if (code == KeyEvent.VK_W) {
			delantepressed = true;
		}
		if (code == KeyEvent.VK_S) {
			detraspresed = true;
		}
		if (code == KeyEvent.VK_A) {
			izquierdapresed = true;
		}
		if (code == KeyEvent.VK_D) {
			derechapresed = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_W) {
			delantepressed = false;
		}
		if (code == KeyEvent.VK_S) {
			detraspresed = false;
		}
		if (code == KeyEvent.VK_A) {
			izquierdapresed = false;
		}
		if (code == KeyEvent.VK_D) {
			derechapresed = false;
		}

	}

}
