package control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observer;

import userRemote.SessionRemote;
import DBclass.DBQuery;
import Model.*;

public class InventoryController {
	//private ArrayList<PartModelObserver> observer = new ArrayList<PartModelObserver>();
	int parts;
	private ArrayList<PartModel> Inventory;// = new ArrayList<PartModel>();
	private ArrayList<ItemModel> ItemInventory;// = new ArrayList<ItemModel>();
	private ArrayList<ProductModel> ProductList;// = new ArrayList<ProductModel>();
	private ArrayList<ProductPartModel> ProductPartList;
	
	private ArrayList<PartObserver> partObservers;
	private ArrayList<InventoryObserver> invObservers;
	private ArrayList<ProductObserver> productObservers;
	private ArrayList<ProductPartObserver> ppObservers;
	
	int partno;
	int itemno;
	int productno;
	
	
	public DBQuery myDB;
	
	private SessionRemote currentSession;
	
	public InventoryController(){
		this.myDB = new DBQuery();
		//this.currentSession  = new SessionRemote();
		
		this.Inventory = myDB.getParts();
		this.ItemInventory = myDB.getInventory();
		this.ProductList = myDB.getProducts();
		this.ProductPartList = myDB.getProductParts();
		allocateProductParts();
		this.partObservers = new ArrayList<PartObserver>();
		this.productObservers = new ArrayList<ProductObserver>();
		this.ppObservers = new ArrayList<ProductPartObserver>();
		this.invObservers = new ArrayList<InventoryObserver>();
		partno = Inventory.size();
		itemno = ItemInventory.size();
		productno = ProductList.size();
	}
	
	
	public void allocateProductParts(){
		for(int i = 0; i < ProductList.size(); i++){
			for(int l = 0; l < ProductPartList.size(); l++){
				if(ProductPartList.get(l).getProductId() == ProductList.get(i).getId()){
					ProductList.get(i).addPart(ProductPartList.get(l));
				}
			}
		}
	}
	
