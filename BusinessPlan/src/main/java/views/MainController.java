package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
	
    //Don't want the user clicking things if they are not logged in
    public void setDisabled(boolean val)
    {
    	logout.setDisable(val);
    	savedBPs.setDisable(val);
    	createBP.setDisable(val);
    	personalInfo.setDisable(val);
    }
    
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
    	//System.out.print("about to bo");
    	model.showBPlistView();
    }   
    @FXML
    void onClickLogout(ActionEvent event) {
    	//System.out.print("about to logout");
    	model.logout();
    	this.setDisabled(true);
    	model.showLoginPage(this);
    }

}
