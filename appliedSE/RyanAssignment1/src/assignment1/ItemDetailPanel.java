package assignment1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ItemDetailPanel extends JPanel implements ActionListener{
	private ItemModel model;
	private PartView view;
	private ItemInventoryPanel inview;
	private InventoryModel invmod;
	private editItemPanel epanel;
	private JButton ibutton;
	private JButton ebutton;
	private JLabel id;
	private JLabel part;
	private JLabel location;
	private JLabel quantity;
	private JLabel iid;
	private JLabel ipart;
	private JLabel ilocation;
	private JLabel iquantity;

	
	public ItemDetailPanel(ItemModel m, PartView v, ItemInventoryPanel iv, InventoryModel im) {
		this.model = m;
		this.view = v;
		this.inview = iv;
		this.invmod = im;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		part = new JLabel("Part:");
		id = new JLabel("Product ID:");
		location = new JLabel("Location:");
		quantity = new JLabel("Quantity:");
		
		ipart = new JLabel(model.getPart().getPname());
		iid = new JLabel(String.valueOf(model.getId()));
		ilocation = new JLabel(model.getLocation());
		iquantity = new JLabel(String.valueOf(model.getQuantity()));

		ibutton = new JButton("Inventory");
		ibutton.addActionListener(this);
		ibutton.setActionCommand("inventory");
		ebutton = new JButton("Edit");
		ebutton.addActionListener(this);
		ebutton.setActionCommand("edit");
		
		this.add(part);
		this.add(ipart);
		this.add(id);
		this.add(iid);
		this.add(quantity);
		this.add(iquantity);
		this.add(location);
		this.add(ilocation);

		this.add(ibutton);
		this.add(ebutton);


	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getActionCommand()){
			case "inventory":
				view.refreshInventory();
				view.pSet(inview);
				view.remove(this);
				break;
			case "edit":
				epanel = new editItemPanel(model, view, inview, invmod);
				view.add(epanel, BorderLayout.CENTER);
				view.pSet(epanel);
				view.remove(this);
				break;
		}
		
	}
	
	
}