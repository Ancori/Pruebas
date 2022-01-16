package dad.pruebas.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.pruebas.pokemon.Ataque;
import dad.pruebas.pokemon.Combate;
import dad.pruebas.pokemon.Pokemon;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

import javafx.scene.layout.BorderPane;
import javafx.util.converter.NumberStringConverter;

public class JuegoController implements Initializable {

	private ObjectProperty<Combate> combate = new SimpleObjectProperty<>();
	private ObjectProperty<Pokemon> PokemonSeleccionado = new SimpleObjectProperty<>();
	private ObjectProperty<Pokemon> Pokemoncpu = new SimpleObjectProperty<>();
	private ListProperty<Pokemon> entrenador1 = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<Pokemon> entrenador2 = new SimpleListProperty<>(FXCollections.observableArrayList());
	private DoubleProperty vida=new SimpleDoubleProperty();
	private BooleanProperty ganador = new SimpleBooleanProperty();

	@FXML
	private ImageView pokemon1;

	@FXML
	private ImageView pokemon2;

	@FXML
	private ComboBox<Pokemon> comboPokemon;

	@FXML
	private Button ataque1;

	@FXML
	private Button ataque2;

	@FXML
	private Button ataque3;

	@FXML
	private Button ataque4;

	@FXML
	private Label vidalabel;

	@FXML
	private Label vida2label;

	@FXML
	private Slider vida1slider;

	@FXML
	private Slider vida2slider;

	@FXML
	private BorderPane view;

	public JuegoController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Juego.fxml"));
		loader.setController(this);
		loader.load();
	}

	public void initialize(URL location, ResourceBundle resources) {
		iniciarCombate();
		ataque1.textProperty().bind(PokemonSeleccionado.get().getAtaques().get(0).nombreProperty());
		ataque2.textProperty().bind(PokemonSeleccionado.get().getAtaques().get(1).nombreProperty());
		ataque3.textProperty().bind(PokemonSeleccionado.get().getAtaques().get(2).nombreProperty());
		ataque4.textProperty().bind(PokemonSeleccionado.get().getAtaques().get(3).nombreProperty());
		ganador.addListener((o, ov, nv) -> onhayganador(o, ov, nv));
		PokemonSeleccionado.addListener((o, ov, nv) -> onpokemonchanged(o, ov, nv));


	}


	private void onpokemonchanged(ObservableValue<? extends Pokemon> o, Pokemon ov, Pokemon nv) {
		if(ov!=null) {
			vida.unbind();
			ataque1.textProperty().unbind();
			ataque2.textProperty().unbind();
			ataque3.textProperty().unbind();
			ataque4.textProperty().unbind();
		}
		if(nv!=null) {
			vida.bind(nv.vidaProperty());
			ataque1.textProperty().bind(nv.getAtaques().get(0).nombreProperty());
			ataque2.textProperty().bind(nv.getAtaques().get(1).nombreProperty());
			ataque3.textProperty().bind(nv.getAtaques().get(2).nombreProperty());
			ataque4.textProperty().bind(nv.getAtaques().get(3).nombreProperty());
		}
		pokemon1.setImage(PokemonSeleccionado.get().getDelante());
	}

	private void onhayganador(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv) {
		if (ganador.get() == true)
			if (App.confirm("Informacion de combate", "El combate ha finalizado", "¿Desea Continuar?")) {
				iniciarCombate();
				ganador.set(false);
			} else {
				System.exit(0);
			}

	}

	@FXML
	void onataque1(ActionEvent event) {
		ataqueCombate(PokemonSeleccionado.get().getAtaques().get(0), Pokemoncpu.get());
	}

	@FXML
	void onataque2(ActionEvent event) {
		ataqueCombate(PokemonSeleccionado.get().getAtaques().get(1), Pokemoncpu.get());
	}

	@FXML
	void onataque3(ActionEvent event) {
		ataqueCombate(PokemonSeleccionado.get().getAtaques().get(2), Pokemoncpu.get());

	}

	@FXML
	void onataque4(ActionEvent event) {
		ataqueCombate(PokemonSeleccionado.get().getAtaques().get(3), Pokemoncpu.get());

	}

	private void ataqueCombate(Ataque ataque, Pokemon pk) {
		Combate.ataque(ataque, pk);
		if (pk.getVida() <= 0) {
			ganador.set(true);
		}
		Combate.ataquecpu(PokemonSeleccionado.get());
		if (PokemonSeleccionado.get().getVida() <= 0) {
			ganador.set(true);
		}
	}

	private void iniciarCombate() {
		combate.set(new Combate());
		entrenador1.bind(combate.get().entrenador1Property());
		entrenador2.bind(combate.get().entrenador2Property());
		comboPokemon.itemsProperty().bind(entrenador1);
		comboPokemon.getSelectionModel().selectFirst();
		PokemonSeleccionado.bind(comboPokemon.getSelectionModel().selectedItemProperty());
		Pokemoncpu.set(entrenador2.get(0));
		vida.bind(PokemonSeleccionado.get().vidaProperty());
		vida1slider.setMax(vida.get());
		vida2slider.setMax(Pokemoncpu.get().getVida());
		vidalabel.textProperty().bindBidirectional(vida,
				new NumberStringConverter());
		vida2label.textProperty().bindBidirectional(Pokemoncpu.get().vidaProperty(), new NumberStringConverter());
		vida1slider.valueProperty().bind(vida);
		vida2slider.valueProperty().bind(Pokemoncpu.get().vidaProperty());
		pokemon1.setImage(PokemonSeleccionado.get().getDelante());
		pokemon2.setImage(Pokemoncpu.get().getDelante());

	}

	public BorderPane getView() {
		return view;
	}

}
