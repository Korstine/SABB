package fr.sabb.application.service;

import java.util.List;
import java.util.Optional;

import fr.sabb.application.ValidationException;
import fr.sabb.application.data.mapper.SabbMapper;
import fr.sabb.application.data.object.SabbObject;

public abstract class SabbObjectServiceImpl<T extends SabbObject> implements SabbObjectService<T> {

	public abstract SabbMapper<T> getMapper();

	@Override
	public List<T> getAll() {
		return getMapper().getAll();
	}

	@Override
	public void updateOrInsert(T object) throws ValidationException {
		if (object.isPersisted()) {
			getMapper().update(object);
		} else {
			getMapper().insert(object);
		}

	}

	@Override
	public void delete(T object) {
		getMapper().delete(object);
	}

	@Override
	public Optional<T> getById(int id) {
		return this.getAll().stream().filter(s -> s.getId() == id).findFirst();
	}
}
