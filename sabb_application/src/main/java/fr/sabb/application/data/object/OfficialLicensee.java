package fr.sabb.application.data.object;

public class OfficialLicensee {

	private Licensee licensee;

	private int numberOfficial;

	public OfficialLicensee(Licensee licensee, int numberOfficial) {
		super();
		this.licensee = licensee;
		this.numberOfficial = numberOfficial;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("%s : %s", licensee.toString(), numberOfficial);
	}

}
