package dad.pruebas.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

public class ViewController implements Initializable {

	@FXML
	private Label pokemon;
	@FXML
	private AnchorPane view;

	public ViewController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Juego.fxml"));
		loader.setController(this);
		loader.load();
	}

	public void initialize(URL location, ResourceBundle resources) {
		
		view.setStyle("-fx-background-image:url('file:fondo.gif')");
	
		pokemon.addEventFilter(KeyEvent.KEY_PRESSED, key -> {

			switch (key.getCode()) {
			case W:
			case UP:
				if(pokemon.getLayoutY()>0){
				pokemon.setLayoutY(pokemon.getLayoutY() - 10);	
				}
				
				break;
			case A:
			case LEFT:
				if(pokemon.getLayoutX()>0){
				pokemon.setLayoutX(pokemon.getLayoutX() - 10);
				}
				break;
			case D:
			case RIGHT:
				if(pokemon.getLayoutX()<App.getPrimaryStage().getWidth()-71) {
					pokemon.setLayoutX(pokemon.getLayoutX() + 10);
				}
				break;
			case S:
			case DOWN:
				if(pokemon.getLayoutY()<App.getPrimaryStage().getHeight()-66) {
				pokemon.setLayoutY(pokemon.getLayoutY() + 10);
				}
				break;
			case ENTER:
				System.out.println("Enter");
				break;
			case ESCAPE:
				System.exit(0);
				break;
			default:
			}
		});
		view.requestFocus();
		pokemon.setFocusTraversable(true);
		pokemon.requestFocus();
	}

	public AnchorPane getView() {
		return view;
	}

}
