package Model;

import control.InventoryController;


public class PartModel {
	
	private String partno;
	private String partname;
	private String vendor;
	private int id;
	private String external;
	private String qunit;
	private int version;

	public PartModel(String name, String number, String v, String e , String u) {
		partno = number;
		partname = name;
		if(v != null)
			vendor = v;
		if(e != null)
			external = e;
		id = 0;
		this.qunit = u;
		version = 1;
	}
	
	public PartModel(String name, String number, String v, String e) {
		partno = number;
		partname = name;
		if(v != null)
			vendor = v;
		if(e != null)
			external = e;
		id = 0;
		this.qunit = "Unknown";
		version = 1;
	}

	
    public PartModel() {
		// TODO Auto-generated constructor stub
    	this.partno="";
    	this.partname="";
    	this.vendor="";
    	this.id = -1;
    	this.external="";
    	this.qunit="";
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
    
    /*public int getQuantity(){
    	return quantity;
    }*/
    
    
    //TODO add external number
    public int editModel(String name, String number, String e, String v, InventoryController model, String unit){
    	if(number.length() > 20 || name.length() > 255 || v.length() > 20){
			System.out.println("Invalid String Input Size");
			return -1;
		}
		if(number.length() <= 0 || name.length() <= 0){
			System.out.println("Required Fields: Part Name, Part Number");
			return -1;
		}
		if(model.checkNum(this) == -1 ){
			System.out.println("Part Number Taken");
			return -1;
		}
		if(unit.equals("Unknown")){
			System.out.println("Don't pick unkown");
			return -1;
		}
		
		if(number.startsWith("P")){
			partno = number;
			partname = name;
			vendor = v;
    		qunit = unit;
    		external = e;
    		return 0;
		}
    	
    	return -1;
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
    
    /*public void setQuantity(int s){
    	quantity = s;
    }*/

	public String getQunit() {
		return qunit;
	}

	public void setQunit(String qunit) {
		this.qunit = qunit;
	}
	
	@Override
	public String toString(){
		String s = "";
		s = s+"pid : " + id + "\n";
		s = s+"partno : " + partno + "\n";
		s = s+"partname : " + partname + "\n";
		s = s+"vendor : " + vendor + "\n";
		s = s+"external : " + external + "\n";
		s = s+"qunit : " + qunit + "\n";
		return  s;
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