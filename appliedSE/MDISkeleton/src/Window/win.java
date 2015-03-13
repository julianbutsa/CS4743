package Window;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control.InventoryController;

/*
 * MasterFrame : a little MDI skeleton that has communication from child to JInternalFrame 
 * 					and from child to the top-level JFrame (MasterFrame)  
 */
public class win extends JFrame {
	private static final long serialVersionUID = 1L;
	private JDesktopPane desktop;
	private int newFrameX = 0, newFrameY = 0; //used to cascade or stagger starting x,y of JInternalFrames
	
	private InventoryController masterController;
	
	
	public win(String title) {
		super(title);
		
		this.masterController = new InventoryController();
		//create menu for adding inner frames
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem menuItem = new JMenuItem("Quit");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				win.this.dispatchEvent(new WindowEvent(win.this, WindowEvent.WINDOW_CLOSING));
			}
		});
		menu.add(menuItem);
		menuBar.add(menu);
		
		menu = new JMenu("Child");
		menuItem = new JMenuItem("Show Child");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChildPanel child = new ChildPanel(win.this);
				openMDIChild(child);
			}
		});
		menu.add(menuItem);
		menuBar.add(menu);

		setJMenuBar(menuBar);
		   
		
		
		
		
		
		//create the MDI desktop
		desktop = new JDesktopPane();
		
		
		add(desktop);

	}
	
	//create the child panel, insert it into a JInternalFrame and show it
	public void openMDIChild(ChildPanel child) {
		JInternalFrame frame = new JInternalFrame(child.getTitle(), true, true, true, true );
		frame.add(child, BorderLayout.CENTER);
		frame.pack();
		//wimpy little cascade for new frame starting x and y
		frame.setLocation(newFrameX, newFrameY);
		newFrameX = (newFrameX + 10) % desktop.getWidth(); 
		newFrameY = (newFrameY + 10) % desktop.getHeight(); 
		desktop.add(frame);
		frame.setVisible(true);
	}

	//display a child's message in a dialog centered on MDI frame
	public void displayChildMessage(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}
	
	
	
	
	//creates and displays the JFrame
	public void createAndShowGUI() {
		win frame = new win("MDI Skeleton");
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.setVisible(true);		
		
		//open an inventory window
		frame.openMDIChild(new PartListPanel(frame, masterController.getInventory()));
		frame.openMDIChild(new InventoryPanel(frame, masterController.getItemInventory()));
		frame.openMDIChild(new ProductPanel(frame, masterController.getProducts()));
	}

	//main: launch the JFrame on the EDT	
	public void run() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}

	public void openPartWindow(){
		
	}
	
	public void openInventoryWindow(){
		
	}
	
	public int getWidth(){
		return super.getWidth();
	}
	
	public int getHeight(){
		return super.getHeight();
	}
	
	public InventoryController getController(){
		return this.masterController;
	}
	
	
	

}