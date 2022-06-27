package managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Tweet;
import utils.DB;


public class ManageTweets {
	
	private DB db = null ;
	
	public ManageTweets() {
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
	
	/* Add a tweet */
	public void addTweet(Tweet tweet) {
		String query = "INSERT INTO tweets (uid,postdatetime,content) VALUES (?,?,?)";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,tweet.getUid());
			statement.setTimestamp(2,tweet.getPostDateTime());
			statement.setString(3,tweet.getContent());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* Delete existing tweet */
	public void deleteTweet(Integer id) {
		String query = "DELETE FROM tweets WHERE id = ?";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,id);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* Delete existing tweets From specific user */
	public void deleteTweetsFrom(String user) {
		String query = "DELETE FROM tweets WHERE uid = ?";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,user);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/* Get tweets from a user given start and end*/
	public List<Tweet> getUserTweets(String uid,Integer start, Integer end) {
		 String query = "SELECT tweets.id,tweets.uid,tweets.postdatetime,tweets.content,users.usr FROM tweets INNER JOIN users ON tweets.uid = users.usr where tweets.uid = ? ORDER BY tweets.postdatetime DESC LIMIT ?,? ;";
		 PreparedStatement statement = null;
		 List<Tweet> l = new ArrayList<Tweet>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setString(1,uid);
			 statement.setInt(2,start);
			 statement.setInt(3,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 Tweet tweet = new Tweet();
       		     tweet.setId(rs.getInt("id"));
				 tweet.setUid(rs.getString("uid"));
				 tweet.setPostDateTime(rs.getTimestamp("postdatetime"));
				 tweet.setContent(rs.getString("content"));
				 l.add(tweet);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}
	
	public List<Tweet> getAllUserTweets(String uid) {
		 String query = "SELECT tweets.id,tweets.uid,tweets.postdatetime,tweets.content,users.usr FROM tweets INNER JOIN users ON tweets.uid = users.usr where tweets.uid = ? ORDER BY tweets.postdatetime DESC;";
		 PreparedStatement statement = null;
		 List<Tweet> l = new ArrayList<Tweet>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setString(1,uid);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 Tweet tweet = new Tweet();
      		     tweet.setId(rs.getInt("id"));
				 tweet.setUid(rs.getString("uid"));
				 tweet.setPostDateTime(rs.getTimestamp("postdatetime"));
				 tweet.setContent(rs.getString("content"));
				 l.add(tweet);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}
	//select * from Tweets where uid in (select fid from follows where uid like "admin"); 
	//SELECT tweets.id,tweets.uid,tweets.postdatetime,tweets.content,users.usr FROM tweets INNER JOIN users ON tweets.uid = users.usr where tweets.uid = ? ORDER BY tweets.postdatetime DESC
	
	//Select Tweets from all following users
	public List<Tweet> getFollowingAllTweets(String uid) {
		 String query = "SELECT tweets.id,tweets.uid,tweets.postdatetime,tweets.content FROM tweets where tweets.uid IN (SELECT follows.fid FROM follows where uid = ?) ORDER BY tweets.postdatetime DESC;";
		 PreparedStatement statement = null;
		 List<Tweet> l = new ArrayList<Tweet>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setString(1,uid);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 Tweet tweet = new Tweet();
     		     tweet.setId(rs.getInt("id"));
				 tweet.setUid(rs.getString("uid"));
				 tweet.setPostDateTime(rs.getTimestamp("postdatetime"));
				 tweet.setContent(rs.getString("content"));
				 l.add(tweet);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}
	
	
}