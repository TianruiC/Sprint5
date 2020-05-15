package models;

public class Comment {
	public String content;
	public Person person;
	
	public Comment(String newCon, Person newPer) {
		this.content=newCon;
		this.person=newPer;	
	}
	
	@Override
	public String toString() {
		return person.username+":\n"+content;
	}
}
