package models;

public class Pair
{
    User user;
    boolean follow;
    
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
