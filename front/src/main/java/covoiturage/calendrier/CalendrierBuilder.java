package covoiturage.calendrier;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import covoiturage.db.DaysDao;

public class CalendrierBuilder {

	public Calendrier build(Calendar c) {
		Calendar workingCal = Calendar.getInstance();
		workingCal.setTime(c.getTime());

		workingCal.set(Calendar.DAY_OF_MONTH, 1);
		int month = workingCal.get(Calendar.MONTH);

		moveCalToFirstDayOfWeek(workingCal);
		Calendrier calendrier = new Calendrier();
		List<Semaine> semaines = new ArrayList<>();

		int week;
		int year;
		int curMonth = month;
		while (curMonth == month) {
			week = workingCal.get(Calendar.WEEK_OF_YEAR);
			year = workingCal.get(Calendar.YEAR);
			Semaine semaine = new Semaine(week, year);
			semaine.setJours(buildDaysOfWeek(workingCal));
			semaines.add(semaine);

			curMonth = workingCal.get(Calendar.MONTH);
		}

		calendrier.setSemaines(semaines);
		return calendrier;
	}

	private void moveCalToFirstDayOfWeek(Calendar c) {
		while (c.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			c.add(Calendar.DAY_OF_WEEK, -1);
		}
	}

	private List<Jour> buildDaysOfWeek(Calendar c) {
		List<Jour> jours = new ArrayList<>();
		DaysDao dao = new DaysDao();
		jours.add(dao.find(c));
		c.add(Calendar.DAY_OF_MONTH, 1);
		jours.add(dao.find(c));
		c.add(Calendar.DAY_OF_MONTH, 1);
		jours.add(dao.find(c));
		c.add(Calendar.DAY_OF_MONTH, 1);
		jours.add(dao.find(c));
		c.add(Calendar.DAY_OF_MONTH, 1);
		jours.add(dao.find(c));
		c.add(Calendar.DAY_OF_MONTH, 1);
		jours.add(dao.find(c));
		c.add(Calendar.DAY_OF_MONTH, 1);
		jours.add(dao.find(c));
		c.add(Calendar.DAY_OF_MONTH, 1);
		dao.close();
		Collections.sort(jours);
		return jours;
	}

}
