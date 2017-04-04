package it.polito.tdp.lab04.controller;


import java.util.*;

import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Corso;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SegreteriaStudentiController {
	private Model model;
   

    @FXML // fx:id="comboCorso"
    private ComboBox<String> comboCorso; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaIscrittiCorso"
    private Button btnCercaIscrittiCorso; // Value injected by FXMLLoader

    @FXML // fx:id="txtMatricola"
    private TextField txtMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaNome"
    private ImageView btnCercaNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaCorsi"
    private Button btnCercaCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="btnIscrivi"
    private Button btnIscrivi; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	if(!txtMatricola.getText().matches("[0-9]*")){
    		
			txtResult.setText("Matricola non valida");
			return;
		}
    	if (txtMatricola.getText().isEmpty()){
			txtResult.setText("Inserisci una matricola");
			return;
		}
    	int matricola = Integer.parseInt(txtMatricola.getText());
    	if(model.cercaNomeStudente(matricola)==null){
    		txtResult.appendText("Studente inesistente");
    		return;
    	}
    	else{
    		if(comboCorso.getValue().compareTo("Seleziona corso...")==0){
    			String result = model.cercaCorsiStudente(matricola);
    			if(result==null){
    				txtResult.appendText("Studente non iscritto a corsi");
    			}
    			else{
    				txtResult.appendText(result);
    			}
    	}
    		else{
    			boolean result = model.controllaCorsoStudente(comboCorso.getValue(), matricola);
    			if(result)
    				txtResult.appendText("\nStudente già iscritto al corso");
    			else
    				txtResult.appendText("\nStudente non ancora iscritto al corso");
    		}
    	}
    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {    	
    	String corso = null;
    	corso=comboCorso.getValue();
    	String risultato = model.studentiIscritti(corso);
    	if(risultato==null){
    		txtResult.appendText("Nessuno studente iscritto al corso "+ corso);
    	}
    	else{
    		txtResult.appendText(risultato);
    	}
    }

    @FXML
    void doCercaNome(MouseEvent event) {
    	
    	
    	if(!txtMatricola.getText().matches("[0-9]*")){
    		
			txtResult.setText("Matricola non valida");
			return;
		}
    	if (txtMatricola.getText().isEmpty()){
			txtResult.setText("Inserisci una matricola");
			return;
		}
		int matricola = Integer.parseInt(txtMatricola.getText());
		
		if(model.cercaNomeStudente(matricola) == null){
			txtResult.setText("Matricola non trovata!");
			return;
		}
		txtNome.setText(model.cercaNomeStudente(matricola));
		txtCognome.setText(model.cercaCognomeStudente(matricola));

    	
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	if(!txtMatricola.getText().matches("[0-9]*")){
    		
			txtResult.setText("Matricola non valida");
			return;
		}
    	if (txtMatricola.getText().isEmpty()){
			txtResult.setText("Inserisci una matricola");
			return;
		}
    	if(model.iscrivi(Integer.parseInt(txtMatricola.getText()),comboCorso.getValue())==0){
    		txtResult.setText("Studente iscritto al corso");
    	}
    	else {
    		txtResult.setText("Errore: non è possibile iscrivere lo studente");
    	}
    	
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtResult.clear();
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	comboCorso.setValue("Seleziona corso...");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert comboCorso != null : "fx:id=\"comboCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaNome != null : "fx:id=\"btnCercaNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
       

    }

	public void setModel(Model model) {
		// TODO Auto-generated method stub
		this.model=model;
		comboCorso.getItems().addAll(model.getListaCorsi());
		if (comboCorso.getItems().size()>0){
			comboCorso.setValue("Seleziona corso...");
		}
	}
}

