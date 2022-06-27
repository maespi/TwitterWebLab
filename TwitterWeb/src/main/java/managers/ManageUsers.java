package managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import models.Tweet;
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
	
	//Finalizes DB connection.
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
	
	// Check if all the fields are filled correctly.
	public boolean isComplete(User user) {
	    return(hasValue(user.getUser()) &&
	    	   hasValue(user.getMail()) &&
	    	   hasValue(user.getPwd()));
	}
	
	private boolean hasValue(String val) {
		return((val != null) && (!val.equals("")));
	}
	
	// Check min requirements for login completion.
	public boolean isLoginComplete(User user) {
	    return(hasValue(user.getUser()) &&
	    	   hasValue(user.getPwd()) );
	}
	
	// Gets One user by username (PK).
	public User getUser(String name) {
		String query = "Select usr, mail, admin FROM users WHERE usr = ? ;";
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
				user.setAdmin(rs.getBoolean("admin"));
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	// Return list of users.
	public List<User> getUsers(List<String> names) {
		
		List<User> users = new ArrayList<User>();
		
		for(int i=0; i<names.size(); i++) {
			User tmp = getUser(names.get(i));
			if(tmp.getUser().length()>0)
				users.add(tmp);
		}
		return users;
	}
	
	// Return list of users.
		public List<User> getXUsers(int x) {
			
			List<User> users = new ArrayList<User>();
			String query = "Select usr, mail FROM users LIMIT ? ;";
			PreparedStatement statement = null;
			ResultSet rs = null;
			User user = null;
			try {
				statement = db.prepareStatement(query);
				statement.setInt(1,x);
				rs = statement.executeQuery();
				while (rs.next()) {
					user = new User();
					user.setUser(rs.getString("usr"));
					user.setMail(rs.getString("mail"));
					users.add(user);
				}
				rs.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return users;
		}
	
	// Test if the User Login is correct.
	public Boolean checkLogin(User user) {
		
		String query = "SELECT usr,mail from users where usr = ? AND pwd = ?";
		PreparedStatement statement = null;
		boolean output = false;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,user.getUser());
			statement.setString(2,user.getPwd());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				user.setUser(rs.getString("usr"));
				user.setMail(rs.getString("mail"));
				output = true;
			} 
			rs.close();
			statement.close();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return output;
		
	}
	
	// Boolean function on follow a user.
	public boolean ifFollowedUser(String uid, String fid) {
		String query = "Select uid, fid FROM follows WHERE uid = ? AND fid = ?";
		PreparedStatement statement = null;
		boolean output = false;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,uid);
			statement.setString(2,fid);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				output = true;
			} 
			statement.close();
			rs.close();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return output;
	}
	
	
	// Follow a user.
	public void followUser(String uid, String fid) {
		String query = "INSERT INTO follows (uid,fid) VALUES (?,?)";
		PreparedStatement statement = null;
		try {
			System.out.println("user1 "+uid+" user2 "+fid);
			statement = db.prepareStatement(query);
			statement.setString(1,uid);
			statement.setString(2,fid);
			statement.executeUpdate();
			statement.close();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// UnFollow a user.
	public void unFollowUser(String uid, String fid) {
		String query = "DELETE FROM follows WHERE uid = ? AND fid = ?";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,uid);
			statement.setString(2,fid);
			statement.executeUpdate();
			statement.close();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteUser(String uid) {
		String query = "DELETE FROM users WHERE usr = ?;";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,uid);
			statement.executeUpdate();
			statement.close();
			System.out.println("User Manager: User Deleted");
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
