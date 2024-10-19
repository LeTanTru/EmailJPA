package dopamine.servlet;

import dopamine.dao.impl.UserDAOImpl;
import dopamine.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Servlet implementation class EmailServlet
 */
@WebServlet("/email-list")
public class EmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserDAOImpl userDAO = new UserDAOImpl();


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

		String url = "/views/home.jsp";

		// get current action
		String action = request.getParameter("action");
		if (action == null) {
			action = "join";  // default action
		}

		// perform action and set URL to appropriate page
		if (action.equals("join")) {
			url = "/views/home.jsp";    // the "join" page
		}
		else if (action.equals("add")) {
			// get parameters from the request

			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");


			// store data in User object
			User user = new User();
			user.setEmail(email);
			user.setFirstName(firstName);
			user.setLastName(lastName);

			// validate the parameters
			String message;
			if (userDAO.emailExists(user.getEmail())) {
				message = "This email address already exists.<br>" +
						"Please enter another email address.";
				url = "/views/home.jsp";
			}
			else {
				message = "";
				url = "/views/thanks.jsp";

				userDAO.insert(user);
			}
			request.setAttribute("user", user);
			request.setAttribute("message", message);
		}

		getServletContext()
				.getRequestDispatcher(url)
				.forward(request, response);

	}
}
