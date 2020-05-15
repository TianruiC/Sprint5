package models;

import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;


// Concrete server class
public class MyRemoteImpl implements MyRemote {

	private Person loginPerson=null;
	private ArrayList <BusinessPlan> storedBP=new ArrayList<BusinessPlan>();
	private ArrayList <Person> storedUser=new ArrayList<Person>();
	private ArrayList<ArrayList<String>> diffSec = new ArrayList<>();
  
    public MyRemoteImpl() {
    	
    }

	public Person getLoginPerson() {
		return loginPerson;
	}

	public void setLoginPerson(Person loginPerson) {
		this.loginPerson = loginPerson;
	}

	public ArrayList<Person> getStoredUser() {
		return storedUser;
	}

	public void setStoredUser(ArrayList<Person> storedUser) {
		this.storedUser = storedUser;
	}
	
	public ArrayList<BusinessPlan> getStoredBP() {
		return storedBP;
	}
	
	public void setStoredBP(ArrayList<BusinessPlan> storedBP) {
		this.storedBP = storedBP;
	}

	public String sayHello() {
        return "Hello User!";
    }
    
	public Person verifyLoginPerson(String username, String password) {
    	for(int i=0; i<storedUser.size();i++){
    		if ((storedUser.get(i).username.equals(username))&&(storedUser.get(i).password.equals(password))){
    			loginPerson=storedUser.get(i);
    			System.out.println("user found.");
    			return loginPerson;
    		}
    	}
    	System.out.println("user not found.");
    	return null;
    }
    
    //helper class for checking the person exists or not in the test file
    public Person findPerson(String username, String password, String deparment, Boolean bol) {
    	for(int i=0; i<storedUser.size();i++){
    		if ((storedUser.get(i).username.equals(username))&&(storedUser.get(i).password.equals(password))){
    			Person personfound=storedUser.get(i);
    			System.out.println("user found.");
    			return personfound;
    		}
    	}
    	System.out.println("user not found.");
    	return null;
    }
    
    public void addPerson(String username, String password, String department, Boolean isAdmin) {
    	Person newperson=new Person(username,password,department,isAdmin);
    	storedUser.add(newperson);
    	System.out.println("New User:"+username+" added.");
    }
    
    public void changeEditable(int year, boolean bool) {
    	BusinessPlan bpcur=null;
    	if(loginPerson==null) {
    		System.out.println("PLEASE LOGIN FIRST.");
    	}
    	else {
    		for (int i=0; i<storedBP.size();i++){
        		if((storedBP.get(i).department.equals(loginPerson.department))&&(storedBP.get(i).year==year)){
        			bpcur=storedBP.get(i);
        		}
    		}
    		if(bpcur!=null) {
    			bpcur.isEditable=bool;
    			System.out.println("BusinessPlan isEditable changed to: "+bool);
    		}
    		else {
    			System.out.println("BusinessPlan not found.");
    		}
    	}
    	
    }
    
    public void logOut() {
    	loginPerson=null;
    	System.out.println("user logout from Server.");
    }
    
    //called by client askForAllBP function
    public ArrayList<BusinessPlan> findDepAllBP(){
    	ArrayList<BusinessPlan> DepAllBP= new ArrayList<BusinessPlan>();
    	if(loginPerson==null) {
    		System.out.println("PLEASE LOGIN FIRST.");
    		return null;
    	}
    	for (int i=0; i<storedBP.size();i++){
    		if((storedBP.get(i).department.equals(loginPerson.department))){
    			DepAllBP.add(storedBP.get(i));
    			System.out.println(loginPerson);
    			System.out.println(storedBP.get(i));
    			}
    	}
    	System.out.println(DepAllBP);
    	return DepAllBP;
    }
    
    //called by client askForBP function
    public BusinessPlan findBP(int year) {
    	if(loginPerson==null) {
    		System.out.println("PLEASE LOGIN FIRST.");
    		return null;
    	}
    	for (int i=0; i<storedBP.size();i++){
    		if((storedBP.get(i).department.equals(loginPerson.department))&&(storedBP.get(i).year==year)){
    			System.out.println("BusinessPlan found.");
    			return storedBP.get(i);
    		}
    	}
    	System.out.println("BusinessPlan not found.");
    	return null;
    }
    
    //called by client uploadBP function
    public String addBP(BusinessPlan BP) {
    	String Message="";
    	if(loginPerson==null) {
    		Message="PLEASE LOGIN FIRST.";
    		System.out.println(Message);
    		return Message;
    	}
    	System.out.println(storedBP);
    	for (int i=0; i<storedBP.size();i++){
    		BusinessPlan current=storedBP.get(i);

    		if((current.department.equals(BP.department))&&(current.year==BP.year)){
    			//Message=("Business Plan already exists.");
    			System.out.println("Business Plan already exists.");
    			if(current.isEditable==false) {
    				Message="This BusinessPlan is not Editable";
        			System.out.println(Message);
        			return Message;
    			}
    			storedBP.remove(current);
    			storedBP.add(BP);
    			Message="Replaced Old Version BP with New One.";
    			System.out.println(Message);
    			return Message;
    		}
    	}
    	storedBP.add(BP);
    	System.out.println("Business does not exist.");
    	Message="Added new BP to Server";
    	System.out.println(Message);
    	return Message;
    }
    
