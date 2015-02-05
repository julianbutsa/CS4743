package unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import containers.Item;
import control.ListLoader;

public class testLoader {

	/* test list
	1 item1 vendor1 10
	2 item2 vendor1 1
	3 item3 vendor2 2
	*/
	
	public int ino[] = { 1,2,3};
	public String iname[] = { "item1", "item2", "item3" };
	public int quantity[] = { 10,1,2};
	public String vendor[] = {"vendor1", "vendor1", "vendor2"};
	
	@Test
	public void testLoader() {
		ArrayList<Item> testList = ListLoader.load("src/unittests/testList.txt");
		for(int i = 0; i < 3; i++){
			Item temp = testList.get(i);
			assertEquals(iname[i],temp.getPartName());
			assertEquals(ino[i], temp.getPartNumber());
			assertEquals(vendor[i], temp.getVendor());
			assertEquals(quantity[i], temp.getQuantity());	
		}
	}

}
