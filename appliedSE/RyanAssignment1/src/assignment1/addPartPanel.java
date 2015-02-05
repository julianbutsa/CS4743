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
	private JTextField nametf;
	private JTextField numbertf;
	private JTextField vendortf;
	private JTextField qtf;

	
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
		nametf = new JTextField("", 255);
		numbertf = new JTextField("", 20);
		vendortf = new JTextField("",255);
		qtf = new JTextField("", 20);
		
		this.add(name);
		this.add(nametf);
		this.add(number);
		this.add(numbertf);
		this.add(vendor);
		this.add(vendortf);
		this.add(quantity);
		this.add(qtf);
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
	
	public void setNumbertf(String s){
		numbertf.setText(s);
	}
	
	public void setVendortf(String s){
		vendortf.setText(s);
	}
	
	public void setQtf(String s){
		qtf.setText(s);
	}
	
	public void registerListener(PartController controller){
		addbutton.addActionListener(controller);
	}
}