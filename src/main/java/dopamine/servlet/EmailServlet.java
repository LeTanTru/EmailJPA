package dopamine.servlet;

import dopamine.dao.UserDAO;
import dopamine.dao.impl.UserDAOImpl;
import dopamine.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Servlet implementation class EmailServlet
 */
@WebServlet("/email-list")
public class EmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserDAO userDAO = new UserDAOImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "/home.jsp";

		GregorianCalendar currentDate = new GregorianCalendar();
		int currentYear = currentDate.get(Calendar.YEAR);
		request.setAttribute("currentYear", currentYear);

		String action = request.getParameter("action");
		if (action == null) {
			action = "join";
		}
		if (action.equals("join")) {
			url = "views/home.jsp";
		}
		else if (action.equals("add")) {
			
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");

			User user = new User(firstName, lastName, email);

			String message;
			if (firstName == null || lastName == null || email == null ||
					firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
				message = "Please fill out all fields";
				url = "views/home.jsp";
			} else if (userDAO.emailExists(user.getEmail())) {
				message = "This email already exists.<br>Please enter another email address.";
				url = "views/home.jsp";
			} else {
				message = "";
				url = "views/thanks.jsp";
				userDAO.insert(user);
			}
			request.setAttribute("user", user);
			request.setAttribute("message", message);
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}
