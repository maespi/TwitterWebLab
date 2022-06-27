package controllers;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import managers.ManageTweets;
import managers.ManageUsers;
import models.Tweet;
import models.User;

/**
 * Servlet implementation class dTcontroller
 */
@WebServlet("/GetUserTweets")
public class GetUserTweets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserTweets() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		List<Tweet> tweets = Collections.emptyList();
		User user = (User) session.getAttribute("user");
		boolean anon = (boolean) session.getAttribute("anon");
		User target = (User) session.getAttribute("target");
		ManageTweets tweetManager = new ManageTweets();
		
		//We Check to see if target is created, in that case we exchange the data to load the target tweets.
		if (user != null) {
			//Retrieval of the Tweets.
			if(target != null) {
				tweets = tweetManager.getAllUserTweets(target.getUser());
			}else {
				tweets = tweetManager.getFollowingAllTweets(user.getUser());
			}	
		}else if(anon) {
			String nTarget = (String) request.getParameter("target");
			if(nTarget != null)
				tweets = tweetManager.getAllUserTweets(nTarget);
			else
				tweets = tweetManager.getTweets(20);
		}
			
		//Those tweets are stored in a temp storage (request) and so is target to use it in the view.
		tweetManager.finalize();
		request.setAttribute("user",user);
		request.setAttribute("target",target);
		request.setAttribute("tweets",tweets);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewTweets.jsp"); 
		dispatcher.forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

