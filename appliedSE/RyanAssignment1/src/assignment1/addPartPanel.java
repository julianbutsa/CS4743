package assignment1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Iterator;

public class addPartPanel extends JPanel{
	private InventoryModel model;
	private PartView view;
	private JButton addbutton;
	private JLabel name;
	private JLabel number;
	private JLabel vendor;
	private JLabel quantity;
	private JLabel qunitLabel;
	private JLabel locationLabel;
	private JLabel external;
	private JTextField nametf;
	private JTextField numbertf;
	private JTextField vendortf;
	private JTextField qtf;
	private JComboBox<String> qunit;
	private JComboBox<String> location;
	private JTextField etf;
	
	public addPartPanel(InventoryModel m, PartView v) {
		this.model = m;
		this.view = v;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		addbutton = new JButton("Add");
		addbutton.setActionCommand("add");
		
		name = new JLabel("Part Name:");
		number = new JLabel("Part Number:");
		vendor = new JLabel("Vendor:");
		quantity = new JLabel("Quantity:");
		qunitLabel = new JLabel("Units");
		locationLabel = new JLabel("Location");
		external = new JLabel("External Part #:");
		nametf = new JTextField("", 255);
		numbertf = new JTextField("", 20);
		vendortf = new JTextField("",255);
		qtf = new JTextField("", 20);
		etf = new JTextField("", 50);
		String[] units = {"Unknown", "Linear Feet", "Pieces"};
		qunit = new JComboBox<String>(units);
		String[] local = { "Facility 1 Warehouse 1", "Facility 1 Warehouse 2", "Facility 2",
				"Unknown"} ;
		location = new JComboBox<String>(local);
		this.add(name);
		this.add(nametf);
		this.add(number);
		this.add(numbertf);
		this.add(vendor);
		this.add(vendortf);
		this.add(quantity);
		this.add(qtf);
		this.add(external);
		this.add(etf);
		this.add(qunitLabel);
		this.add(qunit);
		this.add(locationLabel);
		this.add(location);
		this.add(addbutton);

		
	}
	
	public String getNametf(){
		return nametf.getText();
	}
	
	public String getNumbertf(){
		return numbertf.getText();
	}
	public String getVendortf(){
		return vendortf.getText();
	}
	
	public int getQtf(){
		return Integer.parseInt(qtf.getText());
	}
	
	public void setNametf(String s){
		nametf.setText(s);
	}
	
	public void setEtf(String s){
		etf.setText(s);
	}
	
	public String getEtf(){
		return etf.getText();
	}
	
	public void setNumbertf(String s){
		numbertf.setText(s);
	}
	
	public void setVendortf(String s){
		vendortf.setText(s);
	}
	
	public void setQtf(String s){
		qtf.setText(s);
	}
	
	public String getQUnit(){
		return this.qunit.getSelectedItem().toString();
	}
	
	public String getLocal(){
		return this.location.getSelectedItem().toString();
	}
	public void registerListener(PartController controller){
		addbutton.addActionListener(controller);
	}


}