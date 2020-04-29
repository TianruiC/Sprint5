package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import models.ViewTransitionModelInterface;

public class MainController {
	
	ViewTransitionModelInterface model;

	  
    public void setModel(ViewTransitionModelInterface newModel)
    {
      model=newModel;
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
    }

}
