package views;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.Main;
import models.ViewTransitionModelInterface;

public class MainController {
	
	ViewTransitionModelInterface model;

	  
    public void setModel(ViewTransitionModelInterface newModel)
    {
      model=newModel;
    }
    
    @FXML
	private Button logout;
	
	@FXML
	private Button personalInfo;
	
	@FXML
	private Button savedBPs;
	
	@FXML
	private Button createBP;
	
    
    @FXML
    void onClickCreateNewBP(ActionEvent event) {
    	model.showEmptyBPView();
    }

    @FXML
    void onClickPersonalInfo(ActionEvent event) {
    	model.showPersonInfo();
    }

    @FXML
    void onClickSavedBPs(ActionEvent event) {
    	model.showBPlistView();
    }   
    @FXML
    void onClickLogout(ActionEvent event) {
    	model.logout(); 
    	Stage stage = (Stage) logout.getScene().getWindow();
		stage.close();
    	model.showLoginPage(this);
		 
    }

}
