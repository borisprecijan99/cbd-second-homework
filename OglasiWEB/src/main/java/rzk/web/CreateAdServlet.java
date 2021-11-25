package rzk.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rzk.AdBeanRemote;

@WebServlet("/CreateAdServlet")
public class CreateAdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateAdServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdBeanRemote bean = (AdBeanRemote) request.getSession().getAttribute("bean");
		String text = request.getParameter("text");
		boolean ok = bean.createAd(text);
		if (ok) {
			request.setAttribute("message", "Ad successfully created!");
			request.getRequestDispatcher("createAd.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "Error creating ad!");
			request.getRequestDispatcher("createAd.jsp").forward(request, response);
		}
	}

}
