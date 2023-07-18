package mca.filesmanagement.docs.infraestructure.adapter;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mca.filesmanagement.docs.DocsFactory;
import mca.filesmanagement.docs.commons.DocumentDto;
import mca.filesmanagement.docs.commons.DocumentNewDto;
import mca.filesmanagement.docs.infraestructure.repository.JpaDocumentRepository;

@ExtendWith(SpringExtension.class)
@Tag("UnitTest")
@DisplayName("Document repository adapter Use Case tests")
public class DocumentRepositoryAdapterTest {

	@InjectMocks
	private DocumentRepositoryAdapter documentRepositoryAdapter;

	@Mock
	private JpaDocumentRepository jpaDocumentRepository;

	@Mock
	private ModelMapper modelMapper;

	/** Configuración inicial. */
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	/** Test create a new document */
	@Test
	@DisplayName("Test create a document. ")
	public void givenNewDocumentWhenCreateThenReturnDocumentCreated() {
		long id = 1;
		DocumentNewDto documentNewDto = DocsFactory.createNewDoc(id);

		when(this.jpaDocumentRepository.save(any())).thenReturn(DocsFactory.createDocEntity(id));

		long documentId = this.documentRepositoryAdapter.createDocument(documentNewDto);

		verify(this.jpaDocumentRepository, times(1)).save(any());
		assertTrue(documentId == id);
	}

	@Test
	@DisplayName("Test find a existing doc by id")
	public void givenAExistingDocsWhenFindByIdThenReturnDocDto() {
		Long id = 1l;
		when(this.jpaDocumentRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(DocsFactory.createDocEntity(id)));
		when(this.modelMapper.map(any(), any())).thenReturn(DocsFactory.createDoc(id));

		DocumentDto doc = this.documentRepositoryAdapter.getById(id);
		verify(this.jpaDocumentRepository, times(1)).findById(Mockito.anyLong());
		verify(this.modelMapper, times(1)).map(any(), any());

		assertNotNull(doc);
		assertNotNull(doc.getId());
		assertEquals(DocsFactory.generateCode(id), doc.getCode());
		assertEquals(String.format(DocsFactory.NAME_FORMAT, id), doc.getName());
	}

	@Test
	@DisplayName("Test find a existing doc by code")
	public void givenAExistingDocsWhenFindByCodeThenReturnDocDto() {
		Long id = 1l;
		when(this.jpaDocumentRepository.findByCode(Mockito.anyString())).thenReturn(DocsFactory.createDocEntity(id));
		when(this.modelMapper.map(any(), any())).thenReturn(DocsFactory.createDoc(id));

		DocumentDto doc = this.documentRepositoryAdapter.getByCode(String.format(DocsFactory.NAME_FORMAT, id));
		verify(this.jpaDocumentRepository, times(1)).findByCode(Mockito.anyString());
		verify(this.modelMapper, times(1)).map(any(), any());

		assertNotNull(doc);
		assertNotNull(doc.getId());
		assertEquals(DocsFactory.generateCode(id), doc.getCode());
		assertEquals(String.format(DocsFactory.NAME_FORMAT, id), doc.getName());
	}

	@Test
	@DisplayName("Test active and update document")
	public void givenAExistingDocIdWhenActiveThenOK() {
		long id = 1;
		doNothing().when(this.jpaDocumentRepository).activeDocument(Mockito.anyLong(), Mockito.anyString());
		
		this.documentRepositoryAdapter.active(id, DocsFactory.generateCode(id));
		verify(this.jpaDocumentRepository, times(1)).activeDocument(Mockito.anyLong(), Mockito.anyString());
	}

	@Test
	@DisplayName("Test active and update document")
	public void givenAExistingDocIdWhenDeleteThenOK() {
		long id = 1;
		doNothing().when(this.jpaDocumentRepository).deleteById(Mockito.anyLong());
		
		this.documentRepositoryAdapter.deleteById(id);
		verify(this.jpaDocumentRepository, times(1)).deleteById(Mockito.anyLong());
	}
}
