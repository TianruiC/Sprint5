package views;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.MainViewModel;
import models.ViewTransitionModelInterface;

public class CloneWindowController {

	MainViewModel model;  

	
	 public void setModel(MainViewModel newModel)
	    {
	      model=newModel;	      
	      currentBPName.textProperty().set(newModel.client.getCurrentBP().toString());	      
	    }
	 
    @FXML
    private Label currentBPName;

    @FXML
    private TextField NewBPName;

    @FXML
    private TextField year;
    
    @FXML
    private Label Message;
    @FXML
    void onClickCancel(ActionEvent event) {
    	NewBPName.textProperty().set("");
    	year.textProperty().set("");

    }

    @FXML
    //还需改进 小问题 记得回来看看
    
    void onClickClone(ActionEvent event) {
    	System.out.println("OH You clicked clone~");
    	String CloneBPName = NewBPName.getText();
    	String CloneBPyear = year.getText();
    	int CloneBPYearInt=-1;
    	
    	try {
    		CloneBPYearInt = Integer.parseInt(year.getText());
		}
		catch(NumberFormatException e) {
			year.textProperty().set("Please type a number! ");
		}
    	
    	if(CloneBPName!=null&&CloneBPyear!=null&&CloneBPYearInt!=-1) {
    		if(Integer.parseInt(year.getText())<1819) {
        		Message.textProperty().set("Please use appropriate year attribute.");
        		Message.setOpacity(1);	
        	}
        	else {
        		if(Integer.parseInt(year.getText())!=model.client.getCurrentBP().year) {
            		model.client.getCurrentBP().name=NewBPName.getText();
                	model.client.getCurrentBP().year=Integer.parseInt(year.getText());
                	model.client.uploadBP();
                	Message.setOpacity(1);
            	}
            	else {
            		Message.textProperty().set("Please copy with a different year");
            		Message.setOpacity(1);			
            	}
        	}
    	}
    	
    	
    	
    }

}
