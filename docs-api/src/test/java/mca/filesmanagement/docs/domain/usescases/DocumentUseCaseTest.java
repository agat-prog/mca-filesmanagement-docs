package mca.filesmanagement.docs.domain.usescases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mca.filesmanagement.docs.DocsFactory;
import mca.filesmanagement.docs.commons.DocumentDto;
import mca.filesmanagement.docs.port.in.DocumentCreationException;
import mca.filesmanagement.docs.port.out.DocumentBinarySaveException;
import mca.filesmanagement.docs.port.out.IDocumentBinaryRepositorio;
import mca.filesmanagement.docs.port.out.IDocumentRepository;

@ExtendWith(SpringExtension.class)
@Tag("UnitTest")
@DisplayName("Docs Case tests")
public class DocumentUseCaseTest {

	@InjectMocks
	private DocumentUseCase documentUseCase;
	
	@Mock
	private IDocumentBinaryRepositorio documentBinaryRepositorio;
	
	@Mock
	private IDocumentRepository documentRepository;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	@DisplayName("Test find a existing doc")
	public void givenAExistingDocsWhenFindThenReturnDocDto() {
		Long id = 1l;
		when(this.documentRepository.getById(Mockito.anyLong())).thenReturn(DocsFactory.createDoc(id));
		
		DocumentDto doc = this.documentUseCase.findById(id);
		verify(this.documentRepository, times(1)).getById(Mockito.anyLong());
		
		assertNotNull(doc);
		assertNotNull(doc.getId());
		assertEquals(DocsFactory.generateCode(id), doc.getCode());
		assertEquals(String.format(DocsFactory.NAME_FORMAT, id), doc.getName());
	}
	
	@Test
	@DisplayName("Test find a non-exist doc")
	public void givenNonExistingDocsWhenFindThenReturnNull() {
		Long id = 1l;
		when(this.documentRepository.getById(Mockito.anyLong())).thenReturn(null);
		
		DocumentDto doc = this.documentUseCase.findById(id);
		verify(this.documentRepository, times(1)).getById(Mockito.anyLong());
		
		assertNull(doc);
	}
	
	@Test
	@DisplayName("Test find by code a existing doc")
	public void givenAExistingUUIDDocsWhenFindThenReturnDocDto() {
		Long id = 1l;
		when(this.documentRepository.getByCode(any())).thenReturn(DocsFactory.createDoc(id));
		
		DocumentDto doc = this.documentUseCase.findByCode(DocsFactory.generateCode(id));
		verify(this.documentRepository, times(1)).getByCode(any());
		
		assertNotNull(doc);
		assertNotNull(doc.getId());
		assertEquals(DocsFactory.generateCode(id), doc.getCode());
		assertEquals(String.format(DocsFactory.NAME_FORMAT, id), doc.getName());
	}
	
	@Test
	@DisplayName("Test create a doc")
	public void givenNewDocsWhenCreateThenReturnId() throws DocumentBinarySaveException, DocumentCreationException {
		long id = 1;
		when(this.documentRepository.createDocument(any())).thenReturn(id);
		when(this.documentRepository.getById(Mockito.anyLong())).thenReturn(DocsFactory.createDoc(id));
		when(this.documentBinaryRepositorio.createOrUpdate(any(), Mockito.any(byte[].class))).thenReturn("ruta");
		doNothing().when(this.documentRepository).active(Mockito.anyLong(), Mockito.anyString());
		
		long idReturn = this.documentUseCase.createDocument(DocsFactory.createNewDoc(2));
		
		assertTrue(idReturn == id);
	}
	
	@Test
	@DisplayName("Test create invalid doc")
	public void givenAInvalidDocWhenCreateThenThrowException() throws DocumentBinarySaveException {
		long id = 1;
		when(this.documentRepository.createDocument(any())).thenReturn(id);
		when(this.documentRepository.getById(Mockito.anyLong())).thenReturn(DocsFactory.createDoc(id));
		when(this.documentBinaryRepositorio.createOrUpdate(any(), Mockito.any(byte[].class))).thenThrow(DocumentBinarySaveException.class);

		assertThrows(DocumentCreationException.class,
				() -> this.documentUseCase.createDocument(DocsFactory.createNewDoc(2)));
	}
}
