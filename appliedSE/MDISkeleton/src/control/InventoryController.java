package control;

import java.util.ArrayList;
import java.util.Observer;

import DBclass.DBQuery;
import Model.ItemModel;
import Model.PartModel;
import Model.ProductModel;

public class InventoryController {
	//private ArrayList<PartModelObserver> observer = new ArrayList<PartModelObserver>();
	int parts;
	private ArrayList<PartModel> Inventory;// = new ArrayList<PartModel>();
	private ArrayList<ItemModel> ItemInventory;// = new ArrayList<ItemModel>();
	private ArrayList<ProductModel> ProductList;// = new ArrayList<ProductModel>();
	
	private ArrayList<PartObserver> partObservers;
	private ArrayList<InventoryObserver> invObservers;
	
	
	
	public DBQuery myDB;
	
	
	
	public InventoryController(){
		this.myDB = new DBQuery();
		this.Inventory = myDB.getParts();
		this.ItemInventory = myDB.getInventory();
		this.ProductList = myDB.getProducts();
		this.partObservers = new ArrayList<PartObserver>();
		this.invObservers = new ArrayList<InventoryObserver>();
	}
	
	

	

	public ArrayList<PartModel> getInventory(){
		return Inventory;
	}
	
	public ArrayList<ItemModel> getItemInventory(){
		return ItemInventory;
	}
	
	public ArrayList<ProductModel> getProducts(){
		return ProductList;
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
    	Inventory.add(m);
		myDB.addPart(m);
		
		this.updatePartObservers(m, 2);
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
    	ItemInventory.add(item);
    	myDB.addToInventory(item);
    	updateInvObservers(item, 0);
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
    	ItemInventory.add(i);
    	myDB.addToInventory(i);
    	updateInvObservers(i, 0);
    	return 0;
    }
    
    /*
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
    	Inventory.add(d);
    	myDB.addPart(d);
    	
    	
    	this.updatePartObservers(d, 2);
    	return 0;
    }
    */
    //delete based off part
    public int deletePart(PartModel m){
    	
    	for(int i = 0; i < ItemInventory.size(); i++)
    		if(ItemInventory.get(i).getPart() == m){
    			System.out.println("Cannot delete part associated with Item");
    			return -1;
    		}
    	Inventory.remove(m);
    	
    	myDB.deletePart(m);
    	
    	updatePartObservers(m, 0);
    	return 0;
    }
    
    //delete based off index
    public int deletePart(int index){
    	PartModel p = Inventory.get(index);
    	for(int i =0; i < ItemInventory.size(); i++){
    		if(ItemInventory.get(i).getPart() == p){
    			System.out.println("Cannot delete part associated with Item");
    			return -1;
    		}
    	}
    	updatePartObservers(p, 0);
    	return 0;
    }
    
    
    
    public int deleteItem(ItemModel m){
    	if(m.getQuantity() != 0){
    		System.out.println("Quantity must be Zero to Delete Item");
    		return -1;
    	}
    	myDB.deleteInventoryEntry(m);
    	ItemInventory.remove(m);
    	updateInvObservers(m, 2);
    	return 0;
    }

	public int deleteProduct(ProductModel toDelete) {
		// TODO Auto-generated method stub
		return 0;
	} 
    public PartModel getPart(int pid){
    	return Inventory.get(pid);
    }

	public void setInventory(ArrayList<PartModel> inventory) {
		Inventory = inventory;
	}

	public void updatePart(PartModel model) {
		// TODO Auto-generated method stub
		myDB.updatePart(model);
		updatePartObservers(model, 1);
	}
	
	
	public void updateInventory(ItemModel model){
		//System.out.println("updating inventory");
		myDB.updateInventory(model);
		updateInvObservers(model, 1);
	}

	public int checkNum(PartModel partModel) {
		// TODO Auto-generated method stub
		for(int i = 0; i < Inventory.size(); i++){
			PartModel temp = Inventory.get(i);
			if(temp.getPnum().equals(partModel.getPnum()) && !partModel.equals(temp)){
				return -1;
			}
		}
		return 0;
	}
	
	
	public void registerPartObserver(PartObserver o){
		this.partObservers.add(o);
	}
	
	public void registerInvObserver(InventoryObserver o){
		this.invObservers.add(o);
	}
	
	
	public void updatePartObservers(PartModel m, int action){
		for(int i=0; i < partObservers.size();i++){
			partObservers.get(i).updateObserver(m,action);
		}
	}
	public void updateInvObservers(ItemModel imodel, int action){
		for(int i =0; i < invObservers.size(); i++){
			invObservers.get(i).updateObserver(imodel, action);
		}
	}




	public ArrayList<String> getLocations(){
		return myDB.getLocations();
	}

	public ItemModel getInventoryEntry(int selectedRow) {
		// TODO Auto-generated method stub
		return ItemInventory.get(selectedRow);
	}





	public ProductModel getProductEntry(int selectedRow) {
		// TODO Auto-generated method stub
		return ProductList.get(selectedRow);
	}






}
