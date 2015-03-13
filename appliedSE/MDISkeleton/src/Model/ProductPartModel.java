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
}
