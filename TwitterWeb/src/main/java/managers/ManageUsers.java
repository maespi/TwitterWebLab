package managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.User;
import utils.DB;

public class ManageUsers {
	
	private DB db = null ;
	
	public ManageUsers() {
		try {
			db = new DB();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void finalize() {
		try {
			db.disconnectBD();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
		
	// Add new user
	public boolean addUser(String name, String mail, String pwd) {
		String query = "INSERT INTO users (usr,mail,pwd) VALUES (?,?,?)";
		PreparedStatement statement = null;
		boolean test = false;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,name);
			statement.setString(2,mail);
			statement.setString(3,pwd);
			statement.executeUpdate();
			statement.close();
			test = true;
		} catch (SQLException e) {
			e.printStackTrace();
			test = false;
		}
		return test;
	}
	
	/*Check if all the fields are filled correctly */
	public boolean isComplete(User user) {
	    return(hasValue(user.getUser()) &&
	    	   hasValue(user.getMail()) &&
	    	   hasValue(user.getPwd()));
	}
	
	private boolean hasValue(String val) {
		return((val != null) && (!val.equals("")));
	}
		
	public User getUser(String name) {
		String query = "Select usr, mail FROM users WHERE usr = ? ;";
		PreparedStatement statement = null;
		ResultSet rs = null;
		User user = null;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,name);
			rs = statement.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUser(rs.getString("usr"));
				user.setMail(rs.getString("mail"));
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	public List<User> getUsers(List<String> names) {
		
		List<User> users = new ArrayList<User>();
		
		for(int i=0; i<names.size(); i++) {
			User tmp = getUser(names.get(i));
			if(tmp.getUser().length()>0)
				users.add(tmp);
		}
		return users;
	}
	
	// TODO: add other methods 

}
