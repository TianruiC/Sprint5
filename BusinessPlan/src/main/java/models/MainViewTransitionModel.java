package models;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import views.BPListController;
import views.NewBPController;
import views.PersonalInfoController;

public class MainViewTransitionModel implements ViewTransitionModelInterface {

	BorderPane mainview;
	MainViewModel model;
	
	public MainViewTransitionModel(BorderPane view,MainViewModel newModel)
	  {
	    mainview = view;
	    model = newModel;
	  }

	@Override
	public void showPersonInfo() {
		
		FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(MainViewTransitionModel.class
	        .getResource("../views/PersonalInfoView.fxml"));
	    try {
	      Pane view = loader.load();
	      mainview.setCenter(view);
	      PersonalInfoController cont = loader.getController();
	      cont.setModel(model);
	      
	      
	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
		
	}

	@Override
	public void showBPlistView() {
		FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(MainViewTransitionModel.class
	        .getResource("../views/BPListView.fxml"));
	    try {
	      Node view = loader.load();
	      mainview.setCenter(view);
	      BPListController cont = loader.getController();
	      cont.setModel(model);
	      
	      
	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
		
	}

	@Override
	public void showEmptyBPView() {
		FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(MainViewTransitionModel.class
	        .getResource("../views/EmptyBPView.fxml"));
	    try {
	      Pane view = loader.load();
	      mainview.setCenter(view);
	      NewBPController cont = loader.getController();
	      cont.setModel(model);
	      
	      
	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
		
	}

	@Override
	public void logout() {
		model.client.logOut();
		
	}
	
}
