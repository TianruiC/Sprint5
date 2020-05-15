package views;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import models.BPMainModel;
import models.Comment;
import models.Section;

public class CommentController {
	
	BPMainModel model;  
	//previous working BPMain window
	Section current;
	
	public ObservableList<Comment> Comments= FXCollections.observableArrayList();
	
	public void setModel(BPMainModel newModel, Section cur)
    {
		model = newModel;	
		current = cur;
		
		Name.textProperty().set(current.getName());
		Content.textProperty().set(current.content);
		newComment.textProperty().set(null);
		
		//regenerate the comments
		Comments.clear();
		ArrayList<Comment> CommentArrayList=cur.getComments();
		for (int i=0; i<CommentArrayList.size();i++){
			Comments.add(CommentArrayList.get(i));
		}
		CommentList.setItems(Comments);
    }
	@FXML
    private Label Name;

    @FXML
    private TextArea Content;
    
	@FXML
    private ListView<Comment> CommentList;

    @FXML
    private TextArea newComment;

    @FXML
    //no need to handle the empty comment case
    //since the users can delete that useless by themselves
    void onClickAdd(ActionEvent event) {
    	current.getComments().add(new Comment(newComment.getText(),model.client.getLoginPerson()));
    	model.client.uploadBP();
    	setModel(model, current);
    }

    @FXML
    //only delete the selected comment
    //unable to undo
    //won't response if nothing is selected.
    void onClickDelete(ActionEvent event) {
    	System.out.println("onClickDelete");
    	Comment SelectedComment=CommentList.getSelectionModel().getSelectedItem();
    	if(SelectedComment!=null) {
    		current.deleteComment(SelectedComment);
    		model.client.uploadBP();
    		setModel(model, current);
    	}
    }

}
