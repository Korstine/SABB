package fr.sabb.application.service.assocation;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

	@Override
	public List<Association> getAllActive() {
		return getAll().stream().filter(Association::isActive).collect(Collectors.toList());
	}

}
