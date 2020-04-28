package models;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainViewModel {

	MyRemoteClient client;
	
	ObservableList<BusinessPlan> BPlist= FXCollections.observableArrayList();
	public MainViewModel(MyRemoteClient wowclient) {
		this.client=wowclient;
		ArrayList<BusinessPlan> Dulplicate=client.askForAllBP();
		for (int i=0; i<Dulplicate.size();i++){
			BPlist.add(Dulplicate.get(i));
		}
		// System.out.println(BPlist);
	}
	/**
	 * @return the bPlist
	 */
	public ObservableList<BusinessPlan> getBPlist() {
		return BPlist;
	}
	
	
}
