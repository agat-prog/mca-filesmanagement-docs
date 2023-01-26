package mca.filesmanagement.docs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mca.filesmanagement.docs.commons.DocumentDto;
import mca.filesmanagement.docs.port.in.IDocumentUseCase;

/**
 * Servicio al CORE de documentos.
 *
 * @author agat
 */
@Service
public class DocService {

	@Autowired
	private IDocumentUseCase documentUseCase;

	/** Constructor por defecto. */
	public DocService() {
		super();
	}

	/**
	 * Devuelve un documento creado en el sistema o NULL en caso contrario.
	 * @param uuid Identificador único externo.
	 * @return Documento
	 */
	public DocumentDto findByCode(String uuid) {
		return this.documentUseCase.findByCode(uuid);
	}
}
