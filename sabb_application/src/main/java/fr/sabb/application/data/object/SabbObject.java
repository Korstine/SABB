package fr.sabb.application.data.object;

public abstract class SabbObject {

	public abstract int getId();
	
	public boolean isPersisted() {
		return getId() != 0;
	}
}
