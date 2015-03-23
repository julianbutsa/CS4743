package Model;

public class Session {
	//USERS.
	public static int ALL_USR = 0;
	public static int PDT_MGR = 1;
	public static int INV_MGR = 2;
	public static int ADMIN = 3;
	
	
	//Permissions
	public boolean canViewProductTemplates;
	public boolean canAddProductTemplates;
	public boolean canDeleteProductTemplates;
	
	public boolean canCreateProducts;
	public boolean canDeleteProducts;
	
	public boolean canViewInventory;
	public boolean canAddInventory;
	public boolean canDeleteInventory;
	
	public boolean canViewParts;
	public boolean canAddParts;
	public boolean canDeleteParts;
	
	
	
	public Session(int user_level){
		canViewInventory = true;
		canViewParts = true;
		switch(user_level){
			case 1:
				canViewProductTemplates = true;
				canAddProductTemplates = true;
				canDeleteProductTemplates = true;
				canCreateProducts = true;
				break;
			case 2:
				canAddInventory = true;
				canAddParts = true;
				break;
			case 3:
				canViewProductTemplates = true;
				canAddProductTemplates = true;
				canDeleteProductTemplates = true;
				canCreateProducts = true;
				
				canAddInventory = true;
				canAddParts = true;
				canDeleteParts = true;
				canDeleteInventory = true;
				break;			
		}
	}
	
	public Session(){
		canViewProductTemplates = false;
		canAddProductTemplates = false;
		canDeleteProductTemplates = false;
		
		canCreateProducts = false;
		canDeleteProducts = false;
		
		canViewInventory = false;
		canAddInventory = false;
		canDeleteInventory = false;
		
		canViewParts = false;
		canAddParts = false;
		canDeleteParts = false;
	}
	
	
	
	
}
