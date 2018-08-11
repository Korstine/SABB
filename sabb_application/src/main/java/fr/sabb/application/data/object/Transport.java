package fr.sabb.application.data.object;

public class Transport extends SabbObject {
	private int id;
	private Match match;
	private Licensee licensee1;
	private Licensee licensee2;
	private Licensee licensee3;

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
	 * @return the match
	 */
	public Match getMatch() {
		return match;
	}

	/**
	 * @param match
	 *            the match to set
	 */
	public void setMatch(Match match) {
		this.match = match;
	}

	/**
	 * @return the licensee1
	 */
	public Licensee getLicensee1() {
		return licensee1;
	}

	/**
	 * @param licensee1
	 *            the licensee1 to set
	 */
	public void setLicensee1(Licensee licensee1) {
		this.licensee1 = licensee1;
	}

	/**
	 * @return the licensee2
	 */
	public Licensee getLicensee2() {
		return licensee2;
	}

	/**
	 * @param licensee2
	 *            the licensee2 to set
	 */
	public void setLicensee2(Licensee licensee2) {
		this.licensee2 = licensee2;
	}

	/**
	 * @return the licensee3
	 */
	public Licensee getLicensee3() {
		return licensee3;
	}

	/**
	 * @param licensee3
	 *            the licensee3 to set
	 */
	public void setLicensee3(Licensee licensee3) {
		this.licensee3 = licensee3;
	}

}
