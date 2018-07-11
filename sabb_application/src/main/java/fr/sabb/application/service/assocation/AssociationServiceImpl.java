package fr.sabb.application.service.assocation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sabb.application.data.mapper.AssociationMapper;
import fr.sabb.application.data.mapper.SabbMapper;
import fr.sabb.application.data.object.Association;
import fr.sabb.application.service.SabbObjectServiceImpl;

@Service
public class AssociationServiceImpl extends SabbObjectServiceImpl<Association> implements AssociationService {

	@Autowired
	private AssociationMapper mapper;

	@Override
	public SabbMapper<Association> getMapper() {
		return mapper;
	}

}
