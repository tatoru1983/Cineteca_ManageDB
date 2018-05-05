package dbentity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS", schema = "cineteca")
public class Users implements Serializable {

	private static final long serialVersionUID = -2980017798684412982L;
	
	private String username;
	private String password;

	public Users(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Users() {
		super();
	}
	
	@Id
	@Column(name = "USERNAME", unique = true, nullable = false)
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name = "PASSWORD", nullable = false)
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
