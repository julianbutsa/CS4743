package assignment1;

public class ItemModel {
	int id;
	PartModel part;
	String location;
	int quantity;
	
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
	}
	
	public int editModel(String num, String l, int q, InventoryModel iv){
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
}
