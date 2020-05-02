package models;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import views.BPTreeController;

public class BPViewTransitionModel{
	
	BorderPane mainview;
	public BPMainModel model;
	
	public BPViewTransitionModel(BorderPane view,BPMainModel newModel)
	  {
	    mainview = view;
	    model = newModel;
	  }
	
	public void showBPtree(BPMainModel thismodel) {
		
		FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(BPViewTransitionModel.class
	        .getResource("../views/BPTreeView.fxml"));
	    try {
	      Pane view = loader.load();
	      mainview.setCenter(view);
	      BPTreeController cont = loader.getController();
	      cont.setModel(thismodel);
	      
	      
	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	}
}
