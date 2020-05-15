package views;

import static org.junit.jupiter.api.Assertions.fail;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.Main;
import models.BusinessPlan;
import models.Comment;
import models.MainViewModel;
import models.MainViewTransitionModel;
import models.MyRemoteClient;
import models.MyRemoteImpl;
import models.Person;
import models.Section;
import models.VMOSA;

@ExtendWith(ApplicationExtension.class)
public class TestTask2 {
	
	static MyRemoteImpl server;
	static MyRemoteClient client;
	
	@BeforeAll
	//Initialize server and client 
	static void Initialization()
	{		
		try
		{		
			Registry registry = LocateRegistry.createRegistry(1099);

			server = new MyRemoteImpl();
			
			//initialize storedBP
			BusinessPlan BP = new VMOSA();
			BP.name="Giao";
			BP.year = 2020;
			BP.department ="CS";
			BP.isEditable=false;
			BP.addSection(BP.root);
			BP.root.content=("this is the vision");
			BP.root.children.get(0).content=("this is the misson");
			BP.root.children.get(0).children.get(0).content=("this is the objective1");
			BP.root.children.get(0).children.get(0).children.get(0).content=("this is the strategy1");
			BP.root.children.get(0).children.get(0).children.get(0).children.get(0).content=("this is the actionplan1");
			BP.addSection(BP.root.children.get(0));
			BP.root.children.get(1).content=("extra misson");
			BP.root.children.get(1).children.get(0).content=("extra objective");
			BP.root.children.get(1).children.get(0).children.get(0).content=("extra strategy");
			BP.root.children.get(1).children.get(0).children.get(0).children.get(0).content=("extra actionplan");

			BusinessPlan BP2 = new VMOSA();
			BP2.name="Hoaho";
			BP2.year = 2009;
			BP2.department ="CS";
			BP2.isEditable=true;
			BP2.addSection(BP2.root);
			BP2.root.children.get(0).content=("this is the misson");
			BP2.root.children.get(0).children.get(0).content=("this is the objective1");
			BP2.root.children.get(0).children.get(0).children.get(0).content=("this is the strategy1");
			BP2.root.children.get(0).children.get(0).children.get(0).children.get(0).content=("this is the actionplan1");

			ArrayList <BusinessPlan> storedBP=new ArrayList<BusinessPlan>();
			storedBP.add(BP);
			storedBP.add(BP2);

			//initialize storedUser
			Person wynnie=new Person("wynnie","wynnie","CS", true);
			Person terry=new Person("terry","terry","CS", false);
			
			BP.root.comments.add(new Comment("Nice job",terry));
			BP.root.children.get(0).comments.add(new Comment("This one should be improved. It should be more detailed",wynnie));
			
			ArrayList <Person> storedUser=new ArrayList<Person>();
			storedUser.add(wynnie);
			storedUser.add(terry);

			server.setStoredBP(storedBP);
			server.setStoredUser(storedUser);
			
		} catch (RemoteException e)
		{
			e.printStackTrace();
		}
		client = new MyRemoteClient(server);
	}
	
	@Start //Before
	private void start(Stage stage)
	{
		
		try {

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
	
	//int to string
	private void enterYearText(FxRobot robot, int text, String target)
	{
		robot.clickOn(target);
		robot.write(Integer.toString(text));
	}

	private void login(FxRobot robot, String username, String password)
	{
		enterText(robot, username, "#usernameInput");
		enterText(robot, password, "#passwordInput");
		robot.clickOn("#login");
	}
	
	private void selectBP(FxRobot robot, String name) {
		robot.clickOn(name);
	}
	
	private void cloneBP(FxRobot robot, String name,String newname, int year)
	{	
		selectBP(robot,name);
		robot.clickOn("#cloneOnlist");
		enterText(robot, newname, "#cloneName");
		enterYearText(robot, year, "#cloneYear");
		robot.clickOn("#cloneBP");
	}
	
	private void goBPMainPage(FxRobot robot, String name) {
		robot.clickOn("#BPlist");
		selectBP(robot,name);
		robot.clickOn("#copyOnlist");
	}
	
	private void selectSection(FxRobot robot, String name) {
		robot.clickOn(name);
	}
	
	private void selectComment(FxRobot robot, String name) {
		robot.clickOn(name);
	}
	
	private void viewComment(FxRobot robot, String name) {
		selectSection(robot,name);
		robot.clickOn("#ViewSection");
	}
	
	private void addComment(FxRobot robot, String content) {
		enterText(robot,content, "#newComment");
		robot.clickOn("#AddButton");
	}
	//person.username+":\n"+content
	private void deleteComment(FxRobot robot, String CommentString) {
		selectComment(robot,CommentString);
		robot.clickOn("#DeleteButton");
	}
	
	//use hasExactlyNumItems and hasListCell to check whether or not the comment has been updated
	private void checkCommentList(FxRobot robot, Section sec) {
		
		@SuppressWarnings("unchecked")
		ListView<Comment> getComments = (ListView<Comment>) robot.lookup("#AllComment").queryAll().iterator().next();
		
		Assertions.assertThat(getComments).hasExactlyNumItems(sec.comments.size());

		for(Comment i: sec.comments) {
			Assertions.assertThat(getComments).hasListCell(i); 
		}		
	}
	
	@Test
	public void testALLTask2(FxRobot robot) {
		try {
			Thread.sleep(1000);

			//login and go to giao's main page
			login(robot,"wynnie","wynnie");
			goBPMainPage(robot,"Giao (2020)");
			
			//view comment in Vision
			viewComment(robot,"Vision");
			addComment(robot,"Giao");
			
			//check Vision
			Section checkSec=client.getCurrentBP().getRoot();
			checkCommentList(robot,checkSec);
			
			//delete the original one
			deleteComment(robot,"terry"+":\n"+"Nice job");
			//check
			checkSec=client.getCurrentBP().getRoot();
			checkCommentList(robot,checkSec);
			
			//back to MainPage
			robot.clickOn("#mainPage");
			robot.clickOn("#leaveYes");
			//go to giao's main page to check the change has been stored
			goBPMainPage(robot,"Giao (2020)");
			viewComment(robot,"Vision");
			
			//check
			checkSec=client.getCurrentBP().getRoot();
			checkCommentList(robot,checkSec);
			
			//back to MainPage
			robot.clickOn("#mainPage");
			robot.clickOn("#leaveYes");
			
			//clone a Giao to see the comment can be clone or not
			robot.clickOn("#BPlist");
			cloneBP(robot, "Giao (2020)", "New Giao", 2021);
			goBPMainPage(robot,"New Giao (2021)");
			viewComment(robot,"Vision");
			
			//check the Comments are the same for cloned BP and original one
			Section checkSec2=client.getCurrentBP().getRoot();
			checkCommentList(robot,checkSec2);
			checkCommentList(robot,checkSec);
			
			//logout and login as terry to check the updated comment
			robot.clickOn("#mainPage");
			robot.clickOn("#leaveYes");
			robot.clickOn("#logout");
			login(robot,"terry","terry");
			goBPMainPage(robot,"Giao (2020)");
			viewComment(robot,"Vision");
			
			//check the Comment is the same for both user
			Section checkSec3=client.getCurrentBP().getRoot();
			checkCommentList(robot,checkSec3);
			checkCommentList(robot,checkSec);
			
			Thread.sleep(1000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
