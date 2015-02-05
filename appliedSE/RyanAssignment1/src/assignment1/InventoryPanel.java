package assignment1;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

public class InventoryPanel extends JPanel {
	private InventoryModel model;
	private PartView view;
	private ArrayList<PartModel> parts;
	private ArrayList<PartPanel> partPanels;
	private JLabel temp;
	private PartModel p;
	
	public InventoryPanel(InventoryModel m, PartView v) {
		this.model = m;
		this.view = v;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.parts = model.getInventory();
		this.partPanels = new ArrayList<PartPanel>();
		Iterator<PartModel> i = parts.iterator();

		
		while(i.hasNext()){
			PartPanel t = new PartPanel(i.next(), model, view, this);
			partPanels.add(t);
		}
		
		for (PartPanel ip : partPanels){
			this.add(ip);
		}
		
	}
	
}
