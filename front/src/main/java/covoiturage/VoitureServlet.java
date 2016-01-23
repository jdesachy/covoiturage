package covoiturage;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import covoiturage.calendrier.Calendrier;
import covoiturage.calendrier.CalendrierBuilder;
import covoiturage.db.Tarification;
import covoiturage.db.TarifsDao;

public class VoitureServlet extends HttpServlet {

	private static final long serialVersionUID = 8176377552616526301L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Calendar cal = Calendar.getInstance();
		CalendrierBuilder builder = new CalendrierBuilder();

		TarifsDao dao = new TarifsDao();
		List<Tarification> tarifs = dao.getTarifs();
		req.setAttribute("tarifs", tarifs);
		dao.close();
		
		int week = getWeek(req, cal);
		req.setAttribute("week", week);

		Calendrier calendrier = builder.build(week, cal.get(Calendar.YEAR));
		req.setAttribute("calendrier", calendrier);

		CalendrierJspHelper helper = new CalendrierJspHelper();
		req.setAttribute("helper", helper);
		
		calendrier.toString();
		String nextJSP = "/index.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(req, resp);
	}

	private int getWeek(HttpServletRequest req, Calendar cal) {
		String currentWeek = req.getParameter("week");
		int week;
		if (currentWeek != null) {
			week = Integer.parseInt(currentWeek);
		} else {
			week = cal.get(Calendar.WEEK_OF_YEAR);
		}
		return week;
	}

}
