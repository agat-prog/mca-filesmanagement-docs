package mca.filesmanagement.docs.port.in;

import mca.filesmanagement.docs.commons.DocumentDto;
import mca.filesmanagement.docs.commons.DocumentNewDto;

public interface IDocumentUseCase {
	long createDocument(DocumentNewDto documentDto) throws DocumentCreationException;
	DocumentDto findById(long id);
	void deleteByCode(String uuid);
	DocumentDto findByCode(String uuid);
}
