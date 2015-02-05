package assignment1;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.event.*;

import javax.swing.*;


public class PartView extends JFrame implements ActionListener{
	private InventoryModel model;
	private InventoryPanel Inventory;
	private PartPanel parts;
	public JPanel currentPanel;
	private addPartPanel addPanel;


	public PartView(InventoryModel model) {
		super("Inventory Database");
		this.model = model;

		Inventory = new InventoryPanel(model, this);
		add(Inventory, BorderLayout.CENTER);
		currentPanel = Inventory;
		
		addPanel = new addPartPanel(model, this);
		add(addPanel, BorderLayout.SOUTH);
		
		
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getActionCommand()){
			
		}
		
	}
	
	public void pSet(JPanel p){
		currentPanel.setVisible(false);
		currentPanel = p;
		currentPanel.setVisible(true);
	}
	
	public void refreshInventory(){
		remove(Inventory);
		Inventory = new InventoryPanel(model, this);
		add(Inventory, BorderLayout.CENTER);
		this.revalidate();
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
