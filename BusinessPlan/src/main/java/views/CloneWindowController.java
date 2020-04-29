package views;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.MainViewModel;

public class CloneWindowController {

	MainViewModel model;  
	 public void setModel(MainViewModel newModel)
	    {
	      model=newModel;
	      currentBPName.textProperty().set(model.client.getCurrentBP().name);	      
	    }
	 
    @FXML
    private Label currentBPName;

    @FXML
    private TextField NewBPName;

    @FXML
    private TextField year;

    @FXML
    void onClickCancel(ActionEvent event) {
    	NewBPName.textProperty().set("");
    	year.textProperty().set("");
    }

    @FXML
    void onClickClone(ActionEvent event) {
    	System.out.println("OH You clicked clone~");
    }

}
