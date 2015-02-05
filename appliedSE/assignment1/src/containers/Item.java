package containers;

/*
 * Item class
 * 
 * We'll use this class to hold information about a particular item.
 * These will be constructed on execution by retrieving data from somewhere
 * 
 */
public class Item {

	private int partNumber;
	private String partName;
	private String vendor;
	private int quantity;


	
	/*
	 * Constructor
	 */
	
	public Item( int pno, String pname, String vendor, int q){
		this.partNumber = pno;
		this.partName = pname;
		this.vendor = vendor;
		this.quantity = q;
	}
	
	/*
	 * Generic constructor. Makes a non-item
	 */
	public Item(){
		this.partNumber = 0;
		this.partName = "";
		this.vendor = "";
		this.quantity = 0;
	}
	
	
	
	
	
	/*
	 * Getters and Setters
	 */
	public int getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(int partNumber) {
		this.partNumber = partNumber;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

	
}
