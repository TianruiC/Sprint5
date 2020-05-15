package views;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import models.BPMainModel;
import models.BusinessPlan;
import models.Section;

public class CompareDetailedController {
	
	BPMainModel model; 
	BusinessPlan ComparedBP;
	
	private void addNodes(Section p, TreeItem<String> t, ArrayList<String> diff) {
		t.setExpanded(true);
		for (Section child : p.children) {
			String Descrption=child.showContent();
			if(diff.contains(Descrption)) {
				Descrption="***"+Descrption;
			}
			TreeItem<String> node = new TreeItem<String>(Descrption);
			t.getChildren().add(node);
			addNodes(child, node, diff);
		}
	}
	public void setModel(BPMainModel newModel, BusinessPlan bp)
    {
		model = newModel;
		ComparedBP=bp;
		
		//get the diffArrayList
		ArrayList<ArrayList<String>> diff = model.client.diffSectionList(bp);
		ArrayList<String> diffSectionOfBP1=diff.get(0);
		ArrayList<String> diffSectionOfBP2=diff.get(1);
		System.out.println(diffSectionOfBP1.toString());
		System.out.println(diffSectionOfBP2.toString());
		
		//show BP1 with highlight differences
		Section root = model.client.getCurrentBP().getRoot();
    	String rootContent = root.showContent();
    	if(diffSectionOfBP1.contains(rootContent)) {
    		rootContent="***"+rootContent;
		}
		TreeItem<String> rootItem = new TreeItem<String>(rootContent);
		addNodes(root, rootItem,diffSectionOfBP1);
		BP1.setShowRoot(true);
		BP1.setRoot(rootItem);
		BP1Name.textProperty().set(model.client.getCurrentBP().name);
		
		//show BP2 with highlight differences
		Section root2 = ComparedBP.getRoot();
    	String rootContent2 = root2.showContent();
    	if(diffSectionOfBP2.contains(rootContent2)) {
    		rootContent2="***"+rootContent2;
		}
		TreeItem<String> rootItem2 = new TreeItem<String>(rootContent2);
		addNodes(root2, rootItem2,diffSectionOfBP2);
		BP2.setShowRoot(true);
		BP2.setRoot(rootItem2);
		BP2Name.textProperty().set(ComparedBP.name);
		
    }
	@FXML
    private Label BP1Name;

    @FXML
    private TreeView<String> BP1;

    @FXML
    private Label BP2Name;

    @FXML
    private TreeView<String> BP2;
}
