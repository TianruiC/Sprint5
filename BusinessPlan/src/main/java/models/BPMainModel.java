package models;

import java.io.IOException;
<<<<<<< HEAD
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
=======

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import views.BPMainController;
>>>>>>> eb4053adcdcc167a5385cce87cdb303e2bc06ca3
import views.MainController;

public class BPMainModel {

	public MyRemoteClient client;
	public BorderPane mainview;
	public ViewTransitionModelInterface model;
	public MainViewModel modelV;


	public BPMainModel(MyRemoteClient wowclient,BorderPane view) {
		this.client=wowclient;
		this.mainview=view;
	}

	//switch to MainView window
	public void showMainView() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(BPMainModel.class
				.getResource("../views/MainPageShell.fxml"));
		try {
			BorderPane view = loader.load();
			MainController cont = loader.getController();
			modelV = new MainViewModel(client,view);
			model = new MainViewTransitionModel(view,modelV);
			cont.setModel(model);
			model.showPersonInfo();
			Stage stage = new Stage();
			Scene s = new Scene(view);
			stage.setScene(s);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
<<<<<<< HEAD
	}		
=======
	}
	
	
	
		
>>>>>>> eb4053adcdcc167a5385cce87cdb303e2bc06ca3
}
