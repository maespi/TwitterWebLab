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
 * Servlet implementation class GetOwnTimeline
 */
@WebServlet("/GetOwnTimeline")
public class GetOwnTimeline extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOwnTimeline() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String target = request.getParameter("target");
		User user = (User) session.getAttribute("user");
		User targetUser = new User();
		ManageUsers userManager = new ManageUsers();
		
		boolean own = Boolean.parseBoolean(request.getParameter("own"));
		
		if(own)
			target = user.getUser();
		
		//If target exists in the db, is set as a session attr.
		if (target != null) {
			targetUser = userManager.getUser(target);
			//In case target exists, get user and if its correct check follow
			if(targetUser != null) {
				session.setAttribute("target",targetUser);
			}else{
				request.setAttribute("error", true);
				request.setAttribute("error_msg", "User does not exist.");
			}
		}else
			session.setAttribute("target", null);
		
		userManager.finalize();
		RequestDispatcher dispatcher = request.getRequestDispatcher("ViewOwnTimeline.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
