package control;

import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.Iterator;

import containers.Item;







/* 
 * Will take care of retrieving and editing things in the item list
 */
public class ListHandler {

	private ArrayList<Observer> observers;
	private ArrayList<ItemObserver> itemObservers;
	
	private ArrayList<Item> itemList;
	
	
	public ListHandler(String file){
		this.itemList = ListLoader.load(file);
		this.observers = new ArrayList();
		this.itemObservers = new ArrayList();
	}
	
	
	public void deleteItem(int index){
		this.itemList.remove(index);	
		updateObservers();
		updateItemObservers(2, index);
	}
	
	public void editItem(int index, int ino, int quantity, String name, String vendor){
		Item i = itemList.get(index);
		if(name == null){
			throw new IllegalArgumentException("Part name cannot be null");
		}
		if(partNameExists(name) && !name.equals(i.getPartName())){
			throw new IllegalArgumentException("Part Name Exists");
		}
		
		i.setPartNumber(ino);
		i.setQuantity(quantity);
		i.setPartName(name);
		i.setVendor(vendor);
		updateObservers();
		updateItemObservers(1, index);		
	}
	public void addItem(int ino, int quantity, String name, String vendor){
		/*Argument checks.
			a new item must follow these requirements:
				- item number cannot be null
				- name cannot be null
				- quantity must be 1 or greater
				- itemname cannot be a duplicate
				
		
		if(ino == null){
			throw new IllegalArgumentException("there needs to be an item number");
		}*/
		if(name == null){
			System.out.println("name was null");
			throw new IllegalArgumentException("Part name cannot be null");
		}
		if(quantity <= 0){
			System.out.println("Quantity was 0");
			throw new IllegalArgumentException("Quantity must be greater than 0");
		}
		if(partNameExists(name)){
			System.out.println("name exists");
			throw new IllegalArgumentException("Part Name Exists");
		}
		
		//add the new item to the list
		itemList.add(new Item(ino, name, vendor, quantity));
		updateObservers();

		
	}
	
	// helper function that checks for duplicate item names in the list
	
	private boolean partNameExists(String n){
		java.util.Iterator<Item> i = itemList.iterator();
		while(i.hasNext()){
			if(i.next().getPartName().equals(n)){
				return true;
			}
		}
		return false;
	}
	
	public void error(){
		
	}
	
	public ArrayList<Item> getItemList(){
		return this.itemList;
	}
	
	public void printList(){
		java.util.Iterator<Item> i = itemList.iterator();
		while(i.hasNext()){
			Item temp = i.next();
			System.out.printf("%s, %s, %s, %s\n", temp.getPartNumber(), temp.getPartName(), temp.getVendor(), temp.getQuantity());
		}

	}
	
	public Item getItem(int index){
		return itemList.get(index);
	}
	
	
	//observer functions
	public void register(Observer o){
		this.observers.add(o);
	}
	
	public void updateObservers(){
		for(int i = 0; i < observers.size() ; i ++){
			observers.get(i).updateObserver();
		}
		
	}
	
	public void updateItemObservers(int acommand, int index){
		for(int i = 0; i < itemObservers.size(); i ++){
			itemObservers.get(i).updateItemObserver(acommand, index);
		}
	}
	public void registerItemObserver(ItemObserver o){
		this.itemObservers.add(o);
	}



	
}
