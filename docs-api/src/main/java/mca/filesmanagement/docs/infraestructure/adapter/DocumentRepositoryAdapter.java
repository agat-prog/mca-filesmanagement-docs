package mca.filesmanagement.docs.infraestructure.adapter;

import java.util.Date;
import java.util.UUID;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mca.filesmanagement.docs.commons.DocumentDto;
import mca.filesmanagement.docs.commons.DocumentNewDto;
import mca.filesmanagement.docs.infraestructure.model.DocumentEntity;
import mca.filesmanagement.docs.infraestructure.repository.JpaDocumentRepository;
import mca.filesmanagement.docs.port.out.IDocumentRepository;

@Service
public class DocumentRepositoryAdapter implements IDocumentRepository{

	@Autowired
	private JpaDocumentRepository jpaDocumentRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public DocumentRepositoryAdapter() {
		super();
	}

	@Override
	@Transactional
	public long createDocument(DocumentNewDto documentDto) {
		DocumentEntity entity = new DocumentEntity();
		entity.setCode(UUID.randomUUID().toString());
		entity.setCreationDate(new Date());
		entity.setCreationUser(documentDto.getCreationUser());
		entity.setUpdateDate(new Date());
		entity.setUpdateUser(documentDto.getCreationUser());
		entity.setActive(false);
		entity.setName(documentDto.getName());
		entity = this.jpaDocumentRepository.save(entity);
		return entity.getId();
	}

	@Override
	@Transactional
	public DocumentDto getById(long id) {
		return this.modelMapper.map(jpaDocumentRepository.findById(id).orElseThrow(), DocumentDto.class);
	}
	
	@Override
	@Transactional
	public DocumentDto getByCode(String code) {
		return this.modelMapper.map(jpaDocumentRepository.findByCode(code), DocumentDto.class);
	}

	@Override
	@Transactional
	public void active(long id, String URL) {
		this.jpaDocumentRepository.activeDocument(id, URL);
	}
	
	@Override
	@Transactional	
	public void deleteById(long id) {
		this.jpaDocumentRepository.deleteById(id);
	}
}
