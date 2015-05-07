package user;
import java.util.Set;

import javax.ejb.Stateful;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

import userRemote.UserBeanRemote;


@Entity
public class User {
	@ElementCollection
	@Id
	private Set<Integer> id;
	
	@ElementCollection
	@Id
	private Set<String> fullname;
	@ElementCollection
	private Set<String> email;
	@ElementCollection
	private Set<String> password;
	@ElementCollection
	private Set<Integer> permissions;
	
	
}
