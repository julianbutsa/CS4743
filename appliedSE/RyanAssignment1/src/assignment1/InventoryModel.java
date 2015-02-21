package assignment1;

import java.util.ArrayList;

public class InventoryModel {
	
	//private ArrayList<PartModelObserver> observer = new ArrayList<PartModelObserver>();
	int parts;
	private ArrayList<PartModel> Inventory = new ArrayList<PartModel>();
	private ArrayList<ItemModel> ItemInventory = new ArrayList<ItemModel>();
	private PartModel a, b, c;
	private ItemModel z;
	int currentid = 1;
	int currentItemId = 1;
	
	public InventoryModel() {
		parts = 0;
		a = new PartModel("Part A", "24", "Maker Industries", null);
		b = new PartModel("Part B", "17", null, null);
		c = new PartModel("Part C", "35", "Vendor Industries",null);
		Inventory.add(a);
		a.setId(currentid++);
		Inventory.add(b);
		b.setId(currentid++);
		Inventory.add(c);
		c.setId(currentid++);
		z = new ItemModel(a, "Facility 1 Warehouse 1", 4);
		ItemInventory.add(z);
		z = new ItemModel(b, null, 3);
		ItemInventory.add(z);
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
    	PartModel a = new PartModel("Error","Error","Error","Error");
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
    	return 0;
    }
    
    public int deletePart(PartModel m){
    	for(int i = 0; i < ItemInventory.size(); i++)
    		if(ItemInventory.get(i).getPart() == m){
    			System.out.println("Cannot delete part associated with Item");
    			return -1;
    		}
    	Inventory.remove(m);
    	return 0;
    }
    
    public int deleteItem(ItemModel m){
    	if(m.getQuantity() != 0){
    		System.out.println("Quantity must be Zero to Delete Item");
    		return -1;
    	}
    	ItemInventory.remove(m);
    	return 0;
    }
    
    public PartModel getPart(int n){
    	return Inventory.get(n);
    }
    

	
}