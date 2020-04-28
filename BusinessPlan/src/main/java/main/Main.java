package main;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.BusinessPlan;
import models.MainViewModel;
import models.MainViewTransitionModel;
import models.MyRemote;
import models.MyRemoteClient;
import models.MyRemoteImpl;
import models.Person;
import models.VMOSA;
import views.MainController;

public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		
		//initialize
		BusinessPlan BP = new VMOSA();
		BP.name="Giao";
		BP.year = 2020;
		BP.department ="CS";
		BP.isEditable=false;
		
		BusinessPlan BP2 = new VMOSA();
		BP2.name="Hoaho";
		BP2.year = 2009;
		BP2.department ="CS";
		BP2.isEditable=true;
		
		ArrayList <BusinessPlan> storedBP=new ArrayList<BusinessPlan>();
		storedBP.add(BP);
		storedBP.add(BP2);
		
		//initialize storedUser
		Person wynnie=new Person("wynnie","wynnie","CS", true);
		
		ArrayList <Person> storedUser=new ArrayList<Person>();
		storedUser.add(wynnie);
		
		//
		Registry registry = LocateRegistry.createRegistry(2021);
		MyRemoteImpl server = new MyRemoteImpl();
		
		server.setStoredBP(storedBP);
		server.setStoredUser(storedUser);
		
    	MyRemote stub = (MyRemote) UnicastRemoteObject.exportObject(server, 0);
		registry.rebind("MyRemote", stub);
		MyRemote serverInterface=(MyRemote) registry.lookup("MyRemote");
		MyRemoteClient client=new MyRemoteClient(serverInterface);
		
		//suppose we logined as wynnie
		client.askForLogin("wynnie","wynnie");
		
		MainViewModel model = new MainViewModel(client);
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../views/MainPageShell.fxml")); 
		BorderPane view = loader.load();
		MainController cont = loader.getController();
		MainViewTransitionModel vm =new MainViewTransitionModel(view,model); 
	    cont.setModel(vm);
	    vm.showPersonInfo();
	    
		
		Scene s = new Scene(view);
		stage.setScene(s);
		stage.show();
		
	}
	public static void main (String [] args) {
		launch(args);
		 
	}
}