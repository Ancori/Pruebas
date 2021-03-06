package dad.pruebas.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCombination;

import javafx.stage.Stage;

public class App extends Application {

	private JuegoController controller;
	private static Stage primaryStage;
	private static HiloMusic hiloMusic;

	public void start(Stage primaryStage) throws Exception {

		App.primaryStage = primaryStage;
		controller = new JuegoController();
		Scene scene = new Scene(controller.getView());
		primaryStage.setTitle("Pruebas");
		primaryStage.setFullScreen(true);
		primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		//playMusic("sonidobatalla");
	}

	public static void playMusic(String file) {
		hiloMusic = new HiloMusic(file);
		hiloMusic.run();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static boolean confirm(String titulo, String cabezero, String Contenido) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(primaryStage);
		alert.setTitle(titulo);
		alert.setHeaderText(cabezero);
		alert.setContentText(Contenido);
		return alert.showAndWait().get().equals(ButtonType.OK);
	}

	public static void error(String cabezero, String Contenido) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.initOwner(primaryStage);
		alert.setTitle("Error");
		alert.setHeaderText(cabezero);
		alert.setContentText(Contenido);
		alert.showAndWait();
	}

	public static void info(String titulo, String cabezero, String Contenido) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.initOwner(primaryStage);
		alert.setTitle(titulo);
		alert.setHeaderText(cabezero);
		alert.setContentText(Contenido);
		alert.showAndWait();
	}

}
