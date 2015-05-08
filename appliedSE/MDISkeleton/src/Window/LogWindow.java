package Window;

import java.awt.Dimension;
import java.io.Serializable;
import java.rmi.RemoteException;

import javassist.bytecode.Descriptor.Iterator;

import javax.rmi.PortableRemoteObject;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import logremote.LogObserver;
import Model.ItemLogs;
import Model.LogEntry;



public class LogWindow extends ChildPanel implements LogObserver, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int itemid;
	
	private JScrollPane scrollPane;
	private JTable listTable;
	private JButton closeButton;
	
	
	private ItemLogs logs;
	//private LogObLocal lol;
	
	public LogWindow(win m, int i) {
		super(m);
		
		try {
			PortableRemoteObject.exportObject(this);
		} catch (RemoteException e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
		}
		master.getController().logGateway.registerObserver(this);
		
		
		itemid = i;
		this.myTitle = "Part List";
		//master.getController().
		this.setPreferredSize(new Dimension(450,500));
		
		//add list to a scroll panel so it can expand
		this.scrollPane = new JScrollPane();
		//lol= new LogObLocal(master.getController().logGateway);
		//master.getController().logGateway.registerObserver(this);
		//add everything to the panel

		//this.partList= myDB.getParts();
		
		this.scrollPane.setVisible(true);
		
	
		logs = master.getController().getlogs(i);
		String[][] data = new String[logs.logs.size()][2];
		String[] columnNames = { "Date", "Description" };
		for(int index = 0; index < logs.logs.size(); index++){
			LogEntry le = logs.logs.get(index);
			data[index][0] = le.date;
			data[index][1] = le.desc;
			
		}
		
		this.listTable = new JTable(data, columnNames) {
			public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
		};
		scrollPane.setViewportView(listTable);
		
		listTable = new JTable();
		contentPanel.add(scrollPane);
	}

	@Override
	public void updateObserver(int id) {
		// TODO Auto-generated method stub
		if(itemid == id)
			master.displayChildMessage("Log Updated. Refresh");
	}

}
