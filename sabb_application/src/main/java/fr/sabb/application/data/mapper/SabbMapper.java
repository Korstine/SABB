package fr.sabb.application.data.mapper;

import java.util.List;

import fr.sabb.application.data.object.SabbObject;

public interface SabbMapper<T extends SabbObject> {

	List<T> getAll();

	void insert(T object);

	void delete(T object);

	void update(T object);
}
