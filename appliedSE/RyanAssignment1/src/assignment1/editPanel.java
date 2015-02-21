package assignment1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class editPanel extends JPanel implements ActionListener{
	private PartModel model;
	private PartView view;
	private InventoryPanel inview;
	private InventoryModel invmod;
	private DetailPanel dpanel;
	private JButton ibutton;
	private JLabel name;
	private JLabel number;
	private JLabel vendor;
	private JLabel qunit;
	private JLabel external;
	private JTextField etf;
	private JTextField nametf;
	private JTextField numbertf;
	private JTextField vendortf;
	private JComboBox qunitinput;
	
	
	public editPanel(PartModel m, PartView v, InventoryPanel iv, InventoryModel im) {
		this.model = m;
		this.view = v;
		this.inview = iv;
		this.invmod = im;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		name = new JLabel("Part Name:");
		number = new JLabel("Part Number:");
		vendor = new JLabel("Vendor:");
		qunit = new JLabel("Unit");
		external = new JLabel("External Part #:");
		etf = new JTextField("", 50);
		nametf = new JTextField(model.getPname(), 255);
		numbertf = new JTextField(model.getPnum(), 20);
		vendortf = new JTextField(model.getVendor(),255);
		ibutton = new JButton("Done");
		ibutton.addActionListener(this);
		ibutton.setActionCommand("done");
		
		String[] input = { "Linear Feet", "“Pieces"};
		qunitinput = new JComboBox(input);
		this.add(name);
		this.add(nametf);
		this.add(number);
		this.add(numbertf);
		this.add(vendor);
		this.add(vendortf);
		this.add(external);
		this.add(etf);
		this.add(qunit);
		this.add(qunitinput);
		this.add(ibutton);
		

	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getActionCommand()){
			case "done":
				model.editModel(nametf.getText(), numbertf.getText(), vendortf.getText(), invmod, qunitinput.getSelectedItem().toString());
				model.setExternal(etf.getText());
				dpanel = new DetailPanel(model, view, inview, invmod);
				view.add(dpanel, BorderLayout.CENTER);
				view.pSet(dpanel);
				view.remove(this);
				break;
		}
		
	}
}