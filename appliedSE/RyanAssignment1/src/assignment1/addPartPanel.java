package assignment1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Iterator;

public class addPartPanel extends JPanel implements ActionListener{
	private InventoryModel model;
	private PartView view;
	private JButton addbutton;
	private JLabel name;
	private JLabel number;
	private JLabel vendor;
	private JLabel qunitLabel;
	private JLabel external;
	private JTextField nametf;
	private JTextField numbertf;
	private JTextField vendortf;
	private JTextField etf;
	private JComboBox qunit;

	
	public addPartPanel(InventoryModel m, PartView v) {
		this.model = m;
		this.view = v;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		addbutton = new JButton("Add");
		addbutton.addActionListener(this);
		addbutton.setActionCommand("add");
		
		
		name = new JLabel("Part Name:");
		number = new JLabel("Part Number:");
		vendor = new JLabel("Vendor:");
		qunitLabel = new JLabel("Units:");
		external = new JLabel("External Part #:");
		nametf = new JTextField("", 255);
		numbertf = new JTextField("", 20);
		vendortf = new JTextField("",255);
		etf = new JTextField("", 50);
		String[] units = {"Unknown", "Linear Feet", "Pieces"};
		qunit = new JComboBox(units);
		
		this.add(name);
		this.add(nametf);
		this.add(number);
		this.add(numbertf);
		this.add(vendor);
		this.add(vendortf);
		this.add(external);
		this.add(etf);
		this.add(qunitLabel);
		this.add(qunit);
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
	
	
	public String getQUnit(){
		return this.qunit.getSelectedItem().toString();
	}
	/*
	public void registerListener(PartController controller){
		addbutton.addActionListener(controller);
	}
*/
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getActionCommand()){
			case "add":
				//addPartPanel add = view.getAddPartPanel();
				PartModel m = new PartModel(nametf.getText(), numbertf.getText(), vendortf.getText(),etf.getText());
				m.setQunit(qunit.getSelectedItem().toString());
				int a = model.addPart(m);
				if(a != 0){
					setEtf("Error Detected");
					setNametf("Error Detected");
					setNumbertf("Error Detected");
					setVendortf("Error Detected");
				}else{
					setNametf("");
					setNumbertf("");
					setVendortf("");
					setEtf("");
				}
				view.refreshInventory();
				view.repaint();
				break;
		}
	}
	


}