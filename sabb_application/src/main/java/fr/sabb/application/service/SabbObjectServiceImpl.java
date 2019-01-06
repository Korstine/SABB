package fr.sabb.application.service;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

import javax.swing.Timer;

import fr.sabb.application.ValidationException;
import fr.sabb.application.data.mapper.SabbMapper;
import fr.sabb.application.data.object.SabbObject;

public abstract class SabbObjectServiceImpl<T extends SabbObject> implements SabbObjectService<T> {

	public abstract SabbMapper<T> getMapper();
	
	List<T> itemInCache;
	
	Timer timer = new Timer(1000, new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			itemInCache = null;
			timer.stop();
		}
	});

	@Override
	public List<T> getAll() {
		return getMapper().getAll();
	}
	
	@Override
	public List<T> getAllWithCaching() {
		if (itemInCache == null) {
			itemInCache = getMapper().getAll();
			timer.start();
		}
		return itemInCache;
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
