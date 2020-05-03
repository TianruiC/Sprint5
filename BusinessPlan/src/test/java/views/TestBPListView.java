package views;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.Main;
import models.BusinessPlan;
import models.MainViewModel;
import models.MainViewTransitionModel;
import models.MyRemote;
import models.MyRemoteClient;
import models.MyRemoteImpl;
import models.Person;
import models.VMOSA;

@ExtendWith(ApplicationExtension.class)
public class TestBPListView {
	
	@Start //Before
	private void start(Stage stage)
	{
		//initialize
		BusinessPlan BP = new VMOSA();
		BP.name="Giao";
		BP.year = 2020;
		BP.department ="CS";
		BP.isEditable=false;
		BP.addSection(BP.root);
		BP.root.content=("this is the vision");
		BP.root.children.get(0).content=("this is the misson");
		BP.addSection(BP.root.children.get(0));
		

		BusinessPlan BP2 = new VMOSA();
		BP2.name="Hoaho";
		BP2.year = 2009;
		BP2.department ="CS";
		BP2.isEditable=true;
		BP2.addSection(BP2.root);

		ArrayList <BusinessPlan> storedBP=new ArrayList<BusinessPlan>();
		storedBP.add(BP);
		storedBP.add(BP2);

		//initialize storedUser
		Person wynnie=new Person("wynnie","wynnie","CS", true);
		Person terry=new Person("terry","terry","CS", false);

		ArrayList <Person> storedUser=new ArrayList<Person>();
		storedUser.add(wynnie);
		storedUser.add(terry);
		
		try {
			//set server & client
			Registry registry = LocateRegistry.createRegistry(1399);
			MyRemoteImpl server = new MyRemoteImpl();		
			server.setStoredBP(storedBP);
			server.setStoredUser(storedUser);
			MyRemote stub = (MyRemote) UnicastRemoteObject.exportObject(server, 0);
			registry.rebind("MyRemote", stub);
			MyRemote serverInterface=(MyRemote) registry.lookup("MyRemote");
			MyRemoteClient client=new MyRemoteClient(serverInterface);

			//set initial stage and view
			FXMLLoader loader0 = new FXMLLoader();
			loader0.setLocation(Main.class.getResource("../views/MainPageShell.fxml")); 
			
			BorderPane viewM = loader0.load();
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../views/login.fxml")); 
			
			BorderPane view = loader.load();

			MainViewModel model = new MainViewModel(client,view);

			LoginController cont = loader.getController();
			MainController contM = loader0.getController();
			
			MainViewTransitionModel vm =new MainViewTransitionModel(viewM,model); 
			contM.setModel(vm);
			
		    cont.setModel(model);
		    cont.setParent(viewM, contM);

			Scene s = new Scene(view);
			stage.setScene(s);
			stage.show();
			
		}catch(Exception e) {
			e.printStackTrace();	//print fail
			fail("Fail");
		}
	}
	
	//general input text method
	private void enterText(FxRobot robot, String text, String target)
	{
		robot.clickOn(target);
		robot.write(text);
	}
	//comboBox
	private void chooseType(FxRobot robot, String target, String item) 
	{
		robot.clickOn(target);
		robot.clickOn(item);
	}
	//int to string
	private void enterYearText(FxRobot robot, int text, String target)
	{
		robot.clickOn(target);
		robot.write(Integer.toString(text));
	}

	//step 1: login
	private void login(FxRobot robot, String username, String password)
	{
		enterText(robot, username, "#usernameInput");
		enterText(robot, password, "#passwordInput");
		robot.clickOn("#login");
	}	

	//step 2: create an new BP
	private void newBP(FxRobot robot, String BPtype, String BPname, int BPyear)
	{
		robot.clickOn("#newBP");
		chooseType(robot, "#BPtypeBox", BPtype);
		enterText(robot, BPname, "#NameTextField");
		enterYearText(robot, BPyear, "#YearTextField");
		robot.clickOn("#createButton");
	}
	
	//step 3: clone an new BP
	private void cloneBP(FxRobot robot, String BPname, int BPyear)
	{
		robot.clickOn("#cloneOnlist");
		enterText(robot, BPname, "#cloneName");
		enterYearText(robot, BPyear, "#cloneYear");
		robot.clickOn("#cloneBP");
	}
	

	@Test
	public void testButton(FxRobot robot) {
		try {
			Thread.sleep(1000);
			
			//actions
			login(robot,"wynnie","wynnie");
			robot.clickOn("#BPlist");
			
			newBP(robot,"VMOSA","newBP",1999);
			//robot.clickOn("#BPlist");

			//cloneBP(robot,"cloneBP",2000);
			//robot.clickOn("#BPlist");
			
			
			Thread.sleep(1000);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
