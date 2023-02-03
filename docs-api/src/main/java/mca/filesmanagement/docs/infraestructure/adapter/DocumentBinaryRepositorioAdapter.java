package mca.filesmanagement.docs.infraestructure.adapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import mca.filesmanagement.docs.port.out.DocumentBinarySaveException;
import mca.filesmanagement.docs.port.out.IDocumentBinaryRepositorio;

@Service
public class DocumentBinaryRepositorioAdapter implements IDocumentBinaryRepositorio {

	private static Logger LOGGER = LoggerFactory.getLogger(DocumentBinaryRepositorioAdapter.class);

	@Value("${mca.filesmanagement.docs.port.out.documentbinary.path}")
	private String path;

	/** Constructor por defecto. */
	public DocumentBinaryRepositorioAdapter() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String createOrUpdate(String name, byte[] content) throws DocumentBinarySaveException {
		StringBuilder sb = new StringBuilder();
		sb.append(this.path);
		sb.append(File.separator);
		sb.append(name);

		String path = sb.toString();
		LOGGER.info(String.format("Creating file in path [%s]", path));

		try (FileOutputStream out = new FileOutputStream(new File(path))){
			out.write(content);
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("An error found when file was been created ", e);
			throw new DocumentBinarySaveException();
		}

		return path;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(String path){
		LOGGER.info(String.format("delete file in path [%s]", path));

		try {
			if (Files.exists(Path.of(path))) {
				Files.delete(Path.of(path));
			}
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("An error when delete file ", e);
		}
	}

	/**
	 * Actualiza el valor del path.
	 * @param path
	 */
	protected void setPath(String path) {
		this.path = path;
	}
}
