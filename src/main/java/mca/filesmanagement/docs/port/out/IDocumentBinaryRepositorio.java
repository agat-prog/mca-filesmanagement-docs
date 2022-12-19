package mca.filesmanagement.docs.port.out;

public interface IDocumentBinaryRepositorio {
	String createOrUpdate(String name, byte[] content) throws DocumentBinarySaveException;
	void delete(String path);
}
