package assignment1;

import java.awt.event.*;

import javax.swing.JFrame;

import DBclass.DBQuery;

public class Assign1 {
	/*
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/* create new model, view and controller */
        InventoryModel model = new InventoryModel();
		PartView view = new PartView(model);
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setSize(650, 400);
		view.setVisible(true);
		PartController controller = new PartController(model, view);
		view.registerListener(controller);
		
		
		DBQuery q = new DBQuery();
		q.getParts();
		q.addLocation("the place");
		q.destroy();
	}
}