	public SessionRemote getSession(){
		return currentSession;
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
		m.setId(++partno);
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
    
    public int checkProductList(ProductModel p){
    	for(int a = 0; a < ProductList.size(); a++){
    		if(p.getprodNum() == ProductList.get(a).getprodNum()){
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
    	item.setId(++itemno);
    	ItemInventory.add(item);
    	myDB.addToInventory(item);
    	updateInvObservers(item, 0);
    	return 0;
    }
    
    public int addProduct(ProductModel p){
    	if(checkProductList(p) == -1){
    		System.out.println("Product Number taken");
    		return -1;
    	}
    	p.setId(++productno);
    	ProductList.add(p);
    	myDB.addProduct(p);
    	updateProductObservers(p, 0);
    	return 0;
    }
    
    public int addProductPart(ProductPartModel p){
    	if(p.getQuantity() <= 0){
    		System.out.println("Quantity must be above 0");
    		return -1;
    	}
    	for(int i = 0; i < ProductPartList.size(); i++){
    		if(p.getProductId() == ProductPartList.get(i).getProductId() && p.getPartId() == ProductPartList.get(i).getPartId()){
    			System.out.println("Product-Part combination not unique");
        		return -1;
    		}
    	}
    	ProductPartList.add(p);
    	myDB.addProductPart(p);
    	updateProductPartObservers(p, 0);
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
    
    //delete based off part
    public int deletePart(PartModel m){
    	
    	for(int i = 0; i < ItemInventory.size(); i++)
    		if(ItemInventory.get(i).getPart() == m){
    			System.out.println("Cannot delete part associated with Item");
    			return -1;
    		}
    	for(int i = 0; i < ProductList.size(); i++){
    		ArrayList<ProductPartModel> temp = ProductList.get(i).getParts();
    		for(int l = 0; l < temp.size(); l++){
    			if(temp.get(l).getPartId() == m.getId()){
    				System.out.println("Cannot delete part associated with Product");
        			return -1;
    			}
    		}
    	}
    	Inventory.remove(m);
    	
    	myDB.deletePart(m);
    	
    	updatePartObservers(m, 2);
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
    	for(int i = 0; i < ProductList.size(); i++){
    		ArrayList<ProductPartModel> temp = ProductList.get(i).getParts();
    		for(int l = 0; l < temp.size(); l++){
    			if(temp.get(l).getPartId() == p.getId()){
    				System.out.println("Cannot delete part associated with Product");
        			return -1;
    			}
    		}
    	}
    	updatePartObservers(p, 2);
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

    public int deleteProductPart(ProductPartModel toDelete) {
		myDB.deleteProductPart(toDelete);
		ProductPartList.remove(toDelete);
		updateProductPartObservers(toDelete, 1);
		return 0;
	} 
    
	public int deleteProduct(ProductModel toDelete) {
		myDB.deleteProduct(toDelete);
		ProductList.remove(toDelete);
		updateProductObservers(toDelete, 1);
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
		PartModel temp = myDB.getPart(model.getId());
		if(temp.getVersion() != model.getVersion()){
			System.out.println("Edit error, Versions out of Sync");
		}
		model.VersionIncrease();
		myDB.updatePart(model);
		updatePartObservers(model, 1);
	}
	
	public void updateProduct(ProductModel model){
		ProductModel temp = myDB.getProduct(model.getId());
		if(temp.getVersion() != model.getVersion()){
			System.out.println("Edit error, Versions out of Sync");
		}
		model.VersionIncrease();
		myDB.updateProduct(model);
		updateProductObservers(model, 1);
		ItemModel m = new ItemModel(); 
		updateInvObservers(m, 1);
	}
	
	public void updateProductPart(ProductPartModel model){
		ProductPartModel temp = myDB.getProductPart(model.getProductId(), model.getPartId());
		if(temp.getVersion() != model.getVersion()){
			System.out.println("Edit error, Versions out of Sync");
		}
		model.VersionIncrease();
		myDB.updateProductPart(model);
	    updateProductPartObservers(model, 1);
	}
	
	
	public void updateInventory(ItemModel model){
		//System.out.println("updating inventory");
		
		ItemModel temp = myDB.getItem(model.getId());
		if(temp.getVersion() != model.getVersion()){
			System.out.println("Edit error, Versions out of Sync");
		}
		model.VersionIncrease();
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
	
	public void registerProductObserver(ProductObserver o){
		this.productObservers.add(o);
	}
	
	public void registerProductPartObserver(ProductPartObserver o){
		this.ppObservers.add(o);
	}
	
	public void registerInvObserver(InventoryObserver o){
		this.invObservers.add(o);
	}
	
	public int checkPermission(int action, int permission){
		/*This is assuming Permissions 1 = Inventory Manager, 2 = Production Manager, and 3 = Admin */
		
		if(action == 1 || action == 2 || action == 3 || action == 4){
			//View Product Templates
			//Add/Edit Product Templates Permissions
			//Delete Product Templates Permissions
			//Create Products
			if(permission == 2 || permission == 3){
				return 0;
			}else{
				return -1;
			}
		}
		if(action == 6 || action == 8){
			//View Inventory Items
			//View Parts
			return 0;
		}
		if(action == 6 || action == 8){
			//Add/Edit Inventory Items
			//Add/Edit Parts
			if(permission == 1 || permission == 3){
				return 0;
			}else{
				return -1;
			}
		}
		if(action == 9 || action == 10){
			//Delete Inventory
			//Delete Parts
			if(permission == 3){
				return 0;
			}else{
				return -1;
			}
		}
		
		
		return -1;
	}
	
	
	public void updatePartObservers(PartModel m, int action){
		for(int i=0; i < partObservers.size();i++){
			partObservers.get(i).updateObserver(m,action);
		}
	}
	public void updateProductObservers(ProductModel m, int action){
		for(int i=0; i < productObservers.size();i++){
			productObservers.get(i).updateObserver(m,action);
		}
	}
	public void updateProductPartObservers(ProductPartModel m, int action){
		for(int i=0; i < ppObservers.size();i++){
			ppObservers.get(i).updateObserver(m,action);
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

	

	public void setSession(SessionRemote s) {
		currentSession = s;
		
	}


	public int addproductotinv(int productIndex, String s) {
		// TODO Auto-generated method stub

		ProductModel thisPart = ProductList.get(productIndex);
		for(Iterator<ItemModel> i = ItemInventory.iterator(); i.hasNext();){
			ItemModel p = i.next();
			if(p.getTypeFlag() == 1){
				if(p.getLocation().equals(s) && p.getProduct().getprodNum().equals(thisPart.getprodNum())){
					p.setQuantity(p.getQuantity()+1);
					return 0;
				}
			}
		}
		
		
		//check inventory
		ArrayList a = thisPart.getParts();
		for(Iterator<ProductPartModel> i = a.iterator();i.hasNext();){
			ProductPartModel part = i.next();
			for(Iterator<ItemModel> ii = ItemInventory.iterator(); ii.hasNext();){
				ItemModel item = ii.next();
				if(item.typeFlag == 0){
					if(part.getPartId() == item.getPart().getId()){
						if(item.getQuantity() < part.getQuantity()){
							return -1;
						}
					}
				}
			}
		}
		
		//adjust Inventory quantities.
		for(Iterator<ProductPartModel> i = a.iterator();i.hasNext();){
			ProductPartModel part = i.next();
			for(Iterator<ItemModel> ii = ItemInventory.iterator(); ii.hasNext();){
				ItemModel item = ii.next();
				if(item.typeFlag == 0){
					if(part.getPartId() == item.getPart().getId()){
						item.setQuantity(item.getQuantity() - part.getQuantity());
						myDB.updateInventory(item);
					}
				}
			}
		}
		ItemModel newModel = new ItemModel(thisPart, s, 1);
		newModel.setTypeFlag(1);
		newModel.setVersion(1);
		myDB.addToInventory(newModel);
		
		//update
		for(Iterator<ItemModel> ii = ItemInventory.iterator(); ii.hasNext();){
			ItemModel item = ii.next();
			updateInvObservers(item, 1);
		}
		
		return 0;
	}






}
