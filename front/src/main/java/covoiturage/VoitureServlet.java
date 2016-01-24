package covoiturage;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
	private final Calendar cal = Calendar.getInstance();
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy");

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		CalendrierBuilder builder = new CalendrierBuilder();

		TarifsDao dao = new TarifsDao();
		List<Tarification> tarifs = dao.getTarifs();
		req.setAttribute("tarifs", tarifs);
		dao.close();

		int month = getMonth(req);
		req.setAttribute("actualMonth", month);
		
		String formattedMonth = dateFormat.format(cal.getTime());
		req.setAttribute("month", formattedMonth);

		Calendrier calendrier = builder.build(cal);
		req.setAttribute("calendrier", calendrier);

		double total = calendrier.getTarif(tarifs, month);
		req.setAttribute("total", total);

		CalendrierJspHelper helper = new CalendrierJspHelper();
		req.setAttribute("helper", helper);

		String nextJSP = "/index.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(req, resp);
	}

	private int getMonth(HttpServletRequest req) {
		String currentMonth = req.getParameter("month");
		int sens;
		if (currentMonth != null) {
			sens = Integer.parseInt(currentMonth);
			cal.add(Calendar.MONTH, sens);
		}
		return cal.get(Calendar.MONTH);
	}

}
