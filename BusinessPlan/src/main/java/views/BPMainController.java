package views;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;
import models.BPMainModel;
import models.Section;


public class BPMainController {


	@FXML
	private Label BPname;

	@FXML
	private Label BPyear;

	@FXML
	private Label BPdep;

	@FXML
	private Label BPtype;

	@FXML
	private Label BPedi;

	@FXML
	private TreeView<Section> outlineTree;

	@FXML
    private TreeView<String> contentTree;

	@FXML
	private Button MainPage;
	
	@FXML
    private Button ViewBP;
	
	@FXML
	private Button Edit;

	@FXML
	private Button Delete;

	@FXML
	private Button Add;

	BPMainModel model;
	
	Stage currentStage;
	
    //add tree items to the outline tree
	private void addNodes(Section p, TreeItem<Section> t) {
		t.setExpanded(true);
		for (Section child : p.children) {
			TreeItem<Section> node = new TreeItem<Section>(child);
			t.getChildren().add(node);
			addNodes(child, node);
		}
	}
	
	//add tree items to the content tree
	private void addStringNodes(Section p, TreeItem<String> t) {
		t.setExpanded(true);
		for (Section child : p.children) {
			TreeItem<String> node = new TreeItem<String>(child.showContent());
			t.getChildren().add(node);
			addStringNodes(child, node);
		}
	}

	public void setModel(BPMainModel newmodel) {
		model = newmodel;
		Section root = model.client.getCurrentBP().getRoot();
		
		//show BP info details
		BPname.textProperty().set(model.client.getCurrentBP().name);
		BPyear.textProperty().set(Integer.toString(model.client.getCurrentBP().year));
		BPdep.textProperty().set(model.client.getCurrentBP().department);
		BPtype.textProperty().set(model.client.getCurrentBP().type);
		if(model.client.getCurrentBP().isEditable==true) {
	    	BPedi.textProperty().set("Yes");
	    }
	    else {
	    	BPedi.textProperty().set("No");
	    	//can not use buttons to make changes
	    	Edit.setDisable(true);
	    	Delete.setDisable(true);
	    	Add.setDisable(true);
	    }
		
		//set tree views
		TreeItem<Section> rootItem = new TreeItem<Section>(root);
		addNodes(root, rootItem);
		outlineTree.setShowRoot(true);
		outlineTree.setRoot(rootItem);
		
    	String rootContent = root.showContent();
		TreeItem<String> rootItem2 = new TreeItem<String>(rootContent);
		addStringNodes(root, rootItem2);
		contentTree.setShowRoot(true);
		contentTree.setRoot(rootItem2);
	}
	
    @FXML
    void onClickBack(ActionEvent event) {
    	//save the current working BPMain window
    	Stage stage = (Stage) MainPage.getScene().getWindow();
        currentStage = stage;
    	
    	//show Leave Confirmation window
    	model.showLeaveConfirm(stage);

    }
    
    @FXML
    void onClickAdd(ActionEvent event) {
 
    }

    @FXML
    void onClickDelete(ActionEvent event) {

    }

    @FXML
    void onClickEdit(ActionEvent event) {
    	Section clickedSection=outlineTree.getSelectionModel().getSelectedItem().getValue();
    	
    	System.out.println(clickedSection);
    	if(clickedSection!=null) {
    		model.showEditView(clickedSection);
        }
    	
    }
    @FXML
    void onClickView(ActionEvent event) {
    	model.showTreeView();
    }

}


