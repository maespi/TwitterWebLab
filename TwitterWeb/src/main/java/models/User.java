package models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String user = "";
	private String pwd = "";
	private String mail = "";
	private int[] error = {0,0,0}; 
	
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
	
	/*Setters*/
	public void setUser(String user){
		this.user = user;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public void setMail(String mail) {
		//TODO: Descomentar per a personalitzar l'entrada de mail
		/**
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(mail);
		if (matcher.matches()) {
			this.mail = mail;
			System.out.println(mail);
		} else {
			error[1]=1;
			System.out.println("Mail Error: "+mail);
		}
		*/
		this.mail = mail;
	}
	
	/* Logic Functions */
	public boolean isComplete() {
	    return(hasValue(getUser()) &&
	           hasValue(getPwd()) );
	}
	
	private boolean hasValue(String val) {
		return((val != null) && (!val.equals("")));
	}
}
