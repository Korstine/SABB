package fr.sabb.application.business;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.sabb.application.ValidationException;
import fr.sabb.application.data.object.Match;
import fr.sabb.application.data.object.Team;
import fr.sabb.application.service.match.MatchService;
import fr.sabb.application.service.team.TeamService;

@Component
public class MatchFillerBusiness {

	@Autowired
	private MatchService matchService;
	
	@Autowired
	private TeamService teamService;
	
	private int index = 0;
	private String dateStr = null;
	private Team team;
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyyHH:mm");

	public void reloadGameFromFFBB(Team team) throws Exception {

		this.team = team;
		if (this.team.getFfbbUniqueId() == null) {
			throw new ValidationException("Definissez au préalable l'identifiant unique ffbb");
		}
		Document doc = Jsoup.connect("http://resultats.ffbb.com/championnat/equipe/division/" + team.getFfbbUniqueId() + ".html").get();
		
		doc.getElementsByClass("altern-2").stream().forEach(this::generateMatchFromFFBBElements);
		doc.getElementsByClass("no-altern-2").stream().forEach(this::generateMatchFromFFBBElements);
	}
	
	public void reloadGameFromFFBBForAllTeam() throws Exception {
		teamService.getAllActiveForCurrentSeason().forEach(t -> {
			try {
				reloadGameFromFFBB(t);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	private void generateMatchFromFFBBElements(Element matchElements) {
		
		if (matchElements.childNodeSize() != 7) {
			return;
		}
		Match match = new Match();
		match.setTeam(this.team);
		index = 0;
		dateStr = null;
		matchElements.getAllElements().stream().forEach(e->{
			switch (index) {
				case 1:
					match.setIdFFBB(Integer.valueOf(e.text()));
					break;
				case 2:
					dateStr = e.text();
					break;
				case 3:
					match.setMatchDate(Timestamp.valueOf(LocalDateTime.parse(dateStr + e.text(), formatter)));
					break;
				case 4:
					if (e.text().contains(team.getAssociation().getNameFfbb())) {
						match.setHome(true);
					} else {
						match.setHome(false);
						match.setOpponent(e.text());
					}
					break;
				case 6:
					if (match.isHome()) {
						match.setOpponent(e.text());
					}
					break;
				default:
					break;
			}
			index++;
		});
		try {
			matchService.updateOrInsert(match);
		} catch (ValidationException e) {
			e.printStackTrace();
		}
	}
}
