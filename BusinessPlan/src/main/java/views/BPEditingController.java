package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import models.BPMainModel;
import models.Section;

public class BPEditingController {

	BPMainModel model;

	public void setModel(BPMainModel newmodel, Section cur) {
		model=newmodel;
		ContentTextArea.textProperty().set(cur.content);
		
	}
	
    @FXML
    private TextArea ContentTextArea;

    @FXML
    void onClickCancel(ActionEvent event) {
    	
    }

    @FXML
    void onClickSave(ActionEvent event) {

    }
    

}
