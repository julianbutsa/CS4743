package assignment1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class DetailPanel extends JPanel implements ActionListener{
	private PartModel model;
	private PartView view;
	private InventoryPanel inview;
	private InventoryModel invmod;
	private editPanel epanel;
	private JButton ibutton;
	private JButton ebutton;
	private JLabel id;
	private JLabel name;
	private JLabel number;
	private JLabel vendor;
	private JLabel pid;
	private JLabel pname;
	private JLabel pnumber;
	private JLabel pvendor;
	private JLabel qunit;
	private JLabel external;
	private JLabel exp;
	
	public DetailPanel(PartModel m, PartView v, InventoryPanel iv, InventoryModel im) {
		this.model = m;
		this.view = v;
		this.inview = iv;
		this.invmod = im;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		name = new JLabel("Part Name:");
		number = new JLabel("Part Number:");
		vendor = new JLabel("Vendor:");
		id = new JLabel("Product ID:");
		external = new JLabel("External Part #:");
		exp = new JLabel(model.getExternal());
		pname = new JLabel(model.getPname());
		pnumber = new JLabel(model.getPnum());
		pvendor = new JLabel(model.getVendor());
		qunit = new JLabel(model.getQunit());
		//pquantity = new JLabel(String.valueOf(model.getQuantity()));
		pid = new JLabel(String.valueOf(model.getId()));
		ibutton = new JButton("Inventory");
		ibutton.addActionListener(this);
		ibutton.setActionCommand("inventory");
		ebutton = new JButton("Edit");
		ebutton.addActionListener(this);
		ebutton.setActionCommand("edit");
		
		this.add(name);
		this.add(pname);
		this.add(number);
		this.add(pnumber);
		if(model.hasVendor()){
			this.add(vendor);
			this.add(pvendor);
		}
		//this.add(quantity);
		//this.add(pquantity);
		this.add(id);
		this.add(pid);
		if(model.hasExternal()){
			this.add(external);
			this.add(exp);
		}
		this.add(qunit);
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
				epanel = new editPanel(model, view, inview, invmod);
				view.add(epanel, BorderLayout.CENTER);
				view.pSet(epanel);
				view.remove(this);
				break;
		}
		
	}
	
	
}
