package Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import DBclass.DBQuery;
import Model.InventoryModel;
import Model.ItemModel;
import Model.PartModel;

public class InventoryPanel extends ChildPanel implements ActionListener , MouseListener{

	private JScrollPane scrollPane;
	private JTable listTable;
	private JButton addButton;
	private JButton deleteButton;
	private JButton editButton;
	
	public DBQuery myDB;
	public ArrayList<ItemModel> inventory;
	
	
	public InventoryPanel(win m) {
		super(m);
		this.myTitle = "Inventory";
		this.setLocation(master.getWidth()/2, 0);
		//get list
		this.myDB = new DBQuery();
		
		// TODO Auto-generated constructor stub
		
		//add list to a scroll panel so it can expand
				this.scrollPane = new JScrollPane();
				//add everything to the panel

				this.scrollPane.setVisible(true);
				this.makeTable();
				
				
				//make menu
				addButton = new JButton("Add Part");
				addButton.setActionCommand("add");
				addButton.addActionListener(this);
				this.add(addButton);
				
				deleteButton = new JButton("Delete Part");
				deleteButton.setActionCommand("delete");
				deleteButton.addActionListener(this);
				deleteButton.setEnabled(false);
				this.add(deleteButton);
				
				editButton = new JButton("Edit Part");
				editButton.setActionCommand("edit");
				editButton.addActionListener(this);
				editButton.setEnabled(false);
				this.add(editButton);
				
				this.add(scrollPane);
	}
	
	
	private void makeTable(){
		
		ArrayList<ItemModel> items = myDB.getInventory();
		this.inventory = items;
		java.util.Iterator<ItemModel> i = items.iterator();
		
		Object[][] data = new String[items.size()][4];
		
		int index = 0;
		while(i.hasNext()){
			ItemModel temp = i.next();
			data[index][0] = temp.getPart().getPnum();
			data[index][1] = temp.getPart().getPname();
			data[index][2] = temp.getLocation();
			data[index][3] = String.valueOf(temp.getQuantity());
			
			index++;
		}
		
		String[] columnNames = {"Part ID",
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
				//call editPanel
				//itemPanel tempframe = new itemPanel(itemPanel.ADD_MODE, 0, myHandler);
				break;
			case "delete":
				if ( listTable.getSelectedRow() >= 0){
				//	myHandler.deleteItem(listTable.getSelectedRow());
				}
				break;
			case "edit":
				if (listTable.getSelectedRow() >= 0 ){
					//itemPanel editframe = new itemPanel(itemPanel.EDIT_MODE, listTable.getSelectedRow(), myHandler);

				}
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
	
	
	//@Override
	//public void updateObserver() {
	//	// TODO Auto-generated method stub
	//	makeTable();
	//	this.scrollPane.repaint();
		
	//}

}
