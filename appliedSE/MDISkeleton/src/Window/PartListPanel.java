package Window;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import control.PartObserver;
import DBclass.DBQuery;
import Model.*;

public class PartListPanel extends ChildPanel implements MouseListener, ActionListener, PartObserver {
	private JScrollPane scrollPane;
	private JTable listTable;
	private JButton addButton;
	private JButton deleteButton;
	private JButton editButton;
	private JButton updateButton;
	
	public ArrayList<PartModel> partList;
	
	
	public PartListPanel(win m, ArrayList<PartModel> p) {
		super(m);
		//master.getController().registerPartObserver(this);
		this.myTitle = "Part List";
		
		//get list
		this.partList = p;
		this.setPreferredSize(new Dimension(450,500));
		// TODO Auto-generated constructor stub
		
		//add list to a scroll panel so it can expand
				this.scrollPane = new JScrollPane();
				//add everything to the panel

				//this.partList= myDB.getParts();
				
				this.scrollPane.setVisible(true);
				this.makeTable();
				
				
				//make menu
				addButton = new JButton("Add Part");
				addButton.setActionCommand("add");
				addButton.addActionListener(this);
				contentPanel.add(addButton);
				
				deleteButton = new JButton("Delete Part");
				deleteButton.setActionCommand("delete");
				deleteButton.addActionListener(this);
				deleteButton.setEnabled(false);
				contentPanel.add(deleteButton);
				
				editButton = new JButton("Edit Part");
				editButton.setActionCommand("edit");
				editButton.addActionListener(this);
				editButton.setEnabled(false);
				contentPanel.add(editButton);
		
				
				contentPanel.add(scrollPane);
	}
	
	
	private void makeTable(){
		
		//ArrayList<PartModel> items = myDB.getParts();
		java.util.Iterator<PartModel> i = partList.iterator();
		
		Object[][] data = new String[partList.size()][5];
		
		int index = 0;
		while(i.hasNext()){
			PartModel temp = i.next();
			data[index][0] = temp.getPnum();
			data[index][1] = temp.getExternal();
			data[index][2] = temp.getPname();
			data[index][3] = temp.getVendor();
			data[index][4] = temp.getQunit();
			//data[index][3] = String.valueOf(temp.get());
			
			index++;
		}
		
		String[] columnNames = {	"Part Num",
									"External ID",
									"Part Name",
									"Vendor",
									"Unit"
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
				PartDetailPanel child = new PartDetailPanel(this.master, new PartModel(), 0);
				master.openMDIChild(child);
				//itemPanel tempframe = new itemPanel(itemPanel.ADD_MODE, 0, myHandler);
				break;
			case "delete":
				System.out.println("Hit");
				if ( listTable.getSelectedRow() >= 0){
					PartModel toDelete = master.getController().getPart(listTable.getSelectedRow());
					if(master.getController().deletePart(toDelete) == -1){
							master.displayChildMessage("Failed to delete.\n Inventory entries remain");
					}
				}
				break;
			case "edit":
				if (listTable.getSelectedRow() >= 0 ){
					PartModel p = master.getController().getPart(listTable.getSelectedRow());
					master.openMDIChild(new PartDetailPanel(master,  p, 1));
					//itemPanel editframe = new itemPanel(itemPanel.EDIT_MODE, listTable.getSelectedRow(), myHandler);

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
	public void updateObserver(PartModel p, int action) {
		// TODO Auto-generated method stub
		this.partList = master.getController().myDB.getParts();
		makeTable();
		this.scrollPane.repaint();
		
	}

}
