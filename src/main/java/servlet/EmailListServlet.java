package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserModel;

@WebServlet("/email-list")
public class EmailListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8526924901998113540L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "/index.jsp";

		// get current action
		String action = request.getParameter("action");
		if (action == null) {
			action = "join"; // default action
		}
		// perform action and set URL to appropriate page
		if (action.equals("join")) {
			url = "/index.jsp"; // the "join" page
		} else if (action.equals("add")) {
			// get parameters from the request
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");

			// store data in User object and save User object in db
			UserModel user = new UserModel(firstName, lastName, email);
//			UserDB.insert(user);

			// set User object in request object and set URL
			if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || firstName == null || lastName == null
					|| email == null) {
				request.setAttribute("msg", "Please fill all fields !");
				request.setAttribute("user", user);
			} else {
				request.setAttribute("user", user);
				url = "/views/thanks.jsp"; // the "thanks" page
			}
		}

		System.out.println(action);
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
