package rzk.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rzk.AdBeanRemote;

@WebServlet("/RespondToAdServlet")
public class RespondToAdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RespondToAdServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdBeanRemote bean = (AdBeanRemote) request.getSession().getAttribute("bean");
		int id = Integer.parseInt(request.getParameter("adId"));
		String text = request.getParameter("text");
		boolean ok = bean.respondToAd(id, text);
		if (ok) {
			request.getRequestDispatcher("searchAds.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "Error responding to ad!");
			request.getRequestDispatcher("respondToAd.jsp").forward(request, response);
		}
	}

}
