package controllers;

import java.io.IOException;
import java.util.ArrayList;
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
import models.Pair;
import models.Tweet;
import models.User;

/**
 * Servlet implementation class GetUsers
 */
@WebServlet("/GetUsers")
public class GetUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		List<Pair> pairUsers = new ArrayList<>();
		List<User> users = Collections.emptyList();
		User user = (User) session.getAttribute("user");
		
		//We Check to see if target is created, in that case we exchange the data to load the target tweets.
		if (user != null) {
			//Retrieval of 10 first users.
			ManageUsers userManager = new ManageUsers();
			users = userManager.getXUsers(10);
			//We get each user and whether or not they follow the current main user
			for(int i=0;i<users.size();i++) {
				boolean follow = userManager.ifFollowedUser(user.getUser(), users.get(i).getUser());
				Pair p = new Pair();
				p.setFollow(follow);
				p.setUser(users.get(i));
				pairUsers.add(p);
			}
			userManager.finalize();	
		}
		//Those tweets are stored in a temp storage (request) and so is target to use it in the view.
		request.setAttribute("user",user);
		request.setAttribute("users",pairUsers);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewUsers.jsp"); 
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
