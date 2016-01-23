package covoiturage.calendrier;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import covoiturage.db.DaysDao;

public class CalendrierBuilder {

	public Calendrier build(int week, int year) {

		Calendrier calendrier = new Calendrier();
		List<Semaine> semaines = new ArrayList<>();

		Semaine semaine = new Semaine(week, year);
		semaine.setJours(buildDaysOfWeek(week));

		Semaine previous1 = semaine.previous();
		previous1.setJours(buildDaysOfWeek(week - 1));

		Semaine previous2 = previous1.previous();
		previous2.setJours(buildDaysOfWeek(week - 2));

		Semaine previous3 = previous2.previous();
		previous3.setJours(buildDaysOfWeek(week - 3));

		semaines.add(previous3);
		semaines.add(previous2);
		semaines.add(previous1);
		semaines.add(semaine);

		calendrier.setSemaines(semaines);
		return calendrier;
	}

	private List<Jour> buildDaysOfWeek(int week) {
		List<Jour> jours = new ArrayList<>();
		DaysDao dao = new DaysDao();
		jours.add(buildJour(week, 0, dao));
		jours.add(buildJour(week, 1, dao));
		jours.add(buildJour(week, 2, dao));
		jours.add(buildJour(week, 3, dao));
		jours.add(buildJour(week, 4, dao));
		jours.add(buildJour(week, 5, dao));
		jours.add(buildJour(week, 6, dao));
		dao.close();
		Collections.sort(jours);
		return jours;
	}

	private Jour buildJour(int week, int offset, DaysDao dao) {
		Calendar current = Calendar.getInstance();
		current.set(Calendar.WEEK_OF_YEAR, week);
		current.set(Calendar.DAY_OF_WEEK, offset);
		Jour jour = dao.find(current);
		return jour;
	}

}
