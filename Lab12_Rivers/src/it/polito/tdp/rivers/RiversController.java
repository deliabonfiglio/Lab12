/**
 * Sample Skeleton for 'Rivers.fxml' Controller Class
 */

package it.polito.tdp.rivers;

import java.net.URL;
import java.util.ResourceBundle;
import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.River;
import it.polito.tdp.rivers.simulazione.Simulation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RiversController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxRiver"
    private ComboBox<River> boxRiver; // Value injected by FXMLLoader

    @FXML // fx:id="txtStartDate"
    private TextField txtStartDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtEndDate"
    private TextField txtEndDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumMeasurements"
    private TextField txtNumMeasurements; // Value injected by FXMLLoader

    @FXML // fx:id="txtFMed"
    private TextField txtFMed; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    private Model model;
    private Simulation sim;

    @FXML
    void doSearch(ActionEvent event) {
    	River river = boxRiver.getValue();
    	
    	txtStartDate.setText(model.getStartDate(river).getDay().toString());
    	txtEndDate.setText(model.getEndDate(river).getDay().toString());
    	txtNumMeasurements.setText(model.getNumMeasurements(river)+"");
    	txtFMed.setText(model.getFMed(river)+"");
    }

    @FXML
    void doSimulazione(ActionEvent event) {
    	int kvalue=0;
		
    	if(txtK.getText()!="" && boxRiver.getValue()!=null){
    		River r = boxRiver.getValue();
    		kvalue = Integer.parseInt(txtK.getText());
			if(kvalue <=0 ){
				txtResult.setText("Inserire solo valori di k maggiori di 0");
			} else{
				///do simulazione
				model.simula(kvalue, Float.parseFloat(txtFMed.getText()), r);
				txtResult.clear();
				txtResult.appendText("Risultati simulazione: \n");
				txtResult.appendText("I giorni in cui non si e' soddisfatta l'erogazione minima sono: "+model.getStatGGNotSatisfied()+"\n");
				txtResult.appendText("La C media e' stata: "+model.getStatCmedio()+"\n");
			}
    	} else{
    		txtResult.setText("Scegliere un fiume e inserire un fattore di scala");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Rivers.fxml'.";

    }

	public void setModel(Model model) {
			this.model = model;
			this.boxRiver.getItems().addAll(model.getRivers());
		}
}
