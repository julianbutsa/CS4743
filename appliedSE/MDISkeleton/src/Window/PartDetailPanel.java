package Window;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import control.PartObserver;
import Model.PartModel;

public class PartDetailPanel extends ChildPanel implements ActionListener, PartObserver{

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
	private JButton deleteButton;
	
	public PartDetailPanel(win m, PartModel pmodel, int action) {
		super(m);
		master.getController().registerPartObserver(this);
		thisPart = pmodel;
		contentPanel.setLayout(new GridLayout(6,2));
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
		
		//add buttons
		//action ==1 , update, action ==0, add
		if(action == 1){
			submitButton = new JButton("Update");
			submitButton.addActionListener(this);
			submitButton.setActionCommand("update");
			// TODO add action listener for submit
			
			deleteButton = new JButton("Delete");
			deleteButton.addActionListener(this);
			deleteButton.setActionCommand("delete");
			// TODO add action listener for delete	
			
			contentPanel.add(submitButton);
			contentPanel.add(deleteButton);
		}else if (action == 0){
			submitButton = new JButton("Add");
			submitButton.addActionListener(this);
			submitButton.setActionCommand("add");
			contentPanel.add(submitButton);
		}
		
		// TODO Auto-generated constructor stub
		super.myTitle = "Part Detail";
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()){
			case("update"):
				String name = nameField.getText();
				String id = idField.getText();
				String vend = VendorField.getText();
				String unit = UnitField.getSelectedItem().toString();
				String eid = ExternalIdField.getText();
				if(thisPart.editModel(name, id, eid,  vend, master.getController(), unit) == -1){
					master.displayChildMessage("Invalid values");
				}else{
					master.getController().updatePart(thisPart);
				}
				break;
			case("delete"):
				break;
			case("add"):
				String aname = nameField.getText();
				String aid = idField.getText();
				String aeid = ExternalIdField.getText();
				String avend = VendorField.getText();
				String aunit = UnitField.getSelectedItem().toString();
				if(thisPart.editModel(aname, aid, aeid, avend, master.getController(), aunit) != -1){
					if(master.getController().addPart(thisPart)==-1){
						master.displayChildMessage("Invalid values");
					}
				}else{
					master.displayChildMessage("Invalid values");
				}
				this.thisPart = new PartModel();
				this.updatePanel();
				master.revalidate();
				break;
		}
	}

	public void updatePanel(){
		this.idField.setText(thisPart.getPnum());
		this.nameField.setText(thisPart.getPname());
		this.ExternalIdField.setText(thisPart.getExternal());
		this.VendorField.setText(thisPart.getVendor());
	}
	@Override
	public void updateObserver(PartModel m, int action) {
		
		//action = 0 : delete
		if(action == 0){
			//TODO Figure out how to close JPanels
		}
		//action = 1 : update
		if(action == 1){
			//this.thisPart = m;
			this.idField.setText(thisPart.getPnum());
			this.nameField.setText(thisPart.getPname());
			this.ExternalIdField.setText(thisPart.getExternal());
			this.VendorField.setText(thisPart.getVendor());
			
			//figure out the index of the thing
			this.UnitField.setSelectedIndex(0);
		}
	}

}
