package Window;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;






import DBclass.DBQuery;
import Model.ItemModel;
import Model.ProductModel;
import Model.ProductPartModel;
import Model.PartModel;

public class ProductPartPanel extends ChildPanel implements ActionListener, MouseListener{

	private JScrollPane scrollPane;
	private JTable listTable;
	private JButton addButton;
	private JButton deleteButton;
	private JButton editButton;
	
	public ProductModel m;
	public ArrayList<ProductPartModel> parts;
	
	public ProductPartPanel(win m, ProductModel model ){
		super(m);
		this.myTitle = "Products";

		//TODO figure out how to set the location of a child panel
		//this.setLocation(master.getWidth()/2, 0);
		
		//get list
		this.m = model;
		this.parts = model.getParts();
		
		// TODO Auto-generated constructor stub
		
		//add list to a scroll panel so it can expand
				this.scrollPane = new JScrollPane();
				//add everything to the panel
				this.setPreferredSize(new Dimension(450,500));

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
		
		java.util.Iterator<ProductPartModel> i = parts.iterator();
		
		Object[][] data = new String[parts.size()][2];
		
		int index = 0;
		while(i.hasNext()){
			ProductPartModel temp = i.next();
			data[index][0] = String.valueOf(temp.getPartId());
			data[index][1] = String.valueOf(temp.getQuantity());
			
			index++;
		}
		
		String[] columnNames = {"Part ID", "Quantity"};
		
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
	
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
			case "add":
				ProductPartModel i = new ProductPartModel();
				i.setProductId(m.getId());
				ProductPartDetailPanel ipan = new ProductPartDetailPanel(master, i,m, 0);
				master.openMDIChild(ipan);
				break;
			
		case "delete":
			if ( listTable.getSelectedRow() >= 0){
				ProductPartModel toDelete = m.getPartEntry(listTable.getSelectedRow());
				m.deletePart(toDelete);	
			}
			break;
		case "edit":
			if (listTable.getSelectedRow() >= 0 ){
				ProductPartModel i2 = m.getPartEntry(listTable.getSelectedRow());
				ProductPartDetailPanel ipan2 = new ProductPartDetailPanel(master, i2,m, 1);
				master.openMDIChild(ipan2);
			}
			break;
		}
	}
	
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
	
	public void updateObserver(ProductModel p, int action) {
		// TODO Auto-generated method stub
		this.parts = m.getParts();
		makeTable();
		this.scrollPane.repaint();
		
	}
}
