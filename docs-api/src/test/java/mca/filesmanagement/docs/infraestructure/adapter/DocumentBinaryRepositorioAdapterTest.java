package mca.filesmanagement.docs.infraestructure.adapter;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mca.filesmanagement.docs.DocsFactory;
import mca.filesmanagement.docs.port.out.DocumentBinarySaveException;

@ExtendWith(SpringExtension.class)
@Tag("UnitTest")
@DisplayName("Document binary repository adapter Use Case tests")
public class DocumentBinaryRepositorioAdapterTest {

	@InjectMocks
	private DocumentBinaryRepositorioAdapter documentBinaryRepositorioAdapter;

	/** Configuración inicial. */
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	@DisplayName("Test create a binary document. ")
	public void givenNewContentWhenCreateOrUpdateThenReturnDocumentBinaryCreated() throws Exception {
		File file = File.createTempFile("pre", "suf");
		try {
			long id = 1;
			this.documentBinaryRepositorioAdapter.setPath(file.getParent());
			byte[] contenido = String.format(DocsFactory.CONTENIDO_FORMAT, id).getBytes();
			
			String path = this.documentBinaryRepositorioAdapter.createOrUpdate(file.getName(), contenido);
			
			assertNotNull(path);
			assertEquals(path, file.getAbsolutePath());
			assertTrue(file.length() == contenido.length);
			
		} finally {
			file.delete();
		}
	}
	
	@Test
	@DisplayName("Test create a bad binary document. ")
	public void givenNewContentWhenCreateOrUpdateBadDocumentThenThrowException() throws Exception {
		File file = File.createTempFile("pre", "suf");
		try {
			long id = 1;
			this.documentBinaryRepositorioAdapter.setPath(file.getParent() + "___");
			byte[] contenido = String.format(DocsFactory.CONTENIDO_FORMAT, id).getBytes();
			
			assertThrows(DocumentBinarySaveException.class,
					() -> this.documentBinaryRepositorioAdapter.createOrUpdate(file.getName(), contenido));
			
		} finally {
			file.delete();
		}
	}
	
	@Test
	@DisplayName("Test delete a document. ")
	public void givenPathWhenDeleteThenReturnDocumentBinaryDeleted() throws Exception {
		File file = File.createTempFile("pre", "suf");
		this.documentBinaryRepositorioAdapter.delete(file.getAbsolutePath());
		
		assertFalse(file.exists());
	}
}
