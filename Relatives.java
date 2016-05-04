import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.*;

public class Relatives {
	public static void main(String[] args) throws FileNotFoundException{
	File file = askFile();
	SettingMap(file);
		
	} // main gwalho
	
	/*
	 * Method that asks file to the user and return the file. Structured to accept other files.
	 */
	public static File askFile() throws FileNotFoundException{ 
		Scanner input = new Scanner(System.in);
		System.out.println("What is the input file?");
		String f = input.nextLine();
		File file = new File(f);
		return file;
	}
	
	
	/*
	 *Method that runsthrough the file and prints the ancestor and descendants. 
	 */
	public static void SettingMap(File file) throws FileNotFoundException{
		Scanner console = new Scanner(System.in);
		HashMap<String, Person> family = new HashMap<>(); //create a new Map that takes String as a Key and Person as a value.
		Scanner fileScanner = new Scanner(file); //fileScanner that reads through the file
		family = StoreFamily(fileScanner, family); //first part of the file.
		System.out.println("Person's name ('quit' to end)?"); //after getting all the families, ask the person name.
		String name = console.nextLine();
		if(name.equals("quit")){ //if user wants to quit, run the quit method.
			quit();
		} else if(family.containsKey(name)){ //if there is a name as a key of Map,
			StoreParents(family, fileScanner); //run through the second part of the file (after 'END'), setting parents and children.
			System.out.println("Ancestors:"); //print out the ancestors first.
			printAncestors(name, family,1);
			System.out.println("descendants: ");
			printDescendants(name, family,1); //print out the descendants.
		}else{ //if the name is not in the list, finish the method.
			System.out.println("The name is not in the list.");
		}
	}
	
	/*
	 * Method that prints Ancestors. From the map.
	 */
	private static void printAncestors(String name, HashMap<String, Person> family, int indent) {
		for (int i = 0; i < indent; i++) {
			System.out.print("    "); //indent which indicates the generation of ancestors.
		}
		System.out.println(name); //Print the name first.
		Person s = family.get(name); //turn the name to person.
		if (s.hasMother()) { //if the person has mother,
			if (s.getMother().getName() != null) { //if mother's name is not empty,
				String mom = s.getMother().getName(); //mom becomes..mom.
				printAncestors(mom, family, indent + 1); //do the recursion of the method but the name parameter changes to mom.
			}
		}
		if (s.hasFather()) { //same logic as printing mother.
			if (s.getFather().getName() != null) {
				String dad = s.getFather().getName();
				printAncestors(dad, family, indent + 1);
			}
		}
	}
/*
 * Method that prints the descendants.
 */
	private static void printDescendants(String name, HashMap<String, Person> family, int indent) {
		for (int j = 0; j < indent; j++) { //indent which indicates the generation of descendants.
			System.out.print("    ");
		}
		System.out.println(name); //print the name first.
		Person s = family.get(name); //change the name to the person.
		if (s.hasChild()) { //If the person has child,
			for (Person i : s.getChildren()) { //run through the children list of the person.
				String child = i.getName(); //Child is the person of the list.
				printDescendants(child, family, indent + 1); //do the recursion with the child as a new parameter of name.
			}
		}
	}



	
	/*
	 * Method that assigns parents to each person in the second part of the file.
	 */
	private static void StoreParents(HashMap<String, Person> family, Scanner input) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String name = input.nextLine();
		while (!name.equals("END")) { // Second half of the file.
			Person s = family.get(name); // First Name is a person.
			String Mom = input.nextLine(); // Second name is first's mother.
			String Dad = input.nextLine(); // Third name is first's father.
			if (!Mom.equals("unknown")) { // If the mother is not unknown,
				Person mom = family.get(Mom); //get mom as a person.
				if (family.containsValue(mom)) { // If the map has the value of Person mom,
					mom.setChild(s); //add child to the mom.
					s.setMother(mom); // add mother to mom.
				}
			}
			if (!(Dad.equals("unknown"))) { //same logic as the mother side.
				Person dad = family.get(Dad);
				if (family.containsValue(dad)) {
					s.setFather(dad);
					dad.setChild(s);
				}	
			}
			name = input.nextLine(); //update the name from the file list.
		}
	}


	/*
	 * Method that stores family list into the map.
	 */
	private static HashMap<String, Person> StoreFamily(Scanner fileScanner, HashMap<String, Person> family) {
		// TODO Auto-generated method stub
		String name = fileScanner.nextLine(); //Scanning the first part of the list.
		while (!name.equals("END")){ //runs until the first part of the file.
			family.put(name, new Person(name));//Store the map with the person.
			name = fileScanner.nextLine(); //updating the name.
		}
		return family; //return the stored family map.
	}
	
	/*
	 * Quitting method.
	 */
	public static void quit(){
		System.out.println("Quitting the Program.");
	}
	
	
	
} // class gwalho