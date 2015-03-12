package Model;

import java.util.ArrayList;

import DBclass.DBQuery;

public class InventoryModel {
	
	//private ArrayList<PartModelObserver> observer = new ArrayList<PartModelObserver>();
	int parts;
	private ArrayList<PartModel> Inventory = new ArrayList<PartModel>();
	private ArrayList<ItemModel> ItemInventory = new ArrayList<ItemModel>();
	public DBQuery myDB;
	int currentid = 1;
	int currentItemId = 1;
	
	public InventoryModel(DBQuery DB) {
		this.myDB = DB;
		this.Inventory = DB.getParts();
		this.ItemInventory = DB.getInventory();
	}
	
	public ArrayList<PartModel> getInventory(){
		return Inventory;
	}
	
	public ArrayList<ItemModel> getItemInventory(){
		return ItemInventory;
	}
	
    public int getSize(){
    	return Inventory.size();
    }
    
    public int addPart(PartModel m){
    	String name = m.getPname();
    	String number = m.getPnum();
    	String v = m.getVendor();
    	if(number.equals("")){
    		return -1;
    	}
    	if(name.equals("")){
    		return -1;
    	}
    	if(number.length() > 20 || name.length() > 255 || v.length() > 20){
			System.out.println("Invalid String Input Size");
			return -1;
		}
		if(number.length() <= 0 || name.length() <= 0){
			System.out.println("Required Fields: Part Name, Part Number");
			return -1;
		}
		
		if(checkNum(number) == -1){
			System.out.println("Part Number Taken");
			return -1;
		}
		m.setId(currentid++);
    	Inventory.add(m);
		myDB.addPart(m);
    	return 0;
    }
    
    public int checkNum(String n){
    	for(int i = 0; i < Inventory.size();i++){
    		if(Inventory.get(i).getPnum().equals(n)){
    			return -1;
    		}
    	}
    	return 0;
    }
    
    public PartModel getPartByNum(String n){
    	PartModel a = null;
    	for(int i = 0; i < Inventory.size();i++){
    		if(Inventory.get(i).getPnum().equals(n)){
    			a = Inventory.get(i);
    		}
    	}
    	return a;
    }
    
    public int checkName(String name){
    	PartModel temp;
    	for(int i = 0; i < Inventory.size(); i++){
    		temp = Inventory.get(i);
    		if(temp.getPname() == name){
    			return -1;
    		}
    	}
    	return 0;
    }
    
    public int checkItemInventory(PartModel p, String l){
    	for(int a = 0; a < ItemInventory.size(); a++){
    		if(p == ItemInventory.get(a).getPart() && l == ItemInventory.get(a).getLocation()){
    			return -1;
    		}
    	}
    	return 0;
    }
    
    
    public int addItem(PartModel p, String l, int q){
    	if(checkItemInventory(p,l) == -1){
    		System.out.println("Part/Location Combination Taken");
    		return -1;
    	}
    	if(q <= 0){
    		System.out.println("Invalid Quantity");
    		return -1;
    	}
    	ItemModel item = new ItemModel(p, l, q);
    	item.setId(currentItemId++);
    	ItemInventory.add(item);
    	myDB.addToInventory(item);
    	return 0;
    }
    
    public int addItem(ItemModel i){
    	if(checkItemInventory(i.getPart(), i.getLocation()) == -1){
    		System.out.println("Part/Location Combination Taken");
    		return -1;
    	}
    	if(i.getQuantity() <= 0){
			System.out.println("Invalid Quantity");
			return -1;
		}
    	i.setId(currentItemId++);
    	ItemInventory.add(i);
    	myDB.addToInventory(i);
    	return 0;
    }
    
    public int addPart(String name, String number, String v, String e,  int q){
    	if(number.length() > 20 || name.length() > 255 || v.length() > 20 || e.length() > 50){
			System.out.println("Invalid String Input Size");
			return -1;
		}
		if(number.length() <= 0 || name.length() <= 0){
			System.out.println("Required Fields: Part Name, Part Number, and Quantity");
			return -1;
		}
		if(q <= 0){
			System.out.println("Invalid Quantity");
			return -1;
		}
		if(checkNum(number) == -1){
			System.out.println("Part Number Taken");
			return -1;
		}
    	PartModel d = new PartModel(name, number, v, e);
    	d.setId(currentid++);
    	Inventory.add(d);
    	myDB.addPart(d);
    	
    	return 0;
    }
    
    public int deletePart(PartModel m){
    	
    	for(int i = 0; i < ItemInventory.size(); i++)
    		if(ItemInventory.get(i).getPart() == m){
    			System.out.println("Cannot delete part associated with Item");
    			return -1;
    		}
    	Inventory.remove(m);
    	
    	myDB.deletePart(m);
    	return 0;
    }
    
    public int deleteItem(ItemModel m){
    	if(m.getQuantity() != 0){
    		System.out.println("Quantity must be Zero to Delete Item");
    		return -1;
    	}
    	ItemInventory.remove(m);
    	myDB.deleteInventoryEntry(m);
    	return 0;
    }
    
    public PartModel getPart(int pid){
    	return myDB.getPart(pid);
    }

	public void setInventory(ArrayList<PartModel> inventory) {
		Inventory = inventory;
	}

	public void updatePart(PartModel model) {
		// TODO Auto-generated method stub
		myDB.updatePart(model);
	}
	
	public void updateInventory(ItemModel model){
		System.out.println("updating inventory");
		myDB.updateInventory(model);
	}

	public int checkNum(PartModel partModel) {
		// TODO Auto-generated method stub
		for(int i = 0; i < Inventory.size(); i++){
			PartModel temp = Inventory.get(i);
			if(temp.getPnum().equals(partModel.getPnum()) && partModel!=temp){
				return -1;
			}
		}
		return 0;
	}
	

	
}