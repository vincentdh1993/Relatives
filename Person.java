import java.util.ArrayList;
import java.util.List;

public class Person { //Created New Person class with name,father,mother, and the list.
	private String name;
	private Person father;
	private Person mother;
	private ArrayList<Person> children;
	
	public Person(String name){ //Person that takes parameter as String name.
		this.name = name;
		this.children = new ArrayList<Person>();
	}
	
	public void setMother(Person mother){ //setMother.
		this.mother = mother;
	}
	
	public void setFather(Person father){
		this.father = father;
	}
	
	public void setChild(Person child){
		this.children.add(child);
	}

	public boolean hasMother() { //boolean that checks if the person has mother.
		if (this.mother == null){
			return false;
		}else 
		return true;
	}
	
	public boolean hasFather() {
		if (this.father == null){
			return false;
		}else 
		return true;
	}
	public boolean hasChild() { //boolean that checks if the person has child.
		return (!this.children.isEmpty()); 
	}
	
	public String getName(){
		return this.name;
	}
	
	public Person getFather() {
		return this.father;
	}
	
	public Person getMother() {
		return this.mother;
	}
	
	public ArrayList<Person> getChildren() { //returning the list of children
		return this.children;
	}
	
	
	
	
}
