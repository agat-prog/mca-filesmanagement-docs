package mca.filesmanagement.docs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mca.filesmanagement.docs.commons.DocumentDto;
import mca.filesmanagement.docs.port.in.IDocumentUseCase;

@Service
public class DocService {

	@Autowired
	private IDocumentUseCase documentUseCase;
	
	public DocService() {
		super();
	}
	
	public DocumentDto findByCode(String uuid) {
		return this.documentUseCase.findByCode(uuid);
	}
}
