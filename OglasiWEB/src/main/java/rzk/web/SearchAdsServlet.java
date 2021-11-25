package rzk.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Oglas;
import rzk.AdBeanRemote;

@WebServlet("/SearchAdsServlet")
public class SearchAdsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SearchAdsServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String text = request.getParameter("text");
		AdBeanRemote bean = (AdBeanRemote) request.getSession().getAttribute("bean");
		List<Oglas> ads = bean.searchAds(text);
		request.setAttribute("ads", ads);
		request.getRequestDispatcher("searchAds.jsp").forward(request, response);
	}

}
