/*
 * Structure of the tables
 * use the table indexes to get specific information in a general query
 * 
 * 
 * Part									inventory
 * --------------						--------------
 * 1: pid		int primary key			1. invid		int primary key
 * 2: pnum		int unique				2. pid			int foreign key
 * 3: pname		string 					3. lid			int foreign key
 * 4: vendor	string					4. qty			int
 * 5: unit		string
 * 
 * location
 * -----------
 * 1. lid		int primary key
 * 2. lname		string
 */

package DBclass;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javax.sql.DataSource;

import Model.*;

public class DBQuery {
	
	public Connection myConnection;
	public String url = 		"jdbc:mysql://devcloud.fulgentcorp.com:3306/jbc878";
	public String user =		"jbc878";
	public String password = 	"Ril6gl1LglxhUyQAdq6H";
	
	
	public DBQuery(){

		//connect to the database
		DataSource source = getDataSource();
		try {
			this.myConnection = source.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<PartModel> getParts(){
		ArrayList<PartModel> returnList = null;
		String query = "select * from part";
		try {
			Statement s = myConnection.createStatement();
			ResultSet rs = s.executeQuery(query);
			returnList = new ArrayList<PartModel>();
			//take everything from the resultSet and put it in a model class
			while(rs.next()){
				PartModel temp = new PartModel( rs.getString("pname"), rs.getString("pnum"), 
												rs.getString("vendor"), rs.getString("pnumext"),rs.getString("unit"));
				temp.setId(rs.getInt("pid"));
				temp.setVersion(rs.getInt("version"));
				returnList.add(temp);
			}	
			//close that stuff in case it got opened.
			rs.close();
			s.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
		return returnList;
	}
	/*
	public ArrayList<ItemModel> getInventory(){
		ArrayList<ItemModel> returnList = null;
		String query = "select * from inventory join location on (inventory.lid = location.lid)";
		try {
			Statement s = myConnection.createStatement();
			ResultSet rs = s.executeQuery(query);
			returnList = new ArrayList<ItemModel>();
			//take everything from the resultSet and put it in a model class
			while(rs.next()){
				PartModel tpart = this.getPart(rs.getInt("pid"));
				ItemModel m = new ItemModel(tpart, rs.getString("lname"), rs.getInt("qty"));
				m.setId(rs.getInt("invid"));
				m.setVersion(rs.getInt("version"));
				returnList.add(m);
			}
			//close that stuff in case it got opened.
			rs.close();
			s.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
		return returnList;
	}
	*/
	
	public ArrayList<ItemModel> getInventory(){
		ArrayList<ItemModel> returnList = null;
		String query = "select * from inventory join location on (inventory.lid = location.lid)";
		try {
			Statement s = myConnection.createStatement();
			ResultSet rs = s.executeQuery(query);
			returnList = new ArrayList<ItemModel>();
			//take everything from the resultSet and put it in a model class
			while(rs.next()){
				int flag = rs.getInt("partFlag");
				PartModel tpart = null ;
				ProductModel tprod = null;
				if(rs.getInt("partFlag") == 0){
					tpart = this.getPart(rs.getInt("pid"));
					ItemModel m = new ItemModel(tpart, rs.getString("lname"), rs.getInt("qty"));
					m.setId(rs.getInt("invid"));
					m.setVersion(rs.getInt("version"));
					m.setTypeFlag(flag);
					returnList.add(m);
				}else if(rs.getInt("partFlag")== 1){
					tprod = this.getProduct(rs.getInt("pid"));
					ItemModel m = new ItemModel(tprod, rs.getString("lname"), rs.getInt("qty"));
					m.setId(rs.getInt("invid"));
					m.setVersion(rs.getInt("version"));
					m.setTypeFlag(flag);
					returnList.add(m);
				}

			}
			//close that stuff in case it got opened.
			rs.close();
			s.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
		return returnList;
	}
	
	
	
	public ArrayList<String> getLocations(){
		ArrayList<String> returnList = null;
		String query = "select lname from location";
		try {
			Statement s = myConnection.createStatement();
			ResultSet rs = s.executeQuery(query);
			returnList = new ArrayList<String>();
			//take everything from the resultSet and put it in a model class
			while(rs.next()){
				returnList.add(rs.getString("lname"));
			}
			//close that stuff in case it got opened.
			rs.close();
			s.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
		return returnList;
	}
	
	public PartModel getPart(int pid){
		String query = "select * from part where pid = " + pid;
		PartModel p = null;
		try{
			Statement s = myConnection.createStatement();
			ResultSet r = s.executeQuery(query);
			if(r.next()){
				p = new PartModel(r.getString("pname"), r.getString("pnum"), 
						r.getString("vendor"), r.getString("pnumext"), r.getString("unit"));
				p.setId(r.getInt("pid"));
				p.setVersion(r.getInt("version"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return p;
	}
	
	public ProductModel getProduct(int pid){
		String query = "select * from product where id = " + pid;
		ProductModel p = null;
		try{
			Statement s = myConnection.createStatement();
			ResultSet r = s.executeQuery(query);
			if(r.next()){
				p = new ProductModel(r.getString("productno"), r.getString("description"));
				p.setId(r.getInt("id"));
				p.setVersion(r.getInt("version"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return p;
	}
	
	public ProductPartModel getProductPart(int productid, int partid){
		String query = "select * from ProductPart where partid = " + partid + " and productid = " +productid;
		ProductPartModel p = null;
		try{
			Statement s = myConnection.createStatement();
			ResultSet r = s.executeQuery(query);
			if(r.next()){
				p = new ProductPartModel(r.getInt("productid"), r.getInt("partid"), r.getInt("quantity"));
				p.setVersion(r.getInt("version"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return p;
	}
	
	public ItemModel getItem(int id){
		ItemModel p = null;
		String query = "select * from inventory where invid = " + id;
		try {
			Statement s = myConnection.createStatement();
			ResultSet rs = s.executeQuery(query);
			//take everything from the resultSet and put it in a model class
			while(rs.next()){
				PartModel tpart = this.getPart(rs.getInt("pid"));
				p = new ItemModel(tpart, getLocation(rs.getInt("lid")), rs.getInt("qty"));
				p.setId(rs.getInt("invid"));
				p.setVersion(rs.getInt("version"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return p;
	}
	
	public String getLocation(int lid){
		String ret = "Unknown";
		String query = "select lname from location where lid = "+lid;
		try{
			Statement s = myConnection.createStatement();
			ResultSet r =  s.executeQuery(query);
			if(r.next()){
				ret = r.getString("lname");
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return ret;
	}
	
	public int getLocationId(String l){
		int lid = 0;
		String query = "select lid from location where lname = \""+l+"\"";
		try{
			Statement s = myConnection.createStatement();
			ResultSet r =  s.executeQuery(query);
			if(r.next()){
				lid = r.getInt("lid");
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return lid;
	}
	
	
	public void addPart(PartModel p){
		
		//insert into part table
		String query = "insert into part (pid, pname, pnum, pnumext, unit, vendor, version) "
				+ "values (\""+p.getId()+"\",\""+p.getPname()+"\", \""+p.getPnum()+"\", "+p.getExternal()+",\""+p.getQunit()+"\",\""+p.getVendor()+"\", \"1\")";
		System.out.println(query);
		try{
			Statement s = myConnection.createStatement();
			s.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = s.getGeneratedKeys();
			if(rs.next()){
				int pid = rs.getInt(1);
				p.setId(pid);
			}
			
			
			//this.addToInventory(p);
		}catch(SQLException e){
			e.printStackTrace();
		}		
	}
	
	public void addToInventory(ItemModel i){
		int pid = 0;
		if(i.typeFlag == 0){
			pid = i.getPart().getId();
		}else if(i.typeFlag == 1){
			pid = i.getProduct().getId();
		}
		
		int lid = this.getLocationId(i.getLocation());
		String query = "insert into inventory (pid, lid, qty, partFlag, version ) "
				+ "values (\""+pid+"\", "+ lid +", "+i.getQuantity()+", "+i.getTypeFlag()+", "+i.getVersion()+")";
		try{
			Statement s = myConnection.createStatement();
			int iid = s.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			i.setId(iid);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public void addLocation(String lname){
		
		String query = "insert into location (lname) values (\""+lname+"\")";
		try{
			Statement s = myConnection.createStatement();
			s.executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	/*
	 * delete function 
	 */
	
	public void deletePart(PartModel p){
		this.deleteInventoryEntry(p.getId());
		String query = "delete from part where pid = "+p.getId();
		try{
			Statement s = myConnection.createStatement();
			s.executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public void deleteInventoryEntry(ItemModel i){
		String query = "delete from inventory where invid = " + i.getId();
		try{
			Statement s = myConnection.createStatement();
			s.executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	public void deleteInventoryEntry(int pid){
		String query = "delete from inventory where pid = "+pid;
		try{
			Statement s = myConnection.createStatement();
			s.executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	/* 
	 * Update functions
	 */
	public void updatePart(PartModel p){
		PartModel old = this.getPart(p.getId());
		boolean first = true;
		//insert into part table
		/*String query = "update part set ";
		if(!old.getPname().equals(p.getPname())){
			query += "pname = \""+p.getPname()+"\"";
			if(first){
				first = false;
			}
		}
		if(!old.getPnum().equals(p.getPnum())){
			if(first){
				first = false;
			}else{
				query += ",";
			}
			query += " pnum =\""+p.getPnum()+"\"";
		}
		if(!old.getExternal().equals(p.getExternal())){
			if(first){
				first = false;
			}else{
				query += ",";
			}
			query += "pnumext= \""+p.getExternal()+"\"";
		}
		if(!old.getVendor().equals(p.getVendor())){
			if(first){
				first = false;
			}else{
				query += ",";
			}
			query +="vendor= \""+p.getVendor()+"\"";
		}
		if(!old.getQunit().equals(p.getQunit())){
			if(first){
				first = false;
			}else{
				query += ",";
			}
			query += "unit= \"" + p.getQunit() +"\"";
		}
		
		query += ", version= \"" + p.getVersion() +"\"";
		query +=" where pid = " +p.getId();*/

		String query =  "update ignore part set pname = \""
				+p.getPname()+"\", pnum = \""+p.getPnum() +"\", pnumext = \"" + p.getExternal() +"\", unit = \"" + p.getQunit() +
				"\", vendor = \""+ p.getVendor() +"\", version = "+p.getVersion() + " where pid = "+ p.getId();
		//System.out.println(query);
		try{
			Statement s = myConnection.createStatement();
			s.executeUpdate(query);			
		}catch(SQLException e){
			e.printStackTrace();
		}		
	}
	
	public void updateProduct(ProductModel p){	

		String query =  "update ignore product set description = \""
				+p.getDesc()+"\", productno = \""+p.getprodNum() +"\", version = "+p.getVersion() + " where id = "+ p.getId();
		System.out.println(query);
		try{
			Statement s = myConnection.createStatement();
			s.executeUpdate(query);			
		}catch(SQLException e){
			e.printStackTrace();
		}		
	}
	
	public void updateProductPart(ProductPartModel p){	

		String query =  "update ignore ProductPart set quantity = "
				+p.getQuantity()+", version = "+p.getVersion() + " where partid = "+ p.getPartId() +" and productid = "+p.getProductId();
		System.out.println(query);
		try{
			Statement s = myConnection.createStatement();
			s.executeUpdate(query);			
		}catch(SQLException e){
			e.printStackTrace();
		}		
	}
	
	public void addProductPart(ProductPartModel p){	

		String query = "insert into ProductPart (partid, productid, quantity, version) "
				+ "values ("+p.getPartId()+","+p.getProductId()+", "+p.getQuantity()+", 1)";
		System.out.println(query);
		try{
			Statement s = myConnection.createStatement();
			int iid = s.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			//this.addToInventory(p);
		}catch(SQLException e){
			e.printStackTrace();
		}		
	}

	
	public ArrayList<ProductModel> getProducts(){
		ArrayList<ProductModel> returnList = null;
		String query = "select * from product";
		try {
			Statement s = myConnection.createStatement();
			ResultSet rs = s.executeQuery(query);
			returnList = new ArrayList<ProductModel>();
			//take everything from the resultSet and put it in a model class
			while(rs.next()){
				ProductModel temp = new ProductModel(  rs.getString("description"), rs.getString("productno"));
				temp.setId(rs.getInt("id"));
				temp.setVersion(rs.getInt("version"));
				returnList.add(temp);
			}	
			//close that stuff in case it got opened.
			rs.close();
			s.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
		return returnList;
	}
	
	public ArrayList<ProductPartModel> getProductParts(){
		ArrayList<ProductPartModel> returnList = null;
		String query = "select * from ProductPart";
		try {
			Statement s = myConnection.createStatement();
			ResultSet rs = s.executeQuery(query);
			returnList = new ArrayList<ProductPartModel>();
			//take everything from the resultSet and put it in a model class
			while(rs.next()){
				ProductPartModel temp = new ProductPartModel(rs.getInt("productid"), rs.getInt("partid"), rs.getInt("quantity"));
				temp.setVersion(rs.getInt("version"));
				returnList.add(temp);
			}	
			//close that stuff in case it got opened.
			rs.close();
			s.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
		return returnList;
	}
	
	public void addProduct(ProductModel i){
		int pid = i.getId();
		String no = i.getprodNum();
		String desc = i.getDesc();
		String query = "insert into product (id, productno, description, version) "
				+ "values (\""+pid+"\", \""+ no +"\", \""+desc+"\", 1)";
		try{
			Statement s = myConnection.createStatement();
			int iid = s.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			i.setId(iid);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public void deleteProductPart(ProductPartModel p){
		String query = "delete from ProductPart where partid = "+p.getPartId() +" and productid = " +p.getProductId();
		try{
			Statement s = myConnection.createStatement();
			s.executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public void deleteProduct(ProductModel p){
		String query = "delete from product where id = "+p.getId();
		try{
			Statement s = myConnection.createStatement();
			s.executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public UserModel login(String usr, String pass) {
		// TODO Auto-generated method stub
		System.out.println(usr);
		String query =  "select * from login where usr = \""+usr+"\"";
		String p;
		try{
			Statement s = myConnection.createStatement();
			ResultSet rs = s.executeQuery(query);
			
			
			if(rs.next()){
				p = rs.getString("pwd");
				if(p.equals(pass)){
					return new UserModel(rs.getString("usr"), rs.getString("email"), rs.getInt("permissions"));
				}
			}
			System.out.printf("%s\n", rs.getString("pwd"));

		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}		
		
		return null;
	}
	
	public void register(UserModel m, String password){
		String query = "insert into login ( usr, pwd, email, permissions) values (\""+ m.getUsername() + "\",\""+  password + "\", \""+m.getEmail() + "\", "+ 0 + ")";
		try{
			Statement s = myConnection.createStatement();
			s.executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void updateInventory(ItemModel i){
		/*
		String query = "SET foreign_key_checks = 0 ";
		try{
			Statement s = myConnection.createStatement();
			s.executeUpdate(query);			
		}catch(SQLException e){
			e.printStackTrace();
		}
		*/
		System.out.println(i.getLocation() );
		String query =  "update ignore inventory set pid = "+i.getPart().getId()+", lid ="+this.getLocationId(i.getLocation()) +", qty = " + i.getQuantity() +", version = " + i.getVersion() +" where invid ="+ i.getId();
		try{
			Statement s = myConnection.createStatement();
			s.executeUpdate(query);			
		}catch(SQLException e){
			e.printStackTrace();
		}		
		/*
		query = "SET foreign_key_checks = 1";
		try{
			Statement s = myConnection.createStatement();
			s.executeUpdate(query);			
		}catch(SQLException e){
			e.printStackTrace();
		}*/
	}
	
	
	private DataSource getDataSource() {
		Properties props = new Properties();
		FileInputStream fis = null;
        try {
        	fis = new FileInputStream("db.properties");
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        try {
        	MysqlDataSource mysqlDS = new MysqlDataSource();
        	mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
        	mysqlDS.setUser(props.getProperty("MYSQL_DB_USER"));
        	mysqlDS.setPassword(props.getProperty("MYSQL_DB_PW"));
        	return mysqlDS;
        } catch(RuntimeException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	
	public void destroy(){
		try {
			this.myConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
