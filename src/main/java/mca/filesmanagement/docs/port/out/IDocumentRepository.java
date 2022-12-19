package mca.filesmanagement.docs.port.out;

import mca.filesmanagement.docs.commons.DocumentDto;
import mca.filesmanagement.docs.commons.DocumentNewDto;

public interface IDocumentRepository {
	long createDocument(DocumentNewDto documentDto);
	DocumentDto getById(long id);
	void active(long id, String URL);
	void deleteById(long id);
	DocumentDto getByCode(String code);
}
