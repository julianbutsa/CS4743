package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import containers.Item;


/*
 * Loads a list of items from somewhere
 */
public class ListLoader {

	public static ArrayList<Item> load(String filename) {
		// TODO Auto-generated method stub
		ArrayList<Item> returnList = new ArrayList<Item>();
		
		
		Scanner inScanner = null;
		try{
			inScanner = new Scanner(new File(filename));		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		while(inScanner.hasNext()){
			int itemNumber = Integer.parseInt(inScanner.next());
			String itemName = inScanner.next();
			String vendor = inScanner.next();
			int quantity = Integer.parseInt(inScanner.next());
			
			returnList.add(new Item(itemNumber, itemName, vendor, quantity));
		}

		return returnList;
	}

}
