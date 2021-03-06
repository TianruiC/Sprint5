package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Section implements Serializable
{

	private static final long serialVersionUID = 3004879294918214266L;
	public String name;
	public String content = "";
	public Section parent = null;
	public ArrayList<Section> children = new ArrayList<Section>();
	public ArrayList<Comment> comments=new ArrayList<Comment>();
	// default constructor for XML
	public Section()
	{
		this("default");
	}

	public Section(String name)
	{
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
	
	public ArrayList<Comment> getComments() {
		return comments;
	}

	public void deleteComment(Comment comment) {
		this.comments.remove(comment);
	}
	
	public String showContent() {
		return name+":\n"+content;
	}
	
	// getters and setters
	public Section getParent()
	{
		return parent;
	}

	public void setParent(Section parent)
	{
		this.parent = parent;
	}

	// this is used for XMl encoder, but we can never change the children of a
	// Section
	public void setChildren(ArrayList<Section> children)
	{
		this.children = children;
	}

	public String getName()
	{
		return name;
	}

	// this is used for XML encoder, but we can never change the name of a Section
	public void setName(String name)
	{
		this.name = name;
	}

	public ArrayList<Section> getChildren()
	{
		return children;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}
	// end of getters and setters

	// add child to the array list
	public void addChild(Section child)
	{
		children.add(child);
		child.parent=this;
	}

	// remove child from the array list
	public void deleteChild(Section child)
	{
		children.remove(child);
	}
}
