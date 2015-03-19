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
import Model.PartModel;
import Model.ProductModel;

import control.ProductObserver;

public class ProductPanel extends ChildPanel implements ActionListener, MouseListener, ProductObserver{


	private JScrollPane scrollPane;
	private JTable listTable;
	private JButton addButton;
	private JButton deleteButton;
	private JButton editButton;
	private JButton detailButton;
	
	public DBQuery myDB;
	public ArrayList<ProductModel> products;
	
	
	public ProductPanel(win m, ArrayList<ProductModel> p ) {
		super(m);
		master.getController().registerProductObserver(this);
		this.myTitle = "Products";

		//TODO figure out how to set the location of a child panel
		//this.setLocation(master.getWidth()/2, 0);
		
		//get list
		this.products = p;
		
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
				
				detailButton = new JButton("Product Parts");
				detailButton.setActionCommand("detail");
				detailButton.addActionListener(this);
				detailButton.setEnabled(false);
				contentPanel.add(detailButton);
				
				contentPanel.add(scrollPane);
				

	}
	
	
	private void makeTable(){
		
		java.util.Iterator<ProductModel> i = products.iterator();
		
		Object[][] data = new String[products.size()][3];
		
		int index = 0;
		while(i.hasNext()){
			ProductModel temp = i.next();
			data[index][0] = String.valueOf(temp.getId());
			data[index][1] = temp.getprodNum();
			data[index][2] = temp.getDesc();
			index++;
		}
		
		String[] columnNames = {"Product Id", "Product Num", "Description"};
		
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
				ProductModel i = new ProductModel();
				ProductDetailPanel ipan = new ProductDetailPanel(master, i, 0);
				master.openMDIChild(ipan);
				break;
				
			case "delete":
				if ( listTable.getSelectedRow() >= 0){
					ProductModel toDelete = master.getController().getProductEntry(listTable.getSelectedRow());
					if(master.getController().deleteProduct(toDelete) == -1){
						master.displayChildMessage("Quantity needs to be 0 before deletion");
					}
				}
				break;
			case "edit":
				if (listTable.getSelectedRow() >= 0 ){
					ProductModel i2 = master.getController().getProductEntry(listTable.getSelectedRow());
					ProductDetailPanel ipan2 = new ProductDetailPanel(master, i2, 1);
					master.openMDIChild(ipan2);
				}
				break;
			case "detail":
				if (listTable.getSelectedRow() >= 0 ){
					ProductModel i3 = master.getController().getProductEntry(listTable.getSelectedRow());
					ProductPartPanel ipan3 = new ProductPartPanel(master, i3);
					master.openMDIChild(ipan3);
				}
				break;
		}
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if(listTable.getSelectedRow() >= 0){
			deleteButton.setEnabled(true);
			editButton.setEnabled(true);
			detailButton.setEnabled(true);
		}
		else{
			deleteButton.setEnabled(false);
			editButton.setEnabled(false);
			detailButton.setEnabled(false);
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
	
	public void updateObserver(ProductModel p, int action) {
		// TODO Auto-generated method stub
		this.products = master.getController().myDB.getProducts();
		makeTable();
		this.scrollPane.repaint();
		
	}

}
