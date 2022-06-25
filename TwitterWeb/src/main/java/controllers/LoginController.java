package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import managers.ManageUsers;
import models.User;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.print("LoginController: ");
		User user = new User();
		ManageUsers manager = new ManageUsers();
		String view = "ViewLoginForm.jsp";
		
	    try {
			
	    	BeanUtils.populate(user, request.getParameterMap());
	    	
	    	if (manager.isLoginComplete(user)) {
			    
			    if (manager.checkLogin(user)) {
		    		System.out.println("Login OK, forwarding to ViewOwnTimeline. ");
	    			HttpSession session = request.getSession();
	    			session.setAttribute("user",user);
			    	view = "ViewOwnTimeline.jsp";
					request.setAttribute("error", false);
	    			
	    		}
	    		else {
	    			System.out.println("User "+user.getUser()+" is not logged (user not found), forwarding to ViewLoginForm. ");
				    view = "ViewLoginForm.jsp";
					request.setAttribute("user",user);
					request.setAttribute("error", true);
					request.setAttribute("error_msg", "Incorrect Credentials.");
				}
		    } 
			else {
		     
				System.out.println("Login not completed, forwarding to ViewLoginForm. ");
				request.setAttribute("user",user);
				view = "ViewLoginForm.jsp";
				request.setAttribute("error", false);
		    	
		    }
	    	RequestDispatcher dispatcher = request.getRequestDispatcher(view);
	    	dispatcher.forward(request, response);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	    
	}
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

