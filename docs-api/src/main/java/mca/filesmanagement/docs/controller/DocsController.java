package mca.filesmanagement.docs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mca.filesmanagement.docs.commons.DocumentDto;
import mca.filesmanagement.docs.service.DocService;

@RestController
@RequestMapping("/api/docs")
public class DocsController {

	private static Logger LOGGER = LoggerFactory.getLogger(DocsController.class);

	@Autowired
	private DocService docService;

	/** Constructor por defecto. */
	public DocsController() {
		super();
	}

	/**
	 * Devuelve el documento (DTO) a partir de su código externo.
	 * @param documentCode
	 * @return Entidad que envuelve al documento con datos de HTTP Status.
	 */
	@GetMapping(path = "/{documentCode}")
	public ResponseEntity<DocumentDto> findByCode(@PathVariable(name = "documentCode", required = true) String documentCode){
		LOGGER.info(String.format("DocsController.findByCode: documentCode -> %s", documentCode));
		return ResponseEntity.ok(this.docService.findByCode(documentCode));
	}
}
