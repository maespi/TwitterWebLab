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
 * Servlet implementation class EditUser
 */
@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Get Main Data Through request and session.
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		ManageUsers userManager = new ManageUsers();
		user = userManager.getFullUser(user.getUser());
		session.setAttribute("target",null);
		request.setAttribute("user", user);
		
		boolean edit = false;
		if(request.getParameter("edit") != null)
			edit = Boolean.parseBoolean(request.getParameter("edit"));
		
		String view = "/ViewEditUser.jsp";
		//If it has not been an edit on user, pass the normal data to show.
		if(!edit) {
			request.setAttribute("tmp", user);
			//In Case of user edition, test if new info is correct
		}else if(edit) {
			String mail = request.getParameter("mail");
			String pwd = request.getParameter("pwd");
			User tmp = user;
			tmp.setPwd(pwd);
			tmp.setMail(mail);
			if(!tmp.isFull()) {
				request.setAttribute("error", true);
				if(tmp.getError()[1]==1) {
					request.setAttribute("error_msg", "Incorrect Mail.");
				}else if(tmp.getError()[2]== 1) {
					request.setAttribute("error_msg", "Incorrect Password.");
				}else {
					request.setAttribute("error_msg", "Unknown Error.");
				}
				request.setAttribute("tmp", new User(user.getUser(),pwd,mail));
			}else if(tmp.isFull()) {
				userManager.UpdateUser(tmp);
				view = "ViewOwnTimeline.jsp";
			}
		}
		
		
		userManager.finalize();
		RequestDispatcher dispatcher = request.getRequestDispatcher(view); 
		dispatcher.include(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
