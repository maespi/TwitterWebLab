package models;

public class Pair
{
    User user;
    boolean follow;
    //Object Used mainly to controll users and wheter if they follow the main user doing the quest
	public Pair(User user, boolean follow) {
		super();
		this.user = user;
		this.follow = follow;
	}
	
	public Pair() {
		super();
	}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isFollow() {
		return follow;
	}

	public void setFollow(boolean follow) {
		this.follow = follow;
	}

    
}
