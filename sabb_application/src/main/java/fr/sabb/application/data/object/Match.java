package fr.sabb.application.data.object;

import java.sql.Timestamp;

public class Match extends SabbObject {
	private int id;
	private String opponent;
	private Team team;
	private Timestamp matchDate;
	private int idFFBB;
	private boolean home;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the opponent
	 */
	public String getOpponent() {
		return opponent;
	}

	/**
	 * @param opponent
	 *            the opponent to set
	 */
	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}

	/**
	 * @return the team
	 */
	public Team getTeam() {
		return team;
	}

	/**
	 * @param team
	 *            the team to set
	 */
	public void setTeam(Team team) {
		this.team = team;
	}

	/**
	 * @return the matchDate
	 */
	public Timestamp getMatchDate() {
		return matchDate;
	}

	/**
	 * @param matchDate the matchDate to set
	 */
	public void setMatchDate(Timestamp matchDate) {
		this.matchDate = matchDate;
	}

	/**
	 * @return the idFFBB
	 */
	public int getIdFFBB() {
		return idFFBB;
	}

	/**
	 * @param idFFBB the idFFBB to set
	 */
	public void setIdFFBB(int idFFBB) {
		this.idFFBB = idFFBB;
	}

	/**
	 * @return the home
	 */
	public boolean isHome() {
		return home;
	}

	/**
	 * @param home the home to set
	 */
	public void setHome(boolean home) {
		this.home = home;
	}

}
