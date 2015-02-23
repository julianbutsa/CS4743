package assignment1;

import java.awt.event.*;

public class PartController implements ActionListener{

	private InventoryModel model;
	private PartView view;
	

	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Got an addpart command");
		// TODO Auto-generated method stub
		switch(arg0.getActionCommand()){
			case "add":
				addPartPanel add = view.getAddPartPanel();
			PartModel m = new PartModel(add.getNametf(), add.getNumbertf(), add.getVendortf(),add.getEtf());
			m.setQunit(add.getQUnit());
			int a = model.addPart(m);
			if(a != 0){
				add.setEtf("Error Detected");
				add.setNametf("Error Detected");
				add.setNumbertf("Error Detected");
				add.setVendortf("Error Detected");
			}else{
				add.setNametf("");
				add.setNumbertf("");
				add.setVendortf("");
				add.setEtf("");
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
