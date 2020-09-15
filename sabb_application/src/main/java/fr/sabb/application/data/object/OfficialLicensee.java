package fr.sabb.application.data.object;

public class OfficialLicensee {

	private Licensee licensee;

	private int numberOfficial;
	
	private int score;

	public OfficialLicensee(Licensee licensee, int numberOfficial,int score) {
		super();
		this.licensee = licensee;
		this.numberOfficial = numberOfficial;
		this.score = score;
	}

	/**
	 * @return the licensee
	 */
	public Licensee getLicensee() {
		return licensee;
	}

	/**
	 * @param licensee
	 *            the licensee to set
	 */
	public void setLicensee(Licensee licensee) {
		this.licensee = licensee;
	}

	/**
	 * @return the numberOfficial
	 */
	public int getNumberOfficial() {
		return numberOfficial;
	}

	/**
	 * @param numberOfficial
	 *            the numberOfficial to set
	 */
	public void setNumberOfficial(int numberOfficial) {
		this.numberOfficial = numberOfficial;
	}
	
	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("%s : %s (%s)", licensee.toString(), numberOfficial, score);
	}

}
