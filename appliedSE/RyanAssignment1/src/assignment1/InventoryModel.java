package assignment1;

import java.util.ArrayList;

public class InventoryModel {
	
	//private ArrayList<PartModelObserver> observer = new ArrayList<PartModelObserver>();
	int parts;
	private ArrayList<PartModel> Inventory = new ArrayList<PartModel>();
	private PartModel a, b, c;
	int currentid = 1;
	
	public InventoryModel() {
		parts = 0;
		a = new PartModel("Part A", "24", "Maker Industries", 4);
		b = new PartModel("Part B", "17", null, 3);
		c = new PartModel("Part C", "35", "Vendor Industries", 5);
		Inventory.add(a);
		a.setId(currentid++);
		Inventory.add(b);
		b.setId(currentid++);
		Inventory.add(c);
		c.setId(currentid++);
	}
	
	public ArrayList<PartModel> getInventory(){
		return Inventory;
	}
	
    public int getSize(){
    	return Inventory.size();
    }
    
    public int addPart(PartModel m){
    	String name = m.getPname();
    	String number = m.getPnum();
    	String v = m.getVendor();
    	int q = m.getQuantity();
    	if(number.length() > 20 || name.length() > 255 || v.length() > 20){
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
		if(checkName(name) == -1){
			System.out.println("Name Taken");
			return -1;
		}
		m.setId(currentid++);
    	Inventory.add(m);
    	return 0;
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
    
    public int addPart(String name, String number, String v, int q){
    	if(number.length() > 20 || name.length() > 255 || v.length() > 20){
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
		if(checkName(name) == -1){
			System.out.println("Name Taken");
			return -1;
		}
    	PartModel d = new PartModel(name, number, v, q);
    	d.setId(currentid++);
    	Inventory.add(d);
    	return 0;
    }
    
    public void deletePart(PartModel m){
    	Inventory.remove(m);
    }
    
    public PartModel getPart(int n){
    	return Inventory.get(n);
    }
    

	
}