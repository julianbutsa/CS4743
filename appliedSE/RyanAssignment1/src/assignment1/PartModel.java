package assignment1;

import java.util.ArrayList;

public class PartModel {
	
	private ArrayList<PartModelObserver> observer = new ArrayList<PartModelObserver>();
	
	String partno;
	String partname;
	String vendor;
	int quantity;
	int id;
	String external;
	String qunit;

	public PartModel(String name, String number, String v, String e, int q) {
		partno = number;
		partname = name;
		if(v != null)
			vendor = v;
		if(e != null)
			external = e;
		quantity = q;
		id = 0;
		this.qunit = "Unknown";
	}
	
    public String getPnum(){
    	return partno;
    }
    
    public void setExternal(String s){
    	external = s;
    }
    
    
    public boolean hasExternal(){
    	if(external != null){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    public String getExternal(){
    	return external;
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
    
    public int editModel(String name, String number, String v, int q, InventoryModel model, String unit){
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
		if(unit.equals("Unknown")){
			System.out.println("don't pick unkown");
			return -1;
		}
    	partno = number;
    	partname = name;
    	vendor = v;
    	quantity = q;
    	qunit = unit;
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

	public String getQunit() {
		return qunit;
	}

	public void setQunit(String qunit) {
		this.qunit = qunit;
	}
    
    
	
}