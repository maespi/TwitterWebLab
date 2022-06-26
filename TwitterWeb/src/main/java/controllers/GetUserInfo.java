package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import managers.ManageUsers;
import models.User;

/**
 * Servlet implementation class GetUserInfo
 */
@WebServlet("/GetUserInfo")
public class GetUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserInfo() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		User target = (User) session.getAttribute("target");
		ManageUsers userManager = new ManageUsers();
		//Get user in case its passed as an argument.
		if (session != null || user != null) {
			user = userManager.getUser(user.getUser());
			request.setAttribute("user",user);
			
			//Set target as both attr to avoid unnecessary changes on the view.
			if(target != null && (target.getUser() != user.getUser())) {
				request.setAttribute("target",target);
				request.setAttribute("user",target);
				request.setAttribute("follow",false);
				if(userManager.ifFollowedUser(user.getUser(), target.getUser()))
					request.setAttribute("follow",true);
			}
		}
		
		userManager.finalize();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewUserInfo.jsp"); 
		dispatcher.include(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}