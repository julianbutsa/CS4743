package userRemote;



import javax.ejb.Remote;


@Remote
public interface UserBeanRemote {
	public int getId();
	public void setId(int id);


	public String getFullname();
	public void setFullname(String fullname);

	public String getEmail();
	public void setEmail(String email);

	
}
