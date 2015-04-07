package at.ac.tuwien.big.we15.lab2.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BigJeopardyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("TODO: Something");
		System.out.println("Parameter registerButton: " + request.getParameter("registerButton"));
		System.out.println("Parameter signInButton: " + request.getParameter("signInButton"));
	}
}
