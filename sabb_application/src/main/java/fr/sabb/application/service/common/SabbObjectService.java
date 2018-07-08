package fr.sabb.application.service.common;

import java.util.List;
import java.util.Optional;

import fr.sabb.application.ValidationException;

public interface SabbObjectService<T> {

	List<T> getAll();
	
	void updateOrInsert(T t) throws ValidationException;
	
	void delete(T t);
	
	Optional<T> getById(int id);
}
