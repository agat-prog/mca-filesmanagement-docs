package mca.filesmanagement.docs;

import java.util.Date;
import java.util.UUID;

import org.springframework.util.Base64Utils;

import mca.filesmanagement.docs.commons.DocumentDto;
import mca.filesmanagement.docs.commons.DocumentNewDto;

public class DocsFactory {

	public static final String CODE_FORMAT = "XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX";
	public static final String CONTENIDO_FORMAT = "contenido_%s";
	public static final String NAME_FORMAT = "name_%s";
	
	private DocsFactory() {
		super();
	}
	
	public static DocumentDto createDoc(long id) {
		DocumentDto doc = new DocumentDto();
		doc.setActive(true);
		doc.setCode(generateCode(id));
		doc.setContentBase64(Base64Utils.encodeToString(String.format(CONTENIDO_FORMAT, id).getBytes()));
		doc.setCreationDate(new Date());
		doc.setId(id);
		doc.setName(String.format(NAME_FORMAT, id));
		doc.setUpdateDate(new Date());
		return doc;
	}
	
	public static DocumentNewDto createNewDoc(long id) {
		DocumentNewDto doc = new DocumentNewDto();
		doc.setContentBase64(Base64Utils.encodeToString(String.format(CONTENIDO_FORMAT, id).getBytes()));
		doc.setName(String.format(NAME_FORMAT, id));
		return doc;
	}
	
	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString());
	}
	
	public static String generateCode(long id) {
		return CODE_FORMAT.replaceAll("X", String.valueOf(id));
	}
}
