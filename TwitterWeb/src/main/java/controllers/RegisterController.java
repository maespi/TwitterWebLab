package controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import managers.ManageUsers;
import models.User;

/**
 * Servlet implementation class FormController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	   System.out.println("RegisterController: ");
		
	   try {
	
		   User user = new User();
		   BeanUtils.populate(user, request.getParameterMap());
		   
		   if (user.isComplete()) {
			   boolean register = false;
			   ManageUsers manager = new ManageUsers();
			   System.out.println(" User ok, saving to DB");
			   register = manager.addUser(user.getUser(), user.getMail(), user.getPwd());
			   manager.finalize();
			   if(register) {
				  System.out.println(" User saved, forwarding to ViewLoginForm");
				  RequestDispatcher dispatcher = request.getRequestDispatcher("ViewLoginForm.jsp");
				  dispatcher.forward(request, response); 
			   }
			   else {
				   System.out.println(" User not registered, forwarding to ViewRegisterForm");
				   request.setAttribute("user",user);
				   RequestDispatcher dispatcher = request.getRequestDispatcher("ViewRegisterForm.jsp");
				   dispatcher.forward(request, response);
			   }
		   }
		   else {
			   System.out.println(" Forwarding to ViewRegisterForm");
			   request.setAttribute("user",user);
			   RequestDispatcher dispatcher = request.getRequestDispatcher("ViewRegisterForm.jsp");
			   dispatcher.forward(request, response);
		   }
		  
			   
	   } catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
	   }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
