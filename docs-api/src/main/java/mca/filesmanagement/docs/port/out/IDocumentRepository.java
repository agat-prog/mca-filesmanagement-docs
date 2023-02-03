package mca.filesmanagement.docs.port.out;

import mca.filesmanagement.docs.commons.DocumentDto;
import mca.filesmanagement.docs.commons.DocumentNewDto;

/**
 * Contrato con las operaciones realizadas en el repositorio de documentos.
 *
 * @author agat
 */
public interface IDocumentRepository {

	/**
	 * Crea un documento en base al DTO suministrado.
	 * @param documentDto DTO del documento a crear.
	 * @return Identificador de la base de datos generado.
	 */
	long createDocument(DocumentNewDto documentDto);

	/**
	 * Devuelve un documento en base a su PK de base de datos.
	 * @param id Identificador único de base de datos (PK):
	 * @return Documento (DTO) encontrado o NULL en caso contrario.
	 */
	DocumentDto getById(long id);

	/**
	 * Activa un documento y actualiza su URL.
	 * @param id Identificador (PK) del documento a actualizar.
	 * @param url Path donde se encuentra ubicado el documento en el almacén.
	 */
	void active(long id, String url);

	/**
	 * Elimina del repositorio el documento según su identificador único (PK).
	 * @param id Identificador único (PK) del documento a eliminar.
	 */
	void deleteById(long id);

	/**
	 * Devuelve el DTO del documento encontrado a partir de su código externo único.
	 * @param code Identificador externo único.
	 * @return Documento encontrado o NULL en caso contrario.
	 */
	DocumentDto getByCode(String code);
}
