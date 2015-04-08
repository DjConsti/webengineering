package at.ac.tuwien.big.we15.lab2.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BigJeopardyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("action") == null)
			return;
		
		if(request.getParameter("action").compareTo("registerButtonClicked") == 0) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/register.jsp");
			dispatcher.forward(request, response);
		}
		
		if(request.getParameter("action").compareTo("logoutlinkButtonClicked") == 0) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}
		
		if(request.getParameter("action").compareTo("loginButtonClicked") == 0) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}
		
		if(request.getParameter("action").compareTo("restartButtonClicked") == 0) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jeopardy.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("action") == null)
			return;
		
		if(request.getParameter("action").compareTo("signInButtonClicked") == 0) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jeopardy.jsp");
			dispatcher.forward(request, response);
		}
		
		if(request.getParameter("action").compareTo("questionSubmitButtonClicked") == 0) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/question.jsp");
			dispatcher.forward(request, response);
		}
		
		if(request.getParameter("action").compareTo("registerButtonClicked") == 0) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jeopardy.jsp");
			dispatcher.forward(request, response);
		}
		
		if(request.getParameter("action").compareTo("submitButtonClicked") == 0) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/winner.jsp");
			dispatcher.forward(request, response);
		}
	}
}
