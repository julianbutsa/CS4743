package assignment1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class editItemPanel extends JPanel implements ActionListener{
	private ItemModel model;
	private PartView view;
	private ItemInventoryPanel inview;
	private InventoryModel invmod;
	private ItemDetailPanel dpanel;
	private JButton ibutton;
	private JLabel part;
	private JLabel location;
	private JLabel quantity;
	private JTextField ptf;
	private JTextField quantitytf;
	private JComboBox locationtf;
	
	
	public editItemPanel(ItemModel m, PartView v, ItemInventoryPanel iv, InventoryModel im) {
		this.model = m;
		this.view = v;
		this.inview = iv;
		this.invmod = im;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		part = new JLabel("Part Number:");
		location = new JLabel("Part Number:");
		quantity = new JLabel("Vendor:");
		ptf = new JTextField(model.getPart().getPnum(), 255);
		quantitytf = new JTextField(String.valueOf(model.getQuantity()), 20);
		ibutton = new JButton("Done");
		ibutton.addActionListener(this);
		ibutton.setActionCommand("done");
		
		String[] input = {"Facility 1 WareHouse 1", "Facility 1 Warehouse 2", "Facility 2"};
		locationtf = new JComboBox(input);
		this.add(part);
		this.add(ptf);
		this.add(quantity);
		this.add(quantitytf);
		this.add(location);
		this.add(locationtf);
		this.add(ibutton);
		

	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getActionCommand()){
			case "done":
				model.editModel(ptf.getText(), locationtf.getSelectedItem().toString(), Integer.parseInt(quantitytf.getText()), invmod);
				dpanel = new ItemDetailPanel(model, view, inview, invmod);
				view.add(dpanel, BorderLayout.CENTER);
				view.pSet(dpanel);
				view.remove(this);
				break;
		}
		
	}
}