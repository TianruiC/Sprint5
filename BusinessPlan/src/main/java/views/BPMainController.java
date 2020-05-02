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
	private Button MainPage;

	@FXML
	private Button Edit;

	@FXML
	private Button Delete;

	@FXML
	private Button Add;

	BPMainModel model;
	
    //Don't want the user clicking things if they haven't select a specific section
    public void setDisabled(boolean val)
    {
    	Edit.setDisable(val);
    	Delete.setDisable(val);
    	Add.setDisable(val);
    }

	private void addNodes(Section p, TreeItem<Section> t) {
		t.setExpanded(true);
		for (Section child : p.children) {
			TreeItem<Section> node = new TreeItem<Section>(child);
			t.getChildren().add(node);
			addNodes(child, node);
		}
	}

	public void setModel(BPMainModel newModel) {
		model = newModel;
		Section root = model.client.getCurrentBP().getRoot();

		TreeItem<Section> rootItem = new TreeItem<Section>(root);
		addNodes(root, rootItem);
		outlineTree.setShowRoot(true);

		outlineTree.setRoot(rootItem);

	}
	
    @FXML
    void onClickBack(ActionEvent event) {
    	//close current BPMain window
    	Stage stage = (Stage) MainPage.getScene().getWindow();
    	stage.close();
    	//show MainView window
    	model.showMainView();	

    }
    
    @FXML
    void onClickAdd(ActionEvent event) {

    }

    @FXML
    void onClickDelete(ActionEvent event) {

    }

    @FXML
    void onClickEdit(ActionEvent event) {

    }

}


