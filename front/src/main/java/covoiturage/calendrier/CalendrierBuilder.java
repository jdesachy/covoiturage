package covoiturage.calendrier;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import covoiturage.CalendrierPrinter;
import covoiturage.db.DaysDao;

public class CalendrierBuilder {

	public Calendrier build(int month, int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.YEAR, year);
		CalendrierPrinter.show("build", cal);
		int curMonth = month;
		Calendrier calendrier = new Calendrier();
		List<Semaine> semaines = new ArrayList<>();

		int week = cal.get(Calendar.WEEK_OF_YEAR);
		year = cal.get(Calendar.YEAR);
		
		while (curMonth == month) {
			Semaine semaine = new Semaine(week, year);
			semaine.setJours(buildDaysOfWeek(week, month, year));
			semaines.add(semaine);

			cal.add(Calendar.WEEK_OF_YEAR, 1);
			week = cal.get(Calendar.WEEK_OF_YEAR);
			curMonth = cal.get(Calendar.MONTH);
		}

		calendrier.setSemaines(semaines);
		return calendrier;
	}

	private List<Jour> buildDaysOfWeek(int week, int month, int year) {
		List<Jour> jours = new ArrayList<>();
		DaysDao dao = new DaysDao();
		jours.add(buildJour(week, month, year, 0, dao));
		jours.add(buildJour(week, month, year, 1, dao));
		jours.add(buildJour(week, month, year, 2, dao));
		jours.add(buildJour(week, month, year, 3, dao));
		jours.add(buildJour(week, month, year, 4, dao));
		jours.add(buildJour(week, month, year, 5, dao));
		jours.add(buildJour(week, month, year, 6, dao));
		dao.close();
		Collections.sort(jours);
		return jours;
	}

	private Jour buildJour(int week, int month, int year, int offset, DaysDao dao) {
		Calendar current = Calendar.getInstance();
		current.set(Calendar.WEEK_OF_YEAR, week);
		current.set(Calendar.DAY_OF_WEEK, offset);
		if (isLastWeekOfPreviousYear(week, month)) {
			current.set(Calendar.YEAR, year - 1);
		}
		CalendrierPrinter.show("buildJour", current);
		Jour jour = dao.find(current);
		return jour;
	}

	private boolean isLastWeekOfPreviousYear(int week, int month) {
		return month == 0 && week > 5;
	}

}
