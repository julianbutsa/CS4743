package view;

import javax.swing.JFrame;

import control.ListHandler;
import control.ListLoader;

//CS 4743 Assignment 1 by Julian Bruneault
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame mframe = new JFrame("Cabinetron Part Inventory");
		mframe.setSize(500, 700);
		ListPanel lp = new ListPanel();
		
		mframe.add(lp);
		lp.setVisible(true);
		mframe.setVisible(true);

		
		
	}

}
