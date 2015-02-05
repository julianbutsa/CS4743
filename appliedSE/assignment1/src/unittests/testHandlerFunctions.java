package unittests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import containers.Item;
import control.ListHandler;

public class testHandlerFunctions {
	/* test list
	1 item1 vendor1 10
	2 item2 vendor1 1
	3 item3 vendor2 2
	*/
	
	public int ino[] = { 1,2,3};
	public String iname[] = { "item1", "item2", "item3" };
	public int quantity[] = { 10,1,2};
	public String vendor[] = {"vendor1", "vendor1", "vendor2"};
	
	public static ListHandler testHandler;
	
	
	@BeforeClass
	public static void setupbeforeclass(){
		testHandler = new ListHandler("src/unittests/testList.txt");
	}
	
	@Test
	public void testNameExists(){
		String testname = "item1";
		try{
			testHandler.addItem(1,1,testname,"vendor");
		}catch(IllegalArgumentException e){
			assertEquals("Part Name Exists", e.getMessage());
		}
	}
	
	@Test
	public void quantityzero(){
		int quantity = 0;
		try{
			testHandler.addItem(1,quantity,"item4","vendor");
		}catch(IllegalArgumentException e){
			assertEquals("Quantity must be greater than 0", e.getMessage());
		}
	}
	
	@Test
	public void testAddSuccess() {
		int itemno=5;
		String itemname = "idontexist";
		String vendor = "newvendor";
		int quantity = 10;
		
		testHandler.addItem(itemno, quantity, itemname, vendor);
		Item i = testHandler.getItem(testHandler.getItemList().size() -1);
		assertEquals(i.getPartName(), itemname);
		assertEquals(i.getQuantity(), quantity);
		assertEquals(i.getPartNumber(), itemno);
		assertEquals(i.getVendor(), vendor);
		
	}
	
	@Test
	public void testDelete() {
		int deleteIndex = 0;
		testHandler.deleteItem(deleteIndex);
		Item i = testHandler.getItem(deleteIndex);
		//we check name since names cannot be identical.  IF we delete a part with name a, there should be no other parts
		//with name a in the list
		assertNotEquals(iname[deleteIndex], i.getPartName() );
	}
	

	


	

}
