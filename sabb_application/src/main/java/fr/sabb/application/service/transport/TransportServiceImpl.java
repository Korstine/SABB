package fr.sabb.application.service.transport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sabb.application.data.mapper.SabbMapper;
import fr.sabb.application.data.mapper.TransportMapper;
import fr.sabb.application.data.object.Transport;
import fr.sabb.application.service.SabbObjectServiceImpl;

@Service
public class TransportServiceImpl extends SabbObjectServiceImpl<Transport>implements TransportService {

	@Autowired
	private TransportMapper mapper;
	
	@Override
	public SabbMapper<Transport> getMapper() {
		return mapper;
	}


}
