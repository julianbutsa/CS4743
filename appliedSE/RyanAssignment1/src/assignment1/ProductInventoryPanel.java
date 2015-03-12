package assignment1;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ProductInventoryPanel extends JPanel {
	private InventoryModel model;
	private PartView view;
	private ArrayList<ProductModel> items;
	private ArrayList<ProductPanel> productPanels;
	private JLabel temp;
	private PartModel p;
	
	public ProductInventoryPanel(InventoryModel m, PartView v) {
		this.model = m;
		this.view = v;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.items = model.getProductInventory();
		this.productPanels = new ArrayList<ProductPanel>();
		Iterator<ProductModel> i = items.iterator();

		
		while(i.hasNext()){
			ProductPanel t = new ProductPanel(i.next(), model, view, this);
			productPanels.add(t);
		}
		
		for (ProductPanel ip : productPanels){
			this.add(ip);
		}
		
	}
	
}