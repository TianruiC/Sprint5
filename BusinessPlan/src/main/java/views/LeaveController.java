package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LeaveController {
	
	MainController con;  
	public void setContrl(MainController  controllerOfController)
    {
		con=controllerOfController;	            
    }
	@FXML
    void onClickNo(ActionEvent event) {
		
    }

    @FXML
    void onClickYes(ActionEvent event) {
    	con.model.logout();
    	con.setDisabled(true);
    	con.model.showLoginPage(con);
    }
	
	
}
