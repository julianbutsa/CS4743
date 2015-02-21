package assignment1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ItemPanel extends JPanel implements ActionListener{
	private ItemModel model;
	private PartView view;
	private ItemInventoryPanel inview;
	private InventoryModel inventory;
	private ItemDetailPanel dview;
	private JLabel partname;
	private JLabel id;
	private JLabel quantity;
	private JLabel location;
	private JButton delete;
	private JButton detail;
	
	public ItemPanel(ItemModel m, InventoryModel im, PartView v, ItemInventoryPanel iv) {
		this.model = m;
		this.view = v;
		this.inventory = im;
		this.inview = iv;
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		this.partname = new JLabel(m.getPart().getPname());
		this.setLayout(new GridLayout(1,0));
		this.id = new JLabel(String.valueOf(m.getId()));
		this.location = new JLabel(m.getLocation());
		this.quantity = new JLabel(String.valueOf(m.getQuantity()));
		//this.quantity = new JLabel(String.valueOf(m.getQuantity()));
		this.id = new JLabel(String.valueOf(m.getId()));
		this.add(partname);
		this.add(id);
		this.add(location);
		this.add(quantity); 
		
		this.delete = new JButton("Delete");
		delete.addActionListener(this);
		delete.setActionCommand("delete");
		this.detail = new JButton("Detail");
		detail.addActionListener(this);
		detail.setActionCommand("detail");
		
		this.add(delete);
		this.add(detail);
	}

	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getActionCommand()){
			case "delete":
				inventory.deleteItem(model);
				view.refreshInventory();
				view.pSet(inview);
				break;
			case "detail":
				dview = new ItemDetailPanel(model, view, inview, inventory);
				inview.setVisible(false);
				view.add(dview);
				view.pSet(dview);
				break;
		}
		
	}
	
}