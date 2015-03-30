package Window;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import Model.ProductModel;

public class ProductToInvPanel extends ChildPanel implements ActionListener{

	private ProductModel product;
	private JLabel partIDLabel;
	private JLabel LocationLabel;
	
	private JComboBox partId;
	private JComboBox Locations;
	
	private JButton submit;
	
	private String locArray[];
	private String PIDs[];
	
	
	public ProductToInvPanel(win m, ProductModel p) {
		super(m);
		
		product = p;
		contentPanel.setLayout(new GridLayout(3,2));
		this.partIDLabel = new JLabel ( "Product ID");
		this.LocationLabel = new JLabel("Locatin");
		
		ArrayList<ProductModel> plist = master.getController().getProducts();
		PIDs = new String[plist.size()];
		for(int i = 0; i < plist.size(); i ++){
			ProductModel temp = plist.get(i);
			PIDs[i] = temp.getprodNum();
		}
		this.partId = new JComboBox(PIDs);
		
		ArrayList<String> loc = master.getController().getLocations();
		locArray = new String[loc.size()];
		for(int j =0 ; j < loc.size(); j++){
			locArray[j] = loc.get(j);
		}
		Locations = new JComboBox(locArray);
		
		submit = new JButton("Add");
		submit.addActionListener(this);
		submit.setActionCommand("add");
		
		contentPanel.add(partIDLabel);
		contentPanel.add(partId);
		contentPanel.add(LocationLabel);
		contentPanel.add(Locations);
		contentPanel.add(submit);
		
		// TODO Auto-generated constructor stub
		
		this.setPreferredSize(new Dimension(300, 100));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("add")){
			if(master.getController().addproductotinv(partId.getSelectedIndex(), Locations.getSelectedItem().toString()) == -1){
				master.displayChildMessage("Failed to add product");
			}
		}
		
	}
	

	
}
