package Window;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Model.ProductModel;

public class ProductDetailPanel extends ChildPanel implements ActionListener {

	private ProductModel product;
	private JLabel ProductNumber;
	private JLabel Description;
	
	private JTextField ProductNumberField;
	private JTextField DescriptionField;
	
	private JButton updateButton;
	private JButton deleteButton;
	
	public ProductDetailPanel(win m, ProductModel p, int action) {
		super(m);
		this.product = p;
		this.setPreferredSize(new Dimension(300,150));
		contentPanel.setLayout(new GridLayout(3,2));
		
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
		

		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
