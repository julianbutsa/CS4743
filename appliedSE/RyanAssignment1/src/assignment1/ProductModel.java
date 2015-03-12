package assignment1;

import java.util.ArrayList;

public class ProductModel {
	String prodno;
	String partdesc;
	int id;
	private ArrayList<PartModel> Composition = new ArrayList<PartModel>();
	
	public ProductModel(String desc, String num) {
		prodno = num;
		partdesc = desc;
	}
	
	public void setId(int i){
		this.id = i;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getprodNum(){
		return this.prodno;
	}
	
	public String getDesc(){
		return this.partdesc;
	}
}
