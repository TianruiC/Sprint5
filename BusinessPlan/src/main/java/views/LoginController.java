package views;

import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import main.Main;
import models.BusinessPlan;
import models.MainViewModel;
import models.MainViewTransitionModel;

public class LoginController {

	MainViewModel model;
	//Need access to the MainController to re-enable the buttons after login
	MainController parent;
	
	
    public void setModel(MainViewModel newModel)
    {
      model=newModel;
    }
    
    public void setParent(MainController pt)
    {
    	parent = pt;
    }
    
    
    @FXML
    private TextField usernameField;
    
    @FXML
    private TextField passwordField;
    
    @FXML 
    private TextField serverField;
    
    @FXML
    private Button loginButton;
    
    @FXML
    private Text error;
        
    @FXML
    void onClickLogin(ActionEvent event)
    {    	
    	String username = usernameField.getText();
    	String password = passwordField.getText();
    	model.client.askForLogin(username, password);
    	if(model.client.getLoginPerson()!=null)
    	{
        	parent.setDisabled(false);
        	error.setOpacity(0);
        	parent.model.showPersonInfo();
    	}
    	else
    	{
    		error.setOpacity(1);
    	}
    }

 
}
