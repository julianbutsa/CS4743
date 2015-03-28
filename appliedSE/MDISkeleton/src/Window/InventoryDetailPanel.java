package Window;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import control.InventoryObserver;
import Model.ItemModel;
import Model.PartModel;

public class InventoryDetailPanel extends ChildPanel implements ActionListener, InventoryObserver{

	private ItemModel item;
	
	private JLabel PartId;
	private JLabel Location;
	private JLabel Quantity;
	private JLabel PartName;
	
	private JComboBox PartIdField;
	private JComboBox LocationField;
	private JTextField QuantityField;
	private JLabel PartNameField;
	
	private JButton deleteButton;
	private JButton editButton;
	
	private String[] input;
	private String[] locArray;
	public InventoryDetailPanel(win w, ItemModel i, int action){
		super(w);
		master.getController().registerInvObserver(this);
		contentPanel.setLayout(new GridLayout(5,2));
		this.setPreferredSize(new Dimension(300,100));
		this.item = i;
		this.PartId = new JLabel("Part Num");
		this.PartName = new JLabel("Part Name");
		this.Location = new JLabel("Location");
		this.Quantity = new JLabel("Quantity");
		
		if(item.getTypeFlag() == 0){
			this.PartNameField = new JLabel(i.getPart().getPname());
		}else{
			this.PartNameField = new JLabel("Product");
		}
		
		this.QuantityField = new JTextField(Integer.toString(item.getQuantity()));
		
		ArrayList<String> loc = master.getController().getLocations();
		locArray = new String[loc.size()];
		for(int j =0 ; j < loc.size(); j++){
			locArray[j] = loc.get(j);
		}
		LocationField = new JComboBox(locArray);
		
		ArrayList<PartModel> parts = master.getController().getInventory();
		input = new String[parts.size()];
		for(int j = 0 ; j < parts.size(); j++){
			input[j] = parts.get(j).getPnum();
		}
	
		PartIdField = new JComboBox(input);
		PartIdField.addActionListener(this);
		


		contentPanel.add(PartId);
		contentPanel.add(PartIdField);
		contentPanel.add(Location);
		contentPanel.add(LocationField);
		contentPanel.add(Quantity);
		contentPanel.add(QuantityField);
		//action == 1, update menu.
		if(action == 1){
			contentPanel.add(PartName);
			contentPanel.add(PartNameField);
			
			this.editButton = new JButton("Update");
			this.editButton.addActionListener(this);
			this.editButton.setActionCommand("update");
			contentPanel.add(editButton);
			
			this.deleteButton = new JButton("Delete");
			this.deleteButton.addActionListener(this);
			this.deleteButton.setActionCommand("delete");
			contentPanel.add(deleteButton);
			
			//set jcombobox to the right part index
			if(item.getTypeFlag() == 0 ){
				for(int j =0; j < parts.size(); j++){
					if(item.getPart().getPnum().equals(input[j])){
						PartIdField.setSelectedIndex(j);
					}
				}
			}else{
				//set this up for products
			}
			
			for(int j =0; j < input.length; j++){
				if(item.getLocation().equals(locArray[j])){
					LocationField.setSelectedIndex(j);
				}
			}
		}
		//action == 0, add menu.
		else if(action == 0){
			this.editButton = new JButton("Add");
			this.editButton.addActionListener(this);
			this.editButton .setActionCommand("add");
			
			contentPanel.add(editButton);
		}
		
		
		
	}

	@Override
	public void updateObserver(ItemModel i, int action) {
		// TODO Auto-generated method stub
		if(i.getId() == item.getId()){
			//update on 1
			if(action ==1 ){
				this.item = i;
				this.PartNameField.setText(item.getPart().getPname());
				this.QuantityField.setText(Integer.toString(item.getQuantity()));
				for(int j =0; j < input.length; j++){
					if(item.getLocation().equals(locArray[j])){
						LocationField.setSelectedIndex(j);
					}
				}
				for(int j =0; j < input.length; j++){
					if(item.getPart().getPnum().equals(input[j])){
						PartIdField.setSelectedIndex(j);
					}
				}
				//this.repaint();
			}
			// delete on 2
			//TODO close JPanel
		}
		
	}

	public void updatePanel(){
		this.PartNameField.setText(this.item.getPart().getPname());
		this.QuantityField.setText(Integer.toString(item.getQuantity()));
		
		
		for(int j =0; j < input.length; j++){
			if(item.getPart().getPnum().equals(input[j])){
				PartIdField.setSelectedIndex(j);
			}
		}
		
		for(int j =0; j < locArray.length; j++){
			if(item.getLocation().equals(locArray[j])){
				LocationField.setSelectedIndex(j);
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()){
			case "add":
				String id = PartIdField.getSelectedItem().toString();
				System.out.println(id);
				
				String loc = LocationField.getSelectedItem().toString();
				int quant = Integer.parseInt(QuantityField.getText());
				
				if( item.editModel(id, loc, quant, master.getController()) == -1){
					master.displayChildMessage("Invalid Values");
				}else{
					master.getController().addItem(this.item);
				}
				this.item = new ItemModel();
				this.updatePanel();
				break;
			case "delete":
				if(	master.getController().deleteItem(this.item) ==-1){
					master.displayChildMessage("Quantity must be 0");
				}
				break;
			case "update":
				String id2 = PartIdField.getSelectedItem().toString();				
				String loc2 = LocationField.getSelectedItem().toString();
				int quant2 = Integer.parseInt(QuantityField.getText());
				
				if( item.editModel(id2, loc2, quant2, master.getController()) == -1){
					master.displayChildMessage("Invalid Values");
				}else{
					master.getController().updateInventory(this.item);
				}
				break;
		}
		
	}
}
