package covoiturage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import covoiturage.calendrier.Jour;
import covoiturage.db.DaysDao;

public class UpdateServlet extends HttpServlet {

	private static final long serialVersionUID = -3028915421370174063L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String json = extractRequest(req);

		RequestReader requestReader = new RequestReader();
		Jour jour = requestReader.read(json);
		DaysDao dao = new DaysDao();
		dao.update(jour);
		dao.close();
	}

	private String extractRequest(HttpServletRequest req) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String json = "";
		if (br != null) {
			json = br.readLine();
		}
		return json;
	}

}
