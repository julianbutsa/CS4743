package Window;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import control.InventoryObserver;
import DBclass.DBQuery;
import Model.ItemModel;
import Model.PartModel;
import Model.Session;

public class InventoryPanel extends ChildPanel implements ActionListener , MouseListener, InventoryObserver{

	private JScrollPane scrollPane;
	private JTable listTable;
	private JButton addButton;
	private JButton deleteButton;
	private JButton editButton;
	
	public DBQuery myDB;
	public ArrayList<ItemModel> inventory;
	
	
	public InventoryPanel(win m, ArrayList<ItemModel> i ) {
		super(m);
		master.getController().registerInvObserver(this);
		this.myTitle = "Inventory";

		//TODO figure out how to set the location of a child panel
		this.setLocation(master.getWidth()/2, 0);
		
		//get list
		this.inventory = i;
		
		// TODO Auto-generated constructor stub
		
		//add list to a scroll panel so it can expand
				this.scrollPane = new JScrollPane();
				//add everything to the panel
				this.setPreferredSize(new Dimension(450,500));

				this.scrollPane.setVisible(true);
				this.makeTable();

				
				//make menu
				addButton = new JButton("Add Entry");
				addButton.setActionCommand("add");
				addButton.addActionListener(this);
				contentPanel.add(addButton);
				
				deleteButton = new JButton("Delete Entry");
				deleteButton.setActionCommand("delete");
				deleteButton.addActionListener(this);
				deleteButton.setEnabled(false);
				contentPanel.add(deleteButton);
				
				editButton = new JButton("Edit Entry");
				editButton.setActionCommand("edit");
				editButton.addActionListener(this);
				editButton.setEnabled(false);
				contentPanel.add(editButton);
				
				contentPanel.add(scrollPane);
				

	}
	
	
	private void makeTable(){
		
		java.util.Iterator<ItemModel> i = inventory.iterator();
		
		Object[][] data = new String[inventory.size()][4];
		
		int index = 0;
		while(i.hasNext()){
			ItemModel temp = i.next();
			data[index][0] = temp.getPart().getPnum();
			data[index][1] = temp.getPart().getPname();
			data[index][2] = temp.getLocation();
			data[index][3] = String.valueOf(temp.getQuantity());
			
			index++;
		}
		
		String[] columnNames = {"Part Num",
								"Part Name",
								"Location",
								"Quantity",
                				};
		
		if(this.listTable != null){
			this.listTable.setVisible(false);
			this.scrollPane.remove(listTable);
		}
		this.listTable = new JTable(data, columnNames) {
			public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
		};
		this.listTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.listTable.removeEditor();
		this.listTable.addMouseListener(this);
		
		this.scrollPane.setViewportView(this.listTable);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()){
			case "add":
				Session a = master.getController().getSession();
				if(a.getAddInventory()){
				ItemModel i = new ItemModel();
				InventoryDetailPanel ipan = new InventoryDetailPanel(master, i, 0);
				master.openMDIChild(ipan);
				//call editPanel
				//itemPanel tempframe = new itemPanel(itemPanel.ADD_MODE, 0, myHandler);
				}else{
					System.out.println("Insufficient Permissions");
				}
				break;
			case "delete":
				Session a2 = master.getController().getSession();
				if(a2.getDeleteInventory()){
				if ( listTable.getSelectedRow() >= 0){
					ItemModel toDelete = master.getController().getInventoryEntry(listTable.getSelectedRow());
					if(master.getController().deleteItem(toDelete) == -1){
						master.displayChildMessage("Quantity needs to be 0 before deletion");
					}
				}
				}else{
					System.out.println("Insufficient Permissions");
				}
				break;
			case "edit":
				Session a3 = master.getController().getSession();
				if(a3.getAddInventory()){
				if (listTable.getSelectedRow() >= 0 ){
					ItemModel i2 = master.getController().getInventoryEntry(listTable.getSelectedRow());
					InventoryDetailPanel ipan2 = new InventoryDetailPanel( master, i2, 1 );
					master.openMDIChild(ipan2);
				}
				}else{
					System.out.println("Insufficient Permissions");
				}
				break;
		}
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if(listTable.getSelectedRow() >= 0){
			deleteButton.setEnabled(true);
			editButton.setEnabled(true);
		}
		else{
			deleteButton.setEnabled(false);
			editButton.setEnabled(false);
		}
		if(e.getClickCount() == 2){
			this.actionPerformed(new ActionEvent(this, 1, "edit"));
		}
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	
	}
	
	
	private void redraw(){
		makeTable();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	
	}


	@Override
	public void updateObserver(ItemModel i, int action) {
		// TODO Auto-generated method stub
		this.inventory = master.getController().getItemInventory();
		makeTable();
		this.scrollPane.repaint();
	}
	
}
