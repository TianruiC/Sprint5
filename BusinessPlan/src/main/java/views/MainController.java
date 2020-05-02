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
    	try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(Main.class.getResource("../views/LeaveView.fxml"));
            stage.setTitle("U Sure?");
            LeaveController con=loader.getController();
            // con.setContrl(this);
            BorderPane view = loader.load();
            Scene s = new Scene(view);
    		stage.setScene(s);
    		stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
//    	model.logout();
//    	this.setDisabled(true);
//    	model.showLoginPage(this);
    }

}
