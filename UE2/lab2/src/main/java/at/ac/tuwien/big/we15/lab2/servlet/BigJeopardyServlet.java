package at.ac.tuwien.big.we15.lab2.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import at.ac.tuwien.big.we15.lab2.api.Category;
import at.ac.tuwien.big.we15.lab2.api.QuestionDataProvider;
import at.ac.tuwien.big.we15.lab2.api.impl.JSONQuestionDataProvider;
import at.ac.tuwien.big.we15.lab2.api.impl.JeopardyBean;
import at.ac.tuwien.big.we15.lab2.api.impl.JeopardyGame;
import at.ac.tuwien.big.we15.lab2.api.impl.ServletJeopardyFactory;
import at.ac.tuwien.big.we15.lab2.api.impl.SimpleJeopardyFactory;

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
			HttpSession session = request.getSession();
			JeopardyBean bean = (JeopardyBean)session.getAttribute("jeopardyBean");
			if(bean == null) {
				bean = new JeopardyBean();
				ServletJeopardyFactory factory = new ServletJeopardyFactory(getServletContext());
				QuestionDataProvider provider = factory.createQuestionDataProvider();
				List<Category> categories = provider.getCategoryData();
				Random random = new Random();
				int randomCategoryNumber = random.nextInt() % categories.size();
				System.out.println(randomCategoryNumber);
				Category category = categories.get(randomCategoryNumber);
				JeopardyGame game = new JeopardyGame(category.getQuestions(), category);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jeopardy.jsp");
				dispatcher.forward(request, response);
				session.setAttribute("jeopardyBean", bean);
			}
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
