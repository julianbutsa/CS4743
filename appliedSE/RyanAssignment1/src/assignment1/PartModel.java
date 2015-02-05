package assignment1;

import java.util.ArrayList;

public class PartModel {
	
	private ArrayList<PartModelObserver> observer = new ArrayList<PartModelObserver>();
	
	String partno;
	String partname;
	String vendor;
	int quantity;
	int id;
	
	public PartModel(String name, String number, String v, int q) {
		partno = number;
		partname = name;
		if(v != null)
			vendor = v;
		quantity = q;
		id = 0;
	}
	
    public String getPnum(){
    	return partno;
    }
    
    public String getPname(){
    	return partname;
    }
	
    public boolean hasVendor(){
    	if(vendor != null){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    public String getVendor(){
    	return vendor;
    }
    
    public int getQuantity(){
    	return quantity;
    }
    
    public int editModel(String name, String number, String v, int q, InventoryModel model){
    	if(number.length() > 20 || name.length() > 255 || v.length() > 20){
			System.out.println("Invalid String Input Size");
			return -1;
		}
		if(number.length() <= 0 || name.length() <= 0){
			System.out.println("Required Fields: Part Name, Part Number, and Quantity");
			return -1;
		}
		if(q < 0){
			System.out.println("Invalid Quantity");
			return -1;
		}
		if(model.checkName(name) == -1){
			System.out.println("Name Taken");
			return -1;
		}
    	partno = number;
    	partname = name;
    	vendor = v;
    	quantity = q;
    	return 0;
    }
    
    public void setPnum(String s){
    	partno = s;
    }
    
    public void setPname(String s){
    	partname = s;
    }
    
    public void setVendor(String s){
    	vendor = s;
    }
    
    public void setId(int i){
    	id = i;
    }
    
    public int getId(){
    	return id;
    }
    
    public void setQuantity(int s){
    	quantity = s;
    }
	
}