package dad.pruebas.ui;

import java.net.URISyntaxException;
import java.net.URL;

import javafx.animation.Transition;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class HiloMusic extends Thread {
	String file;
	MediaPlayer mediaPlayer;

	public HiloMusic(String file) {
		this.file = file;
	}

	@Override
	public void run() {
		URL path = getClass().getResource( file + ".mp3");
		Media sound;
		try {
			sound = new Media(path.toURI().toString());
			mediaPlayer = new MediaPlayer(sound);
			mediaPlayer.setVolume(0.3);
			mediaPlayer.setCycleCount(Transition.INDEFINITE);
			mediaPlayer.play();
		} catch (URISyntaxException e) {
			e.printStackTrace();
			System.out.println("Papaya");
		}
	}

	public void parar() {
		try {
			mediaPlayer.stop();
		} catch (Exception e) {
		}
		;
	}

}
