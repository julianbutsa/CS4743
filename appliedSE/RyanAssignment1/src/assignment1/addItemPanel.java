package assignment1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Iterator;

public class addItemPanel extends JPanel implements ActionListener{
	private InventoryModel model;
	private PartView view;
	private JButton addbutton;
	private JLabel num;
	private JLabel quantity;
	private JLabel location;
	private JTextField numtf;
	private JTextField quantitytf;
	private JComboBox locationtf;
	private JComboBox partf;

	
	public addItemPanel(InventoryModel m, PartView v) {
		this.model = m;
		this.view = v;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		addbutton = new JButton("Add");
		addbutton.addActionListener(this);
		addbutton.setActionCommand("additem");
		
		num = new JLabel("Part Number:");
		quantity = new JLabel("Quantity:");
		location = new JLabel("Location:");
		numtf = new JTextField("", 255);
		quantitytf = new JTextField("",255);
		
		ArrayList<String> l = m.myDB.getLocations();
		String[] locations = new String[l.size()];
		for(int i = 0; i < l.size(); i++){
			locations[i] = l.get(i);
		}
		locationtf = new JComboBox(locations);
		
		ArrayList<PartModel> p = m.getInventory();
		String[] parts = new String[p.size()];
		for(int i = 0; i < p.size(); i++){
			parts[i] = p.get(i).getPnum();
		}
		partf = new JComboBox(parts); 
		this.add(num);
		//this.add(numtf);
		this.add(partf);
		this.add(quantity);
		this.add(quantitytf);
		this.add(location);
		this.add(locationtf);
		this.add(addbutton);

		
	}
	
	
	
	
	
	public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
		switch(arg0.getActionCommand()){
		case "additem":
		PartModel a = model.getPartByNum(partf.getSelectedItem().toString());
		if(a != null){
			ItemModel m = new ItemModel(a, locationtf.getSelectedItem().toString(), Integer.parseInt(quantitytf.getText()));
			int z = model.addItem(m);
			if(z != 0){
				numtf.setText("Error Detected");
				quantitytf.setText("Error Detected");
			}else{
				numtf.setText("");
				quantitytf.setText("");
			}
		}else{
			numtf.setText("Error Detected");
			quantitytf.setText("Error Detected");
		}
		
		view.refreshInventory();
		view.repaint();
		break;
		}
	}
}