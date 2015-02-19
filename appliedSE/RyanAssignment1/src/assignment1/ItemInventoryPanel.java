package assignment1;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ItemInventoryPanel extends JPanel {
	private InventoryModel model;
	private PartView view;
	private ArrayList<ItemModel> items;
	private ArrayList<ItemPanel> itemPanels;
	private JLabel temp;
	private PartModel p;
	
	public ItemInventoryPanel(InventoryModel m, PartView v) {
		this.model = m;
		this.view = v;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.items = model.getItemInventory();
		this.itemPanels = new ArrayList<ItemPanel>();
		Iterator<ItemModel> i = items.iterator();

		
		while(i.hasNext()){
			ItemPanel t = new ItemPanel(i.next(), model, view, this);
			itemPanels.add(t);
		}
		
		for (ItemPanel ip : itemPanels){
			this.add(ip);
		}
		
	}
	
}