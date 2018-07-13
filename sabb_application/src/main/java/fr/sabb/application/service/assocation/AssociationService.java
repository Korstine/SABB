package fr.sabb.application.service.assocation;

import java.util.List;

import fr.sabb.application.data.object.Association;
import fr.sabb.application.service.SabbObjectService;

public interface AssociationService extends SabbObjectService<Association>{

	List<Association> getAllActive();
}
