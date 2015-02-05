package assignment1;

import java.awt.event.*;

public class PartController implements ActionListener{

	private InventoryModel model;
	private PartView view;
	

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getActionCommand()){
			case "add":
				addPartPanel add = view.getAddPartPanel();
			int q = add.getQtf();
			PartModel m = new PartModel(add.getNametf(), add.getNumbertf(), add.getVendortf(), q);
			m.setQunit(add.getQUnit());
			m.setLocation(add.getLocal());
			int a = model.addPart(m);
			if(a != 0){
				add.setQtf("Error Detected");
				add.setNametf("Error Detected");
				add.setNumbertf("Error Detected");
				add.setVendortf("Error Detected");
			}else{
				add.setQtf("");
				add.setNametf("");
				add.setNumbertf("");
				add.setVendortf("");
			}
			view.refreshInventory();
			view.repaint();
			break;
		}
	}
	
	public PartController(InventoryModel model, PartView view){
		this.model = model;
		this.view = view;
	}
}
