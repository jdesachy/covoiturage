package covoiturage;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import covoiturage.db.Tarification;
import covoiturage.db.TarifsDao;

public class SettingsServlet extends HttpServlet {

	private static final long serialVersionUID = 4930246955023712721L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TarifsDao dao = new TarifsDao();
		List<Tarification> tarifs = dao.getTarifs();
		dao.close();
		req.setAttribute("tarifs", tarifs);

		String nextJSP = "/settings.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String value = req.getParameter("value");
		TarifsDao dao = new TarifsDao();
		dao.insert(new Tarification(name, Double.valueOf(value)));
		List<Tarification> tarifs = dao.getTarifs();
		dao.close();

		req.setAttribute("tarifs", tarifs);
		String nextJSP = "/settings.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(req, resp);
	}

}
