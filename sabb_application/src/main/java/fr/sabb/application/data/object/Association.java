package fr.sabb.application.data.object;

public class Association extends SabbObject {

	private int id;
	private String name;
	private boolean main;
	private boolean active;
	private String nameFfbb;
	private String nameFfbbCtc;
	private String ffbbLocation;
	private String ffbbLocationBis;

	/**
	 * @return the ffbbLocationBis
	 */
	public String getFfbbLocationBis() {
		return ffbbLocationBis;
	}

	/**
	 * @param ffbbLocationBis the ffbbLocationBis to set
	 */
	public void setFfbbLocationBis(String ffbbLocationBis) {
		this.ffbbLocationBis = ffbbLocationBis;
	}

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the main
	 */
	public boolean isMain() {
		return main;
	}

	/**
	 * @param main
	 *            the main to set
	 */
	public void setMain(boolean main) {
		this.main = main;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	public String toString() {
		return this.name;
	}

	/**
	 * @return the nameFfbb
	 */
	public String getNameFfbb() {
		return nameFfbb;
	}

	/**
	 * @param nameFfbb the nameFfbb to set
	 */
	public void setNameFfbb(String nameFfbb) {
		this.nameFfbb = nameFfbb;
	}

	/**
	 * @return the nameFfbbCtc
	 */
	public String getNameFfbbCtc() {
		return nameFfbbCtc;
	}

	/**
	 * @param nameFfbbCtc the nameFfbbCtc to set
	 */
	public void setNameFfbbCtc(String nameFfbbCtc) {
		this.nameFfbbCtc = nameFfbbCtc;
	}

	/**
	 * @return the ffbbLocation
	 */
	public String getFfbbLocation() {
		return ffbbLocation;
	}

	/**
	 * @param ffbbLocation the ffbbLocation to set
	 */
	public void setFfbbLocation(String ffbbLocation) {
		this.ffbbLocation = ffbbLocation;
	}

}
