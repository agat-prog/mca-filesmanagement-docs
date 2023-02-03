package mca.filesmanagement.docs.port.in;

import mca.filesmanagement.docs.commons.DocumentDto;
import mca.filesmanagement.docs.commons.DocumentNewDto;

/**
 * Contrato para los casos de uso a implementar relativos a la documentación.

 * @author agat
 */
public interface IDocumentUseCase {
	/**
	 * Crea un nuevo documento en el sistema a partir del DTO de entrada.
	 * @param documentDto DTO con la información del documento a crear.
	 * @return Identificador con el que se ha persistido en base de datos.
	 * @throws DocumentCreationException Lanzada si no ha podido guardarse.
	 */
	long createDocument(DocumentNewDto documentDto) throws DocumentCreationException;

	/**
	 * Devuelve un documento creado en el sistema a partir de su identificador único.
	 * @param id Identificador único del documento.
	 * @return Documento encontrado o NULL en caso contrario.
	 */
	DocumentDto findById(long id);

	/**
	 * Elimina un documento a partir de su identificador externo único.
	 * @param uuid Identificador externo único del documento.
	 */
	void deleteByCode(String uuid);

	/**
	 * Devuelve un documento creado en el sistema a partir de su código externo único.
	 * @param uuid Identificador externo único.
	 * @return Documento encontrado o NULL en caso contrario.
	 */
	DocumentDto findByCode(String uuid);
}
