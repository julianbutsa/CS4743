package Window;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import control.ProductPartObserver;
import Model.*;

public class ProductPartDetailPanel extends ChildPanel implements ActionListener, ProductPartObserver {
	
	private ProductPartModel part;
	private ProductModel product;
	private JLabel ProductID;
	private JLabel PartID;
	private JLabel Quantity;
	private JLabel ProductIDField;
	private JLabel PartIDF;
	private String[] input;
	
	private JComboBox PartIDField;
	private JTextField QuantityField;
	
	private JButton updateButton;
	private JButton deleteButton;

	public ProductPartDetailPanel(win m, ProductPartModel model, ProductModel prod, int action){
		super(m);
		master.getController().registerProductPartObserver(this);
		this.part = model;
		this.setPreferredSize(new Dimension(300,150));
		contentPanel.setLayout(new GridLayout(4,2));
		product = prod;
		
		this.ProductID = new JLabel("Product Id");
		this.PartID = new JLabel("Part Id");
		this.Quantity = new JLabel("Quantity");
		
		ArrayList<PartModel> parts = master.getController().getInventory();
		input = new String[parts.size()];
		for(int j = 0 ; j < parts.size(); j++){
			input[j] = String.valueOf(parts.get(j).getId());
		}
		
		this.ProductIDField = new JLabel(String.valueOf(part.getProductId()));
		this.PartIDF = new JLabel(String.valueOf(part.getPartId()));
		this.PartIDField = new JComboBox(input);
		this.QuantityField = new JTextField(Integer.toString(part.getQuantity()));

		
		for(int j = 0; j < input.length; j++){
			if(String.valueOf(part.getPartId()).equals(input[j])){
				PartIDField.setSelectedIndex(j);
			}
		}
		
		
		//edit on 1
		if(action == 1){
			this.updateButton = new JButton("Update");
			this.updateButton.addActionListener(this);
			this.updateButton.setActionCommand("update");
			
			this.deleteButton = new JButton("Delete");
			this.deleteButton.addActionListener(this);
			this.deleteButton.setActionCommand("delete");
			
			contentPanel.add(ProductID);
			contentPanel.add(ProductIDField);
			contentPanel.add(PartID);
			contentPanel.add(PartIDF);
			contentPanel.add(Quantity);
			contentPanel.add(QuantityField);
			contentPanel.add(updateButton);
			contentPanel.add(deleteButton);
		}else if(action == 0){
			//add on 0
			this.updateButton = new JButton("Add");
			this.updateButton.addActionListener(this);
			this.updateButton.setActionCommand("add");
			contentPanel.add(ProductID);
			contentPanel.add(ProductIDField);
			contentPanel.add(PartID);
			contentPanel.add(PartIDField);
			contentPanel.add(Quantity);
			contentPanel.add(QuantityField);
			contentPanel.add(updateButton);
		}
		super.myTitle = "Product Part Detail";
	}
	
	public void updatePanel(){
		for(int j = 0; j < input.length; j++){
			if(String.valueOf(part.getPartId()).equals(input[j])){
				PartIDField.setSelectedIndex(j);
			}
		}
		this.QuantityField.setText(String.valueOf(part.getQuantity()));

	}
	
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
			case("add"):
				Integer pid = Integer.parseInt(PartIDField.getSelectedItem().toString());
				Integer q = Integer.parseInt(QuantityField.getText());
				if(part.editModel(product.getId(),pid, q) != -1){
					if(master.getController().addProductPart(part) == -1){
						master.displayChildMessage("Invalid values");
					}else{
						product.addPart(part);
					}
				}else{
					master.displayChildMessage("Invalid values");
				}
				this.updatePanel();
				break;
			case("delete"):
				//product.deletePart(part);	
				break;
			case("update"):
				Integer pid2 = Integer.parseInt(PartIDField.getSelectedItem().toString());
				Integer q2 = Integer.parseInt(QuantityField.getText());
				if(part.editModel(product.getId(),pid2, q2) == -1){
					master.displayChildMessage("Invalid values");
				}else{
					master.getController().updateProductPart(part);
				}
				break;
		}
	}
	
public void updateObserver(ProductPartModel m, int action) {
		
		//action = 0 : delete
		if(action == 0){
			//TODO Figure out how to close JPanels
		}
		//action = 1 : update
		if(action == 1){
			//this.thisPart = m;
			this.ProductIDField.setText(String.valueOf(part.getProductId()));
			for(int j = 0; j < input.length; j++){
				if(String.valueOf(part.getPartId()).equals(input[j])){
					PartIDField.setSelectedIndex(j);
				}
			}
			this.QuantityField.setText(String.valueOf(part.getQuantity()));
		}
	}

}
