package views;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.MainViewModel;


public class PersonalInfoController {

	MainViewModel model;
	
	 public void setModel(MainViewModel newModel)
	 {
	    model = newModel;
	 }
	 @FXML
	 private Label username;

	 @FXML
	 private Label department;

	 @FXML
	 private Label isAdmin;

}