package mca.filesmanagement.docs.port.out;

/**
 * Contrato con las operaciones a realizar en almacén binario de los documentos (filesystem, S3, etc.).
 *
 * @author agat
 */
public interface IDocumentBinaryRepositorio {
	/**
	 * Actualiza un documento existente o lo crea, si este no existe.
	 * @param name Nombre con el que se guardará.
	 * @param content Contenido binario del documento.
	 * @return Ruta donde se ha generado o actualizado el documento.
	 * @throws DocumentBinarySaveException Lanzada si no ha podido llevarse a cabo la operación.
	 */
	String createOrUpdate(String name, byte[] content) throws DocumentBinarySaveException;

	/**
	 * Elimina un documento del almacén donde está ubicado.
	 * @param path Ruta del almacén del documento a eliminar.
	 */
	void delete(String path);
}
