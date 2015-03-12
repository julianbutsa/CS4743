package assignment1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ProductPanel extends JPanel implements ActionListener{
	private ProductModel model;
	private PartView view;
	private ProductInventoryPanel inview;
	private InventoryModel inventory;
	//private ProductDetailPanel dview;
	private JLabel productno;
	private JLabel description;
	private JButton delete;
	private JButton detail;

	
	public ProductPanel(ProductModel m, InventoryModel im, PartView v, ProductInventoryPanel iv) {
		this.model = m;
		this.view = v;
		this.inventory = im;
		this.inview = iv;
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		this.productno = new JLabel(m.getprodNum());
		this.setLayout(new GridLayout(1,0));
		this.description = new JLabel(m.getDesc());
		this.add(productno);
		this.add(description);

		
		this.delete = new JButton("Delete");
		delete.addActionListener(this);
		delete.setActionCommand("delete");
		this.detail = new JButton("Detail");
		detail.addActionListener(this);
		detail.setActionCommand("detail");
		
		this.add(delete);
		this.add(detail);
	}

	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getActionCommand()){
			/*case "delete":
				inventory.deleteItem(model);
				view.refreshInventory();
				view.pSet(inview);
				break;
			case "detail":
				dview = new ItemDetailPanel(model, view, inview, inventory);
				inview.setVisible(false);
				view.add(dview);
				view.pSet(dview);
				break;*/
		}
		
	}
	
}