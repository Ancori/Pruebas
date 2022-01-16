package dad.pruebas.pokemon;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

public class Main {

	public static void main(String[] args) {
		ListProperty<Pokemon>listPokemon=new SimpleListProperty<>(FXCollections.observableArrayList());
		try {
			SAXBuilder builder = new SAXBuilder();
			File xml = new File("src/main/resources/Pokemon.xml");
			Document document = builder.build(xml);
			Element root = document.getRootElement();
			List<Element> list = root.getChildren("Pokemon");
			for (int i = 0; i < list.size(); i++) {
				Element Pokemon = list.get(i);
				Element ataque = Pokemon.getChild("Ataques");
				List<Element> ataques = ataque.getChildren();
				ArrayList<Ataque>listaAtaques=new ArrayList<Ataque>();
				for (int j = 0; j < ataques.size(); j++) {
					int poder=Integer.valueOf(ataques.get(j).getAttributeValue("poder"));
					int precision=Integer.valueOf(ataques.get(j).getAttributeValue("precision"));
					listaAtaques.add(j, new Ataque(ataques.get(j).getText(),poder,precision,ataques.get(j).getAttributeValue("tipo")));
				}
			    int nivel=Integer.valueOf(list.get(i).getAttributeValue("nivel"));
			   Pokemon pok=new Pokemon(Pokemon.getAttributeValue("nombre"), Pokemon.getAttributeValue("tipo"), nivel, listaAtaques);
			   listPokemon.add(i, pok);
			}
			System.out.println(listPokemon);
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
