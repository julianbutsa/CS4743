package Model;

import java.util.ArrayList;

public class ProductPartModel {
	int ProductId;
	int PartId;
	int Quantity;
	
	public ProductPartModel(int prodid, int partid, int q){
		ProductId = prodid;
		PartId = partid;
		Quantity = q;
	}
	
	public ProductPartModel(){
		ProductId = 0;
		PartId = 0;
		Quantity = 0;
	}
	
	public int editModel(int prodid, int partid, int q){
		if(q <= 0){
			return -1;
		}
		ProductId = prodid;
		PartId = partid;
		Quantity = q;
		return 0;
	}
	
	public int getPartId(){
		return PartId;
	}
	
	public int getProductId(){
		return ProductId;
	}
	
	public int getQuantity(){
		return Quantity;
	}
}
