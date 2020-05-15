package models;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import views.BPEditingController;
import views.BPMainController;
import views.BPTreeController;
import views.CommentController;
import views.CompareDetailedController;
import views.CompareViewController;
import views.DeleteConfirmationController;
import views.LeaveController;
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
			stage.setTitle("BPViewer");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//edit specific section
	public void showEditView(Section cur) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(BPMainModel.class
				.getResource("../views/EditingView.fxml"));
		try {
			BorderPane view = loader.load();
			BPEditingController cont = loader.getController();
			mainview.setCenter(view);
			cont.setModel(this,cur);


		}	
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//show BP content tree
	public void showTreeView() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(BPMainModel.class
				.getResource("../views/BPTreeView.fxml"));
		try {
			Pane view = loader.load();
			BPTreeController cont = loader.getController();
			mainview.setCenter(view);
			cont.setModel(this);
		}	
		catch (IOException e) {
			e.printStackTrace();
		
		}
	
	}
	
	//show leave confirmation window
	public void showLeaveConfirm(Stage stageP) {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainViewModel.class
				.getResource("../views/LeaveView.fxml"));
		try {
			BorderPane view = loader.load();

			BPMainModel model = new BPMainModel(client, view);
			LeaveController cont = loader.getController();

			cont.setModel(model,stageP);
		
			Stage stage = new Stage();
			Scene s = new Scene(view);
			stage.setScene(s);
			stage.setTitle("Leave Confirmtion");
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();

		}	
	}
	public void showDeleteConfirm(BPMainController controller,Section clickedSection) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainViewModel.class
				.getResource("../views/ConfirmationView.fxml"));
		try {
			BorderPane view = loader.load();

			BPMainModel model = new BPMainModel(client, view);
			DeleteConfirmationController cont = loader.getController();

			cont.setModel(model,controller,clickedSection);
		
			Stage stage = new Stage();
			Scene s = new Scene(view);
			stage.setScene(s);
			stage.setTitle("Delete Confirmation");
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();

		}	
	}
	
	//task1
	public void showCompareBP(Stage stageP) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(BPMainModel.class
				.getResource("../views/CompareView.fxml"));
		try {
			BorderPane view = loader.load();
			CompareViewController cont = loader.getController();

			cont.setModel(this,stageP);
		
			Stage stage = new Stage();
			Scene s = new Scene(view);
			stage.setScene(s);
			stage.setTitle("Stored BP");
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();

		}
	}
	
	public void showCompareDetailed(BusinessPlan clickedBP) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(BPMainModel.class
				.getResource("../views/CompareDetailedView.fxml"));
		try {
			Pane view = loader.load();
			CompareDetailedController cont = loader.getController();
			mainview.setCenter(view);
			cont.setModel(this,clickedBP);
		}	
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	//task2
	public void showComment(Section cur) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(BPMainModel.class
				.getResource("../views/CommentView.fxml"));
		try {
			BorderPane view = loader.load();
			CommentController cont = loader.getController();
			mainview.setCenter(view);
			cont.setModel(this, cur);
			
		} catch (IOException e) {
			e.printStackTrace();

		}
	}
	
	
}
