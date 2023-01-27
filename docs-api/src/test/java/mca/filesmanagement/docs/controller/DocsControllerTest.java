package mca.filesmanagement.docs.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mca.filesmanagement.docs.DocsFactory;
import mca.filesmanagement.docs.commons.DocumentDto;
import mca.filesmanagement.docs.service.DocService;

@ExtendWith(SpringExtension.class)
@Tag("UnitTest")
@DisplayName("Services tests")
public class DocsControllerTest {
	
	@InjectMocks
	private DocsController docsController;
	
	@Mock
	private DocService docService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	@DisplayName("Test find a existing document by code")
	public void givenAExistingDocumentWhenFindThenReturnDocumentDto() {
		long id = 1;
		when(this.docService.findByCode(any())).thenReturn(DocsFactory.createDoc(id));

		ResponseEntity<DocumentDto> response = this.docsController.findByCode(String.format(DocsFactory.CODE_FORMAT, id));
		
		assertNotNull(response);
		assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
		
		verify(this.docService, times(1)).findByCode(anyString());
		
		DocumentDto doc = response.getBody();
		assertNotNull(doc);
		assertNotNull(doc.getId());
		assertEquals(DocsFactory.generateCode(id), doc.getCode());
		assertEquals(String.format(DocsFactory.NAME_FORMAT, id), doc.getName());
	}
}
