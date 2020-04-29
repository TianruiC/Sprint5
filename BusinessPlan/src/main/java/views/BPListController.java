package views;

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

public class BPListController {

	MainViewModel model;  
	ObservableList<BusinessPlan> BPList= FXCollections.observableArrayList();
	
    public void setModel(MainViewModel newModel)
    {
      model=newModel;
      ArrayList<BusinessPlan> Dulplicate=model.client.askForAllBP();
		for (int i=0; i<Dulplicate.size();i++){
			BPList.add(Dulplicate.get(i));
		}
      BPListView.setItems(BPList);
      
    }
    
    @FXML
    private Button clone;

    @FXML
    private Button copy;

    @FXML
    private ListView<BusinessPlan> BPListView;
    
    
    @FXML
    void onClickClone(ActionEvent event) {
    	BusinessPlan clickedBP=BPListView.getSelectionModel().getSelectedItem();
    	System.out.println("Clone Clicked on " + clickedBP);
    	if(clickedBP!=null) {
    		try {
              Stage stage = new Stage();
              FXMLLoader loader = new FXMLLoader();
              loader.setLocation(Main.class.getResource("../views/CloneWindow.fxml")); 
              stage.setTitle("Clone Window");
              BorderPane view = loader.load();
              CloneWindowController cont = loader.getController();
//              cont.setModel(model);
              Scene s = new Scene(view);
              stage.setScene(s);
              stage.show();
              
          }
          catch (IOException e) {
              e.printStackTrace();
          }
    	}
    }
    

    @FXML
    void onClickCopy(ActionEvent event) {
    	BusinessPlan clickedBP=BPListView.getSelectionModel().getSelectedItem();
    	System.out.println("Copy Clicked on " + clickedBP);
    }
}
