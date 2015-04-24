package at.ac.tuwien.big.we15.lab2.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import at.ac.tuwien.big.we15.lab2.api.Answer;
import at.ac.tuwien.big.we15.lab2.api.Category;
import at.ac.tuwien.big.we15.lab2.api.QuestionDataProvider;
import at.ac.tuwien.big.we15.lab2.api.impl.JSONQuestionDataProvider;
import at.ac.tuwien.big.we15.lab2.api.impl.JeopardyBean;
import at.ac.tuwien.big.we15.lab2.api.impl.JeopardyGame;
import at.ac.tuwien.big.we15.lab2.api.impl.ServletJeopardyFactory;
import at.ac.tuwien.big.we15.lab2.api.impl.SimpleJeopardyFactory;

public class BigJeopardyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private JeopardyGame game;
	
	private ArrayList<Integer> clickedButtonList = new ArrayList<Integer>();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("action") == null)
			return;
		
		if(request.getParameter("action").compareTo("registerButtonClicked") == 0) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/register.jsp");
			dispatcher.forward(request, response);
		}
		
		if(request.getParameter("action").compareTo("logoutlinkButtonClicked") == 0) {
			// liste der geklickten buttons wird geloescht
			this.clickedButtonList.clear();
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}
		
		if(request.getParameter("action").compareTo("loginButtonClicked") == 0) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}
		
		if(request.getParameter("action").compareTo("restartButtonClicked") == 0) {
			this.clickedButtonList.clear();
			if(game != null)
			{
				game.restart();
			}
			
			
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
			//JeopardyBean bean = (JeopardyBean)session.getAttribute("jeopardyBean");
			//if(bean == null) {
				ServletJeopardyFactory factory = new ServletJeopardyFactory(getServletContext());
				QuestionDataProvider provider = factory.createQuestionDataProvider();
				List<Category> categories = provider.getCategoryData();
				Category category = categories.get(getCategory(request));
				game = new JeopardyGame(category.getQuestions(), category);
				JeopardyBean bean = new JeopardyBean();
				bean.setGame(game);
				session.setAttribute("jeopardyBean", bean);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jeopardy.jsp");
				dispatcher.forward(request, response);
			//}
		}else if(request.getParameter("action").compareTo("questionSubmitButtonClicked") == 0)
		{
			int selectedQuestion = 0;
			try{
				selectedQuestion = Integer.parseInt(request.getParameter("question_selection"));
			}catch(Exception e){}
			
			ServletJeopardyFactory factory = new ServletJeopardyFactory(getServletContext());
			QuestionDataProvider provider = factory.createQuestionDataProvider();
			List<Category> categories = provider.getCategoryData();
			Category category = categories.get(getCategory(request));
			game.setCategory(category);
			game.setQuestions(category.getQuestions());
			
			game.setCurrentEuroValue(selectedQuestion);
			// 
			JeopardyBean bean = (JeopardyBean)request.getSession().getAttribute("jeopardyBean");
			if(bean!=null) bean.addClickedButton(selectedQuestion);
			
			if(game.askedQuestionCount() > 10) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/winner.jsp");
				dispatcher.forward(request, response);
				return;
			} else {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/question.jsp");
				dispatcher.forward(request, response);
				return;
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
			List<String> selectedAnswerIds = new ArrayList<String>();
			try{
				selectedAnswerIds.addAll(Arrays.asList(request.getParameterValues("answers")));
			}catch(Exception e){}
			
			HttpSession session = request.getSession();
			JeopardyBean bean = (JeopardyBean)session.getAttribute("jeopardyBean");
			// true gibt an ob mensch oder nicht
			game.checkAnswers(selectedAnswerIds, bean.getCorrectAnswers(), true);
			game.makeAiSelections(bean.getCorrectAnswers());
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jeopardy.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	/**
	 * 1 - 4 TV; 5 - 9 ssd; 10 - 14 web; 15 - 18 web; 19 - 23 tuwien
	 * 
	 * @param request
	 * @return
	 */
	int getCategory(HttpServletRequest request)
	{
		
		int selectedQuestion = 0;
		try{
			selectedQuestion = Integer.parseInt(request.getParameter("question_selection"));
		}catch(Exception e){}
		
		int cat = 1;
		// 1 - 4 TV; 5 - 9 ssd; 10 - 14 web; 15 - 18 web; 19 - 23 tuwien
		if(selectedQuestion >= 1 && selectedQuestion <= 4)
			cat = 0;
		else if(selectedQuestion >= 5 && selectedQuestion <= 9)
			cat = 2;
		else if(selectedQuestion >= 10 && selectedQuestion <= 14)
			cat = 3;
		else if(selectedQuestion >= 15 && selectedQuestion <= 18)
			cat = 4;
		else if(selectedQuestion >= 19 && selectedQuestion <= 23)
			cat = 1;
		
		
		return cat;
	}
}
