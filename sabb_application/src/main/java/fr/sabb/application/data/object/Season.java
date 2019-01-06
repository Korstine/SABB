package fr.sabb.application.data.object;

public class Season  extends SabbObject{

	private int id;
	private String name;
	private boolean active;
	private int referenceYear;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	public void setId(int id) {
		this.id = id;
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
	
	public String toString() {
		return this.name + (this.active ? "(Active)" : "");
	}
	/**
	 * @return the referenceYear
	 */
	public int getReferenceYear() {
		return referenceYear;
	}
	/**
	 * @param referenceYear the referenceYear to set
	 */
	public void setReferenceYear(int referenceYear) {
		this.referenceYear = referenceYear;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Season other = (Season) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
