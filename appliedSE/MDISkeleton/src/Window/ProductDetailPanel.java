package Window;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Model.PartModel;
import Model.ProductModel;
import control.ProductObserver;

public class ProductDetailPanel extends ChildPanel implements ActionListener, ProductObserver{

	private ProductModel product;
	private JLabel ProductNumber;
	private JLabel Description;
	
	private JTextField ProductNumberField;
	private JTextField DescriptionField;
	
	private JButton updateButton;
	private JButton deleteButton;
	private JButton addButton;
	private JButton inventoryButton;
	public ProductDetailPanel(win m, ProductModel p, int action) {
		super(m);
		master.getController().registerProductObserver(this);
		this.product = p;
		
		if(action == 0){
			super.setPreferredSize(new Dimension(300,150));
			contentPanel.setLayout(new GridLayout(3,2));
		}
	
		if(action == 1){
			super.setPreferredSize(new Dimension(300,175));
			contentPanel.setLayout(new GridLayout(4,2));
		}
		
		this.ProductNumber = new JLabel("Product No.");
		this.Description = new JLabel("Description");
		
		this.ProductNumberField = new JTextField(product.getprodNum());
		this.DescriptionField = new JTextField(product.getDesc());
		
		
		contentPanel.add(ProductNumber);
		contentPanel.add(ProductNumberField);
		contentPanel.add(Description);
		contentPanel.add(DescriptionField);
		
		
		
		//edit on 1
		if(action == 1){
			this.inventoryButton = new JButton("Add Inv");
			inventoryButton.addActionListener(this);
			inventoryButton.setActionCommand("addinv");
			
			this.updateButton = new JButton("Update");
			this.updateButton.addActionListener(this);
			this.updateButton.setActionCommand("update");
			
			this.deleteButton = new JButton("Delete");
			this.deleteButton.addActionListener(this);
			this.deleteButton.setActionCommand("delete");
			
			contentPanel.add(updateButton);
			contentPanel.add(deleteButton);
			contentPanel.add(inventoryButton);
		}else if(action == 0){
			//add on 0
			this.addButton = new JButton("Add");
			this.addButton.addActionListener(this);
			this.addButton.setActionCommand("add");
			contentPanel.add(addButton);
		}
		
		super.myTitle = "Product Detail";
		// TODO Auto-generated constructor stub
	}
	
	public void updatePanel(){
		this.ProductNumberField.setText(product.getprodNum());
		this.DescriptionField.setText(product.getDesc());

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
			case("update"):
				System.out.println("Something");
				String pnum1 = ProductNumberField.getText();
				String desc1 = DescriptionField.getText();
				if(product.editModel(pnum1, desc1) == -1){
					master.displayChildMessage("Invalid values");
				}else{
					master.getController().updateProduct(product);
				}
				this.product = new ProductModel();
				this.updatePanel();
				break;
			case("add"):
				String pnum = ProductNumberField.getText();
				String desc = DescriptionField.getText();
				if(product.editModel(pnum, desc) != -1){
					if(master.getController().addProduct(product)==-1){
						master.displayChildMessage("Invalid values");
					}
				}else{
					master.displayChildMessage("Must Start With A");
				}
				this.product = new ProductModel();
				this.updatePanel();
				break;
			case("delete"):
				if(master.getController().deleteProduct(product) == -1){
					master.displayChildMessage("Quantity needs to be 0 before deletion");
				}
				break;
			case("addinv"):
				ProductToInvPanel p = new ProductToInvPanel(this.master ,product);
				master.openMDIChild(p);
				break;
			
				/*
				if(master.getController().addproductotinv(product) == -1){
					master.displayChildMessage("Failed to add product");
				}*/
				
			}
		}
	
public void updateObserver(ProductModel m, int action) {
		
		//action = 0 : delete
		if(action == 0){
			//TODO Figure out how to close JPanels
		}
		//action = 1 : update
		if(action == 1){
			//this.thisPart = m;
			this.ProductNumberField.setText(product.getprodNum());
			this.DescriptionField.setText(product.getDesc());
		}
	}

}
