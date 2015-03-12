package Window;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Model.ItemModel;

public class InventoryDetailPanel extends ChildPanel{

	private ItemModel item;
	
	private JLabel PartId;
	private JLabel Location;
	private JLabel Quantity;
	private JLabel PartName;
	
	private JComboBox PartIdField;
	private JTextField LocationField;
	private JTextField QuantityField;
	private JLabel PartNameField;
	
	
	public InventoryDetailPanel(win w, ItemModel i){
		super(w);
		
		this.item = i;
		this.PartId = new JLabel("Part ID");
		this.PartName = new JLabel("Part Name");
		this.Location = new JLabel("Location");
		this.Quantity = new JLabel("Quantity");
		
		
		
	}
}
