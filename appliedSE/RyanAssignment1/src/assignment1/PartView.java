package assignment1;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.event.*;

import javax.swing.*;


public class PartView extends JFrame implements ActionListener{
	private InventoryModel model;
	private InventoryPanel Inventory;
	private ItemInventoryPanel ItemInventory;
	private PartPanel parts;
	public JPanel currentPanel;
	private addPartPanel addPanel;
	private addItemPanel addItem;
	private JButton Switch;
	int tracker = 0;


	public PartView(InventoryModel model) {
		super("Inventory Database");
		this.model = model;

		Inventory = new InventoryPanel(model, this);
		add(Inventory, BorderLayout.CENTER);
		currentPanel = Inventory;
		
		ItemInventory = new ItemInventoryPanel(model, this);
		//add(ItemInventory, BorderLayout.CENTER);
		currentPanel = Inventory;
		
		addPanel = new addPartPanel(model, this);
		add(addPanel, BorderLayout.SOUTH);
		
		addItem = new addItemPanel(model, this);
		//add(addItem, BorderLayout.SOUTH);
		
		
		Switch = new JButton("Part-Item Switch");
		Switch.addActionListener(this);
		Switch.setActionCommand("Switch");
		add(Switch, BorderLayout.NORTH);
		
		
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getActionCommand()){
		case "Switch":
			if(tracker == 0){
				remove(Inventory);
				remove(addPanel);
				ItemInventory = new ItemInventoryPanel(model, this);
				addItem = new addItemPanel(model, this);
				add(ItemInventory, BorderLayout.CENTER);
				add(addItem, BorderLayout.SOUTH);
				this.revalidate();
				tracker++;
			}else{
				remove(ItemInventory);
				remove(addItem);
				Inventory = new InventoryPanel(model, this);
				addPanel = new addPartPanel(model, this);
				add(addPanel, BorderLayout.SOUTH);
				add(Inventory, BorderLayout.CENTER);
				this.revalidate();
				tracker--;
			}
		}
		
	}
	
	public void pSet(JPanel p){
		currentPanel.setVisible(false);
		currentPanel = p;
		currentPanel.setVisible(true);
	}
	
	public void refreshInventory(){
		if(tracker == 1){
			remove(ItemInventory);
			ItemInventory = new ItemInventoryPanel(model, this);
			add(ItemInventory, BorderLayout.CENTER);
			this.revalidate();
		}else{
			remove(Inventory);
			Inventory = new InventoryPanel(model, this);
			add(Inventory, BorderLayout.CENTER);
			this.revalidate();
		}
	}
	
	public void registerListener(PartController controller){
		addPanel.registerListener(controller);
	}
	
	public InventoryPanel getInventory(){
		return Inventory;
	}
	
	public addPartPanel getAddPartPanel(){
		return addPanel;
	}
}
