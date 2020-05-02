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
import javafx.stage.Stage;
import models.BusinessPlan;
import models.MainViewModel;
import models.MainViewTransitionModel;
import models.MyRemote;
import models.MyRemoteClient;
import models.MyRemoteImpl;
import models.Person;
import models.VMOSA;

@ExtendWith(ApplicationExtension.class)
public class TestNewBPView {
	
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

		ArrayList <Person> storedUser=new ArrayList<Person>();
		storedUser.add(wynnie);
		try {

			Registry registry = LocateRegistry.createRegistry(1099);
			MyRemoteImpl server = new MyRemoteImpl();		
			server.setStoredBP(storedBP);
			server.setStoredUser(storedUser);
			MyRemote stub = (MyRemote) UnicastRemoteObject.exportObject(server, 0);
			registry.rebind("MyRemote", stub);
			MyRemote serverInterface=(MyRemote) registry.lookup("MyRemote");
			MyRemoteClient client=new MyRemoteClient(serverInterface);

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainViewTransitionModel.class
					.getResource("../views/EmptyBPView.fxml"));
			try {
				Scene s = new Scene(loader.load());
				NewBPController cont = loader.getController();
				cont.setModel(new MainViewModel(client));
				
				stage.setScene(s);
			    stage.show();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}catch(Exception e) {
			e.printStackTrace();	//print fail
			fail("Fail");
		}
	}
	private void enterText(FxRobot robot, String text, String target)
	  {
	    robot.clickOn(target);
	    robot.write(text);
	  }
	@Test
	public void testButton(FxRobot robot) {
//		try {
//			Thread.sleep(1000);
//			System.out.println("我是涵涵");
//			robot.clickOn("#createButton");
//			System.out.println("你猜我点了没");
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		enterText(robot, "giao", "#NewBPNameTextField");
	}
}
