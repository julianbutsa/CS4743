package assignment1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PartPanel extends JPanel implements ActionListener{
	private PartModel model;
	private PartView view;
	private InventoryPanel inview;
	private InventoryModel inventory;
	private DetailPanel dview;
	private JLabel partname;
	private JLabel id;
	private JLabel partnumber;
	private JLabel vendor;
	private JLabel quantity;
	private JLabel qunit;
	private JLabel location;
	private JButton delete;
	private JButton detail;
	
	public PartPanel(PartModel m, InventoryModel im, PartView v, InventoryPanel iv) {
		this.model = m;
		this.view = v;
		this.inventory = im;
		this.inview = iv;
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		this.partname = new JLabel(m.getPname());
		this.setLayout(new GridLayout(1,0));
		this.partnumber = new JLabel(m.getPnum());
		if(m.hasVendor()){
		   this.vendor = new JLabel(m.getVendor());
		}else{
			this.vendor = new JLabel("N/A");
		}
		this.qunit = new JLabel(m.getQunit());
		this.quantity = new JLabel(String.valueOf(m.getQuantity()));
		this.id = new JLabel(String.valueOf(m.getId()));
		this.location = new JLabel(m.getLocation());
		this.add(partname);
		this.add(partnumber);
		this.add(vendor);


		
		this.add(quantity);
		this.add(qunit);
		this.add(id);
		this.add(location);
		
		this.delete = new JButton("Delete");
		delete.addActionListener(this);
		delete.setActionCommand("delete");
		this.detail = new JButton("Detail");
		detail.addActionListener(this);
		detail.setActionCommand("detail");
		
		this.add(delete);
		this.add(detail);
	}
	
	public PartPanel() {
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		this.partname = new JLabel("Demo Part");
		this.setLayout(new GridLayout(1,0));
		this.partnumber = new JLabel("Demo Num");
		this.vendor = new JLabel("Demo Ven");
		this.quantity = new JLabel("2");
		this.add(partname);
		this.add(partnumber);
		this.add(vendor);
		this.add(quantity);
		this.add(qunit);
		
		delete = new JButton("Delete");
		delete.addActionListener(this);
		delete.setActionCommand("delete");
		detail = new JButton("Detail");
		detail.addActionListener(this);
		detail.setActionCommand("detail");
		
		this.add(delete);
		this.add(detail);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getActionCommand()){
			case "delete":
				inventory.deletePart(model);
				view.refreshInventory();
				view.pSet(inview);
				break;
			case "detail":
				dview = new DetailPanel(model, view, inview, inventory);
				inview.setVisible(false);
				view.add(dview);
				view.pSet(dview);
				break;
		}
		
	}
	
}
