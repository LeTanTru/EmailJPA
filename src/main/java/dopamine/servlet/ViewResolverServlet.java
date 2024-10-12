package dopamine.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ViewResolverServlet
 */
@WebServlet(urlPatterns = {"/views/*", "/css/*", "/js/*"})
public class ViewResolverServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewResolverServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        String resourcePath = path.substring(request.getContextPath().length());

        // Map the resource path to the WEB-INF directory
        if (resourcePath.startsWith("/views/") || resourcePath.startsWith("/css/") || resourcePath.startsWith("/js/")) {
            // Forward the request to the resource in WEB-INF
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF" + resourcePath);
            dispatcher.forward(request, response);
        } else {
            // Resource not found
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
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
