package fr.sabb.application.data.object;

public class Official extends SabbObject {
	private int id;
	private Match match;
	private Team teamTable;
	private Team teamReferee;
	
	public Official() {
		super();
	}
	
	
	public Official(Match match) {
		super();
		this.match = match;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the match
	 */
	public Match getMatch() {
		return match;
	}
	/**
	 * @param match the match to set
	 */
	public void setMatch(Match match) {
		this.match = match;
	}
	/**
	 * @return the teamTable
	 */
	public Team getTeamTable() {
		return teamTable;
	}
	/**
	 * @param teamTable the teamTable to set
	 */
	public void setTeamTable(Team teamTable) {
		this.teamTable = teamTable;
	}
	/**
	 * @return the teamReferee
	 */
	public Team getTeamReferee() {
		return teamReferee;
	}
	/**
	 * @param teamReferee the teamReferee to set
	 */
	public void setTeamReferee(Team teamReferee) {
		this.teamReferee = teamReferee;
	}
}
