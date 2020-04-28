package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import models.BusinessPlan;
import models.MainViewModel;

public class BPListController {

	MainViewModel model;  
	
    public void setModel(MainViewModel newModel)
    {
      model=newModel;
      BPListView.setItems(model.getBPlist());
      
    }
    
    @FXML
    private Button clone;

    @FXML
    private Button copy;

    @FXML
    private ListView<BusinessPlan> BPListView;

    @FXML
    void onClickClone(ActionEvent event) {

    }

    @FXML
    void onClickCopy(ActionEvent event) {

    }
}
