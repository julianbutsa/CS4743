package Window;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Model.PartModel;

public class PartDetailPanel extends ChildPanel{

	private PartModel thisPart;
	private JLabel PartName;
	private JLabel PartId;
	private JLabel ExternalId;
	private JLabel Vendor;
	private JLabel Unit;
	
	private JTextField nameField;
	private JTextField idField;
	private JTextField ExternalIdField;
	private JTextField VendorField;
	private JComboBox UnitField;
	
	private JButton submitButton;
	
	public PartDetailPanel(win m, PartModel pmodel) {
		super(m);
		thisPart = pmodel;
		contentPanel.setLayout(new GridLayout(5,2));
		super.setPreferredSize(new Dimension(300,150));
		
		//set up JLabels
		this.PartName = new JLabel("Part Name");
		this.PartId = new JLabel("Part ID");
		this.ExternalId = new JLabel("Ext.Id");
		this.Vendor = new JLabel("Vendor");
		this.Unit = new JLabel("Unit");
		
		//set up edit fields
		this.nameField = new JTextField(thisPart.getPname());
		this.idField = new JTextField(thisPart.getPnum());
		this.ExternalIdField = new JTextField(thisPart.getExternal());
		this.VendorField = new JTextField(thisPart.getVendor());
		
		String[] input = { "Linear Feet", "“Pieces"};
		UnitField = new JComboBox(input);
		
		
		submitButton = new JButton("Update");
		
		contentPanel.add(PartName);
		contentPanel.add(nameField);
		contentPanel.add(PartId);
		contentPanel.add(idField);
		contentPanel.add(ExternalId);
		contentPanel.add(ExternalIdField);
		contentPanel.add(Vendor);
		contentPanel.add(VendorField);
		contentPanel.add(Unit);
		contentPanel.add(UnitField);
		
		// TODO Auto-generated constructor stub
		super.myTitle = "Part Detail";
		
	}

}
