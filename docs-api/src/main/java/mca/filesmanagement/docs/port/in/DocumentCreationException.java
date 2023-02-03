package mca.filesmanagement.docs.port.in;

/**
 * Excepción lanzada cuando la creación del documento no ha podido ser realizada.
 *
 * @author agat
 */
public class DocumentCreationException extends Exception {

	private static final long serialVersionUID = 8794194810203532184L;

	/**
	 * Constructor con la excepción padre como argumento.
	 * @param exc Excepción padre.
	 */
	public DocumentCreationException(Throwable exc) {
		super(exc);
	}
}
