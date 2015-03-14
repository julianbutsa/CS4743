package Model;

import java.util.ArrayList;

public class ProductModel {
	String prodno;
	String partdesc;
	int id;
	private ArrayList<ProductPartModel> Composition = new ArrayList<ProductPartModel>();
	
	public ProductModel(String desc, String num) {
		prodno = num;
		partdesc = desc;

	}
	
	public ProductModel(){
		prodno = "";
		partdesc = "";
	}
	
	public int editModel(String desc, String num){
		if(num.startsWith("A")){
			prodno = num;
			partdesc = desc;
			return 0;
		}
		return -1;
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
	
	public int addPart(ProductPartModel p){
		Composition.add(p);
		return 0;
	}
	

	
	public ArrayList<ProductPartModel> getParts(){
		return Composition;
	}
	
	public ProductPartModel getPartEntry(int i){
		return Composition.get(i);
	}
	
	public int deletePart(ProductPartModel m){
		Composition.remove(m);
		return 0;
	}
	
}
