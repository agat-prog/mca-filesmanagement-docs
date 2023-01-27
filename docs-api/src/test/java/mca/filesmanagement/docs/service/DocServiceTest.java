package mca.filesmanagement.docs.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
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
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mca.filesmanagement.docs.DocsFactory;
import mca.filesmanagement.docs.commons.DocumentDto;
import mca.filesmanagement.docs.port.in.IDocumentUseCase;

@ExtendWith(SpringExtension.class)
@Tag("UnitTest")
@DisplayName("Services tests")
public class DocServiceTest {

	@InjectMocks
	private DocService docService;
	
	@Mock
	private IDocumentUseCase documentUseCase;
	
	/** Configuración inicial. */
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	@DisplayName("Test find a existing document")
	public void givenAExistingDocumentWhenFindThenReturnDocumentDtoReturned() {
		long id = 1;
		when(this.documentUseCase.findByCode(anyString())).thenReturn(DocsFactory.createDoc(id));
		
		DocumentDto doc = this.docService.findByCode(String.format(DocsFactory.CODE_FORMAT, id));
		
		verify(this.documentUseCase, times(1)).findByCode(anyString());
		assertNotNull(doc);
		assertNotNull(doc.getId());
		assertEquals(DocsFactory.generateCode(id), doc.getCode());
		assertEquals(String.format(DocsFactory.NAME_FORMAT, id), doc.getName());
	}
}
