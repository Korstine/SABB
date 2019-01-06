package fr.sabb.application.service;

import java.util.List;
import java.util.Optional;

import fr.sabb.application.ValidationException;

public interface SabbObjectService<T> {

	List<T> getAll();
	
	List<T> getAllWithCaching();
	
	void updateOrInsert(T t) throws ValidationException;
	
	void delete(T t);
	
	Optional<T> getById(int id);
}
