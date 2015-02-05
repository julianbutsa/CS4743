package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import containers.Item;
import control.ListHandler;
import control.Observer;

public class ListPanel extends JPanel implements ActionListener, MouseListener, Observer{
	private ListHandler myHandler;
	private JScrollPane scrollPane;
	private JTable listTable;
	private JButton addButton;
	private JButton deleteButton;
	private JButton editButton;
	
	
	public ListPanel(){
		//set controller
		this.myHandler = new ListHandler("items.txt");
		
		myHandler.register(this);
		
		//make list
		
		
		
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

	private void redraw(){
		makeTable();
	}
	private void makeTable(){
		
		ArrayList<Item> items = this.myHandler.getItemList();
		java.util.Iterator<Item> i = items.iterator();
		
		Object[][] data = new String[items.size()][4];
		
		int index = 0;
		while(i.hasNext()){
			Item temp = i.next();
			data[index][0] = String.valueOf( temp.getPartNumber());
			data[index][1] = temp.getPartName();
			data[index][2] = temp.getVendor();
			data[index][3] = String.valueOf(temp.getQuantity());
			
			index++;
		}
		
		String[] columnNames = {"Item ID",
                "Item Name",
                "Vendor",
                "Quantity"};
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
				itemPanel tempframe = new itemPanel(itemPanel.ADD_MODE, 0, myHandler);
				break;
			case "delete":
				if ( listTable.getSelectedRow() > 0){
					myHandler.deleteItem(listTable.getSelectedRow());
				}
				break;
			case "edit":
				if (listTable.getSelectedRow() > 0 ){
					itemPanel editframe = new itemPanel(itemPanel.EDIT_MODE, listTable.getSelectedRow(), myHandler);

				}
		}
		
	}

	
	/*mouse listener stuff	 */
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(listTable.getSelectedRow() > 0){
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

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateObserver() {
		// TODO Auto-generated method stub
		makeTable();
		this.scrollPane.repaint();
		
	}


}