    @Override
	public String addNewBP(BusinessPlan BP) throws RemoteException {
    	String Message="";
    	if(loginPerson==null) {
    		Message="PLEASE LOGIN FIRST.";
    		System.out.println(Message);
    		return Message;
    	}
    	for (int i=0; i<storedBP.size();i++){
    		BusinessPlan current=storedBP.get(i);
    		if((current.department.equals(BP.department))&&(current.year==BP.year)){
    			Message=("Business Plan already exists.");
    			System.out.println(Message);
    			return Message;
    		}
    	}
    	storedBP.add(BP);
    	System.out.println("Business does not exist.");
    	Message="Added new BP to Server";
    	System.out.println(Message);
    	return Message;
	}
    
    //save all data to the disk every two minutes 
  	//timeInterval should be set to 1000*120 when call the function
  	public void startEncode(long timeInterval) {
          Runnable runnable = new Runnable() {
          	public void run() {
          		while (true) {
          			// ------- code for task to run	      
          			XMLEncodeAllData();
          			System.out.println("Server has automatically Encode all Data.");
          			// ------- ends here	      
          			try {
          				Thread.sleep(timeInterval);
          				} 
          			catch (InterruptedException e) {
          				e.printStackTrace();
          				}	      
          			}	    
          		}	  
          	};	  	  
          	Thread thread = new Thread(runnable);
          	thread.start();
  	}
  	
	//Encoder
	public void XMLEncodeAllData() {
		String BusinessPlan_File="Server_BusinessPlan.xml";
		String User_File="Server_User.xml";
		XMLEncodeBP(BusinessPlan_File);
		XMLEncodeUser(User_File);
	}
	
    //helperEncodeFunction
	public void XMLEncodeBP(String filename) {
		XMLEncoder encoder=null;
		try{
			encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)));
			}
		catch(FileNotFoundException fileNotFound){
				System.out.println("ERROR: While Creating or Opening the File"+filename);
			}
			encoder.writeObject(this.storedBP);
			encoder.close();
	}
	
	public void XMLEncodeUser(String filename) {
		XMLEncoder encoder=null;
		try{
			encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)));
			}
		catch(FileNotFoundException fileNotFound){
				System.out.println("ERROR: While Creating or Opening the File"+filename);
			}
			encoder.writeObject(this.storedUser);
			encoder.close();
	}
	
	//Decoder
	//Call two decoder functions to read all data from the disk
	//When the server starts, we can call this two function first
	//Since we set the filename of encoder's and decoder's files are the same, 
	//The server will always store and read from the same files.
	@SuppressWarnings("unchecked")
  public ArrayList <BusinessPlan> XMLDecodeBP() {
		String BusinessPlan_File="Server_BusinessPlan.xml";
		XMLDecoder decoder=null;
		try {
			decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(BusinessPlan_File)));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File "+BusinessPlan_File+" not found");
		}
		return (ArrayList<BusinessPlan>)decoder.readObject();
	}
	
	@SuppressWarnings("unchecked")
  public ArrayList <Person> XMLDecodeUser() {
		String User_File="Server_User.xml";
		XMLDecoder decoder=null;
		try {
			decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(User_File)));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File "+User_File+" not found");
		}
		return (ArrayList <Person>)decoder.readObject();
	}
	
    public static void main(String args[]) {
        try {
        	MyRemoteImpl obj = new MyRemoteImpl();
        	MyRemote stub = (MyRemote) UnicastRemoteObject.exportObject(obj, 0);
            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("MyRemote", stub);

            System.err.println("Server ready");

        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
    //get all the children of additional section
    //using recursion
    public void getDiffChild(Section section, ArrayList<String> List) {
    	for(Section children: section.getChildren()) {
    		List.add(children.showContent());
    		getDiffChild(children,List);
    	}
    }
    public void compareSection(Section section1, Section section2) {
  
    	int diffsize = section1.getChildren().size() - section2.getChildren().size();
    	int compareSize;
    	//section1 has more child
    	if(diffsize>0) {
    		compareSize=section2.getChildren().size();
    		for(int i=0; i<diffsize;i++) {
    			diffSec.get(0).add(section1.getChildren().get(i+compareSize).showContent());
    			getDiffChild(section1.getChildren().get(i+compareSize),diffSec.get(0));
    		}
    	}
    	//section2 has more child
    	else if(diffsize<0) {
    		compareSize=section1.getChildren().size();
    		for(int i=0; i>diffsize;i--) {
    			diffSec.get(1).add(section2.getChildren().get(compareSize-i).showContent());
    			getDiffChild(section2.getChildren().get(compareSize-i),diffSec.get(1));
    		}
    	}
    	//the same size
    	else {
    		compareSize=section1.getChildren().size();
    	}
    	//compare sections
    	for (int i = 0; i<compareSize; i++) {
    		if((section1.getChildren().get(i).showContent()).equals(section2.getChildren().get(i).showContent())!=true) {
    			diffSec.get(0).add(section1.getChildren().get(i).showContent());
    			diffSec.get(1).add(section2.getChildren().get(i).showContent());
    		}
    		compareSection(section1.getChildren().get(i),section2.getChildren().get(i));
		}
    	
    }
	public ArrayList<ArrayList<String>> compareBP(BusinessPlan BP1, BusinessPlan BP2) throws RemoteException {
		diffSec.clear();
		diffSec.add(new ArrayList<String>());//BP1
		diffSec.add(new ArrayList<String>());//BP2
		
		if((BP1.getRoot().showContent()).equals(BP2.getRoot().showContent())!=true) {
			diffSec.get(0).add(BP1.getRoot().showContent());
			diffSec.get(1).add(BP2.getRoot().showContent());
		}
		
		//recursion to get all child section
		compareSection(BP1.getRoot(),BP2.getRoot());
		
		return diffSec;
	}

}