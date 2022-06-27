package models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String user = "";
	private String pwd = "";
	private String mail = "";
	private boolean admin = false;
	private int[] error = {0,0,0}; 
	
	public User() {
		super();
	}
	
	public User(String user, String pwd, String mail, boolean admin) {
		super();
		this.user = user;
		this.pwd = pwd;
		this.mail = mail;
		this.admin = admin;
	}

	public User(String user, String pwd, String mail) {
		super();
		this.user = user;
		this.pwd = pwd;
		this.mail = mail;
	}

	/* Getters */
	public String getUser(){
		return user;
	}
	
	public String getMail() {
		return mail;
	}
	
	public int[] getError() {
		return error;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public boolean isAdmin() {
		return admin;
	}

	/*Setters*/
	public void setUser(String user){
		this.user = user;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public void setMail(String mail) {
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]{1,15}@[a-zA-Z0-9.-]+[.]+[a-zA-Z0-9]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(mail);
		if (matcher.matches()) {
			this.mail = mail;
		} else {
			error[1]=1;
			System.out.println("Mail Error: "+mail);
			this.mail = "";
		}
		
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	/* Logic Functions */
	public boolean isFull() {
	    return(hasValue(getUser()) &&
	           hasValue(getPwd()) &&
	           hasValue(getMail()));
	}
	
	/* Logic Functions */
	public boolean isComplete() {
	    return(hasValue(getUser()) &&
	           hasValue(getMail()));
	}
	
	private boolean hasValue(String val) {
		return((val != null) && (!val.equals("")));
	}
}
