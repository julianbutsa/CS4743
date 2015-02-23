package assignment1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
	private JComboBox ptf;
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
		quantity = new JLabel("Quantity:");
		quantitytf = new JTextField(String.valueOf(model.getQuantity()), 20);
		ibutton = new JButton("Done");
		ibutton.addActionListener(this);
		ibutton.setActionCommand("done");
		
		ArrayList<String> l = invmod.myDB.getLocations();
		String[] locations = new String[l.size()];
		for(int i = 0; i < l.size(); i++){
			locations[i] = l.get(i);
		}
		locationtf = new JComboBox(locations);
		for(int i = 0; i < locationtf.getItemCount(); i++){
			if(locationtf.getItemAt(i).toString().equals(m.getLocation())){
				locationtf.setSelectedIndex(i);
			}
		}
		
		
		ArrayList<PartModel> p = invmod.getInventory();
		String[] parts = new String[p.size()];
		for(int i = 0; i < p.size(); i++){
			parts[i] = p.get(i).getPnum();
		}
		ptf = new JComboBox(parts); 
		for(int i = 0; i < ptf.getItemCount(); i++){
			if(ptf.getItemAt(i).toString().equals(m.getPart().getPnum())){
				ptf.setSelectedIndex(i);
			}
		}
		
		//locationtf = new JComboBox(input);
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
				model.editModel(ptf.getSelectedItem().toString(), locationtf.getSelectedItem().toString(), Integer.parseInt(quantitytf.getText()), invmod);
				dpanel = new ItemDetailPanel(model, view, inview, invmod);
				view.add(dpanel, BorderLayout.CENTER);
				view.pSet(dpanel);
				view.remove(this);
				break;
		}
		
	}
}