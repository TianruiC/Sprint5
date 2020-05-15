package views;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import models.BPMainModel;
import models.BusinessPlan;

public class CompareViewController {
	
	BPMainModel model;  
	
	//previous working BPMain window
	Stage stageP;
	
	public ObservableList<BusinessPlan> BPList= FXCollections.observableArrayList();
	
	public void setModel(BPMainModel  newModel, Stage stage)
    {
		model = newModel;	
		stageP = stage;
		ArrayList<BusinessPlan> Dulplicate=model.client.askForAllBP();
		for (int i=0; i<Dulplicate.size();i++){
			BPList.add(Dulplicate.get(i));
		}

		BPListView.setItems(BPList);
    }

	@FXML
	private Button compare;

	@FXML
	private Button cancel;
	
	@FXML
    private ListView<BusinessPlan> BPListView;
	@FXML
	void onClickCancel(ActionEvent event) {
		Stage stage = (Stage) cancel.getScene().getWindow();
		stage.close();
	}

	@FXML
	void onClickCompare(ActionEvent event) {
		BusinessPlan clickedBP=BPListView.getSelectionModel().getSelectedItem();
    	
    	if(clickedBP!=null) {
    		Stage stage = (Stage) cancel.getScene().getWindow();
    		stage.close();
    		model.showCompareDetailed(clickedBP);
    		
    	}
	}	
}
