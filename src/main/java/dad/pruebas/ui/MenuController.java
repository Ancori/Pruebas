package dad.pruebas.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.fxml.Initializable;

public class MenuController implements Initializable {
	
	JuegoController juego=new JuegoController();

	@FXML
	private Button jugarbutton;

	@FXML
	private Button salirbutton;

	@FXML
	private BorderPane view;

	public MenuController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MenuView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	

	}

	@FXML
	void onjugarbutton(ActionEvent event) {
	 view.setCenter(juego.getView());
	}

	@FXML
	void onsalirbutton(ActionEvent event) {
		System.exit(0);
	}

	public BorderPane getView() {
		return view;
	}

}
