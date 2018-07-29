package fr.sabb.application.data.object;

public class Team extends SabbObject {

	private int id;
	private String name;
	private Association association;
	private Category category;
	private Season season;
	private boolean active;
	private String ffbbUniqueId;
	private int sort;
	
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
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
	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	/**
	 * @return the ffbbUniqueId
	 */
	public String getFfbbUniqueId() {
		return ffbbUniqueId;
	}
	/**
	 * @param ffbbUniqueId the ffbbUniqueId to set
	 */
	public void setFfbbUniqueId(String ffbbUniqueId) {
		this.ffbbUniqueId = ffbbUniqueId;
	}
	/**
	 * @return the sort
	 */
	public int getSort() {
		return sort;
	}
	/**
	 * @param sort the sort to set
	 */
	public void setSort(int sort) {
		this.sort = sort;
	}
	
}