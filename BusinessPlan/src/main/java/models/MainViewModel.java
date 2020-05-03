package models;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import views.BPMainController;
import views.BPTreeController;
import views.CloneWindowController;

public class MainViewModel {

	public MyRemoteClient client;
	public BorderPane mainview;

	public MainViewModel(MyRemoteClient wowclient, BorderPane view) {
		this.client=wowclient;
		this.mainview=view;
	}

	public void showCloneView() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainViewModel.class
				.getResource("../views/CloneWindow.fxml"));
		try {
			Pane view = loader.load();
			mainview.setCenter(view);
			CloneWindowController cont = loader.getController();
			cont.setModel(this);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//switch to BPMainView window
	public void showCopyView() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainViewModel.class
				.getResource("../views/BPMainView.fxml"));
		try {
			BorderPane view = loader.load();
			System.out.println(client.getCurrentBP());
			
			BPMainModel model = new BPMainModel(client, view);
			BPMainController cont = loader.getController();
			//BPViewTransitionModel bptran = new BPViewTransitionModel(view, model);
			cont.setModel(model);
			
			//bptran.showBPtree(model);
			
			cont.setDisabled(true);			
			Stage stage = new Stage();
			Scene s = new Scene(view);
			stage.setScene(s);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
