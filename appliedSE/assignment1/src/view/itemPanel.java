package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.glass.events.WindowEvent;

import containers.Item;
import control.ItemObserver;
import control.ListHandler;
import control.Observer;

public class itemPanel extends JFrame implements ActionListener, ItemObserver{
	public static final int EDIT_MODE = 1;
	public static final int ADD_MODE = 2;

	private JTextField itemno;
	private JTextField itemname;
	private JTextField vendor;
	private JTextField quantity;
	
	private JButton submit;
	
	private ListHandler myHandler;
	private int index;
	private Item item;
	private int mode;
	
	public itemPanel(int mode, int i, ListHandler lh){
		lh.registerItemObserver(this);
		this.index = i;
		this.setSize(300, 150);
		this.setLayout(new GridLayout(5,2));
		this.myHandler = lh;
		this.mode = mode;
		
		if(this.mode == ADD_MODE)
			this.item = new Item();
		else
			this.item = lh.getItem(i);
		
		JLabel inolabel = new JLabel("Item Number");
		this.add(inolabel);
		this.itemno = new JTextField(Integer.toString(item.getPartNumber()));
		this.add(itemno);
		
		JLabel inamelabel = new JLabel("Item Name");
		this.add(inamelabel);
		this.itemname = new JTextField(item.getPartName());
		this.add(itemname);

		JLabel vendorlabel = new JLabel("Vendor");
		this.add(vendorlabel);
		this.vendor = new JTextField(item.getVendor());
		this.add(vendor);

		JLabel quantitylabel = new JLabel("Quantity");
		this.add(quantitylabel);
		this.quantity = new JTextField(Integer.toString(item.getQuantity()));
		this.add(quantity);
		
		this.submit = new JButton("Submit");
		this.submit.addActionListener(this);
		if(this.mode == EDIT_MODE)
			this.submit.setActionCommand("edit");
		else if (this.mode == ADD_MODE)
			this.submit.setActionCommand("add");
		this.add(submit);

		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()){
			case "add":
				try{
					this.myHandler.addItem(Integer.valueOf(this.itemno.getText()), Integer.valueOf(this.quantity.getText()),
										this.itemname.getText(), this.vendor.getText());
					this.dispose();
				}catch(IllegalArgumentException e1){
					JOptionPane.showMessageDialog(this, e1.getMessage());
				}
				
				break;
			case "edit":
				try{
					this.myHandler.editItem(this.index, Integer.parseInt(this.itemno.getText()), Integer.parseInt(this.quantity.getText()),
										this.itemname.getText(), this.vendor.getText());
					this.dispose();
				}catch(IllegalArgumentException e1){
					JOptionPane.showMessageDialog(this, e1.getMessage());
				}
				break;
			
		}
		
	}



	@Override
	public void updateItemObserver(int mode, int index) {
		// TODO Auto-generated method stub
		
		switch(mode){
		//1 = edit
			case 1:
				this.item = myHandler.getItem(index);
				this.itemname.setText(this.item.getPartName());
				this.quantity.setText(String.valueOf(this.item.getQuantity()));
				this.vendor.setText(this.item.getVendor());
				this.itemno.setText(String.valueOf(this.item.getPartNumber()));
				this.repaint();
				break;
		//2 = delete
			case 2:
				if(this.index ==index)
					this.dispose();
				break;
		}
	}

}
