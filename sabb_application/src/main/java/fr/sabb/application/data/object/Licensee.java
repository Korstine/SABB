package fr.sabb.application.data.object;

import java.sql.Timestamp;

public class Licensee extends SabbObject {
	
	private int id;
	private Team team;
	private Category category;
	private String numLicensee;
	private String name;
	private String firstname;
	private String phone;
	private String mail;
	private String adress;
	private Timestamp dateOfBirth;
	private String sex;
	private Association association;
	private Season season;

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
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return the numLicensee
	 */
	public String getNumLicensee() {
		return numLicensee;
	}

	/**
	 * @param numLicensee the numLicensee to set
	 */
	public void setNumLicensee(String numLicensee) {
		this.numLicensee = numLicensee;
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
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname
	 *            the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail
	 *            the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the adress
	 */
	public String getAdress() {
		return adress;
	}

	/**
	 * @param adress
	 *            the adress to set
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}

	/**
	 * @return the dateOfBirth
	 */
	public Timestamp getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Timestamp dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("%s %s", firstname, name);
	}

	/**
	 * @return the association
	 */
	public Association getAssociation() {
		return association;
	}
	
	/**
	 * @param association the association to set
	 */
	public void setAssociation(Association association) {
		this.association = association;
	}
	
	/**
	 * @return the season
	 */
	public Season getSeason() {
		return season;
	}

	/**
	 * @param season the season to set
	 */
	public void setSeason(Season season) {
		this.season = season;
	}

	public boolean isMainAssociation() {
		return association != null && association.isMain(); 
	}
	
	public boolean isSeasonActif() {
		return season != null && season.isActive();
	}
	
	public String toStringForListingAG(int count, boolean isParent) {
        StringBuilder line = new StringBuilder();
        line.append(count).append(";");
        line.append(this.name).append(" " + this.firstname).append(";");
        line.append(isParent ? "parent" : "").append(";");
        line.append("").append(";");
        line.append(this.numLicensee).append(";").append("\n");

        return line.toString();
    }
	
}
