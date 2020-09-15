package fr.sabb.application.data.object;

import java.sql.Timestamp;

public class Official extends SabbObject {
	private int id;
	private Match match;
	private Team teamTable;
	private Team teamReferee;
	private Licensee licenseeTable1;
	private Licensee licenseeTable2;
	private Licensee licenseeReferee1;
	private Licensee licenseeReferee2;
	
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


	/**
	 * @return the licenseeTable1
	 */
	public Licensee getLicenseeTable1() {
		return licenseeTable1;
	}


	/**
	 * @param licenseeTable1 the licenseeTable1 to set
	 */
	public void setLicenseeTable1(Licensee licenseeTable1) {
		this.licenseeTable1 = licenseeTable1;
	}


	/**
	 * @return the licenseeTable2
	 */
	public Licensee getLicenseeTable2() {
		return licenseeTable2;
	}


	/**
	 * @param licenseeTable2 the licenseeTable2 to set
	 */
	public void setLicenseeTable2(Licensee licenseeTable2) {
		this.licenseeTable2 = licenseeTable2;
	}


	/**
	 * @return the licenseeReferee1
	 */
	public Licensee getLicenseeReferee1() {
		return licenseeReferee1;
	}


	/**
	 * @param licenseeReferee1 the licenseeReferee1 to set
	 */
	public void setLicenseeReferee1(Licensee licenseeReferee1) {
		this.licenseeReferee1 = licenseeReferee1;
	}


	/**
	 * @return the licenseeReferee2
	 */
	public Licensee getLicenseeReferee2() {
		return licenseeReferee2;
	}


	/**
	 * @param licenseeReferee2 the licenseeReferee2 to set
	 */
	public void setLicenseeReferee2(Licensee licenseeReferee2) {
		this.licenseeReferee2 = licenseeReferee2;
	}
	
}
