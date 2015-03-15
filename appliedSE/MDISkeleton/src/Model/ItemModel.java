package Model;

import control.InventoryController;

public class ItemModel {
	int id;
	PartModel part;
	String location;
	int quantity;
	int version;
	
	public ItemModel(PartModel p, String l, int q) {
		part = p;
		if(l != null){
			location = l;
		}
		else{
			location = "unknown";
		}
		quantity = q;
		id = 0;
		version = 1;
	}
	
	public ItemModel() {
		// TODO Auto-generated constructor stub
		this.id = -1;
		this.part = new PartModel();
		this.location = "";
		this.quantity = 0;
		version = 1;
	}

	public int editModel(String num, String l, int q, InventoryController iv){
		PartModel a = iv.getPartByNum(num);
		if(iv.checkItemInventory(a,l) == -1){
    		System.out.println("Part/Location Combination Taken");
    		return -1;
    	}
		if(q < 0){
    		System.out.println("Invalid Quantity");
    		return -1;
    	}
		part = a;
		location = l;
		quantity = q;
		return 0;
	}
	
	public PartModel getPart(){
		return part;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public String getLocation(){
		return location;
	}
	
	public int getId(){
		return id;
	}
	
	public void setPart(PartModel p){
		part = p;
	}
	
	public void setQuantity(int q){
		quantity = q;
	}
	
	public void setLocation(String l){
		location = l;
	}
	
	public void setId(int i){
		id = i;
	}
	
	@Override
	public String toString(){
		String s = "";
		s += "id:" + id +"\n";
		s += "location:" + location + "\n";
		s += "quantity:" + quantity + "\n";
		s += "Part:\n" + part.toString();
		return s;
	}
	
	public void VersionIncrease(){
		version++;
	}
	
	public int getVersion(){
		return version;
	}
	
	public void setVersion(int a){
		version = a;
	}
}
