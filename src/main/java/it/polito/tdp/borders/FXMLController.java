package it.polito.tdp.borders;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	Model model;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtAnno;

    @FXML
    private ComboBox<Country> idCombo;

    @FXML
    private Button btnVicini;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	int anno;
    	try {
    		anno = Integer.parseInt(txtAnno.getText());    		
    	} catch (Throwable t){ //qualunque tipo di errore
    		txtResult.appendText("Devi inserire un numero!\n");
    		return;
    	}
    	
    	if(txtAnno.getText().equals("")) {
    		txtResult.appendText("Devi inserire un anno");
    		return;
    	}
    	if((anno<1816) || (anno>2016)) {
			txtResult.appendText("Inserisci un anno compreso tra 1816-2006");
			return;}
    	
    	this.model.creaGrafo(anno);
    	txtResult.appendText("Grafo creato!\n");
    	txtResult.appendText("# VERTICI: " + this.model.vertexNumber());
    	txtResult.appendText("# ARCHI: " + this.model.edgeNumber()+"\n");
    	
    	//idCombo.getItems().addAll(this.model.getCountries());
    	idCombo.getItems().addAll(model.getVertex());
    	
    	for(String s : model.getCountry()) {
    		txtResult.appendText(s);
    	}
    	/*for(Country c : model.getVertex()) {
		txtResult.appendText(c+" - numero stati confinanti: "+model.numConfini(c)+"\n");
	}*/
    }

    @FXML
    void doTrovaVicini(ActionEvent event) {
    	
    	Country c = idCombo.getValue();
    
    	if(c==null) {
    		txtResult.appendText("Seleziona uno stato!\n");
    		return;
    	}
    	
    	for(Country ci : model.trovaVicini(c)) {
    			txtResult.appendText(ci+"\n");
    	}
    	//List<Country> visita = model.visitaGrafo(c);
    	//txtResult.appendText(visita.toString());
    }

    @FXML
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert idCombo != null : "fx:id=\"idCombo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnVicini != null : "fx:id=\"btnVicini\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    public void setModel(Model m) {
    	this.model=m;
    }
}
