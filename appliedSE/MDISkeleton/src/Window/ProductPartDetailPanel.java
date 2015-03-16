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

import Model.*;

public class ProductPartDetailPanel extends ChildPanel implements ActionListener {
	
	private ProductPartModel part;
	private ProductModel product;
	private JLabel ProductID;
	private JLabel PartID;
	private JLabel Quantity;
	private JLabel ProductIDField;
	private String[] input;
	
	private JComboBox PartIDField;
	private JTextField QuantityField;
	
	private JButton updateButton;
	private JButton deleteButton;

	public ProductPartDetailPanel(win m, ProductPartModel model, ProductModel prod, int action){
		super(m);
		this.part = model;
		this.setPreferredSize(new Dimension(300,150));
		contentPanel.setLayout(new GridLayout(3,2));
		
		this.ProductID = new JLabel("Product Id");
		this.PartID = new JLabel("Part Id");
		this.Quantity = new JLabel("Quantity");
		
		ArrayList<PartModel> parts = master.getController().getInventory();
		input = new String[parts.size()];
		for(int j = 0 ; j < parts.size(); j++){
			input[j] = String.valueOf(parts.get(j).getId());
		}
		
		this.ProductIDField = new JLabel(String.valueOf(part.getProductId()));
		this.PartIDField = new JComboBox(input);
		this.QuantityField = new JTextField(part.getQuantity());
		
		contentPanel.add(ProductID);
		contentPanel.add(ProductIDField);
		contentPanel.add(PartID);
		contentPanel.add(PartIDField);
		
		
		
		//edit on 1
		if(action == 1){
			this.updateButton = new JButton("Update");
			this.updateButton.addActionListener(this);
			this.updateButton.setActionCommand("update");
			
			this.deleteButton = new JButton("Delete");
			this.deleteButton.addActionListener(this);
			this.updateButton.setActionCommand("delete");
			
			contentPanel.add(updateButton);
			contentPanel.add(deleteButton);
		}else if(action == 0){
			//add on 0
			this.updateButton = new JButton("Add");
			this.updateButton.addActionListener(this);
			this.updateButton.setActionCommand("add");
			contentPanel.add(updateButton);
		}
	}
	
	public void updatePanel(){
		
		this.QuantityField.setText(String.valueOf(part.getQuantity()));

	}
	
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
			case "add":
				Integer pid = Integer.parseInt(PartIDField.getSelectedItem().toString());
				Integer q = Integer.parseInt(QuantityField.getText());
				if(part.editModel(product.getId(),pid, q) != -1){
					if(product.addPart(part)==-1){
						master.displayChildMessage("Invalid values");
					}
				}else{
					master.displayChildMessage("Invalid values");
				}
				this.product = new ProductModel();
				this.updatePanel();
				break;
			case "delete":
				product.deletePart(part);	
				break;
			case "update":
				Integer pid2 = Integer.parseInt(PartIDField.getSelectedItem().toString());
				Integer q2 = Integer.parseInt(QuantityField.getText());
				if(part.editModel(product.getId(),pid2, q2) == -1){
					master.displayChildMessage("Invalid values");
				}else{
					master.getController().updateProduct(product);
				}
				break;
		}
	}
}
