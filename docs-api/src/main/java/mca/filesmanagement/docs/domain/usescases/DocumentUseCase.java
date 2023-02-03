package mca.filesmanagement.docs.domain.usescases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import mca.filesmanagement.docs.commons.DocumentDto;
import mca.filesmanagement.docs.commons.DocumentNewDto;
import mca.filesmanagement.docs.port.in.DocumentCreationException;
import mca.filesmanagement.docs.port.in.IDocumentUseCase;
import mca.filesmanagement.docs.port.out.IDocumentBinaryRepositorio;
import mca.filesmanagement.docs.port.out.IDocumentRepository;

@Service
public class DocumentUseCase implements IDocumentUseCase {

	@Autowired
	private IDocumentBinaryRepositorio documentBinaryRepositorio;

	@Autowired
	private IDocumentRepository documentRepository;

	/** Constructor por defecto. */
	public DocumentUseCase() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long createDocument(DocumentNewDto documentDto)
			throws DocumentCreationException {
		try {
			long id = this.documentRepository.createDocument(documentDto);
			DocumentDto docSave = this.documentRepository.getById(id);
			String url = this.documentBinaryRepositorio
					.createOrUpdate(docSave.getCode(), Base64Utils
							.decodeFromString(documentDto.getContentBase64()));
			this.documentRepository.active(id, url);
			return id;
		} catch (Exception e) {
			throw new DocumentCreationException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DocumentDto findById(long id) {
		return this.documentRepository.getById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DocumentDto findByCode(String uuid) {
		return this.documentRepository.getByCode(uuid);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteByCode(String uuid) {
		DocumentDto document = this.documentRepository.getByCode(uuid);
		this.documentRepository.deleteById(document.getId());
		this.documentBinaryRepositorio.delete(document.getUrl());
	}
}
