package mca.filesmanagement.docs.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mca.filesmanagement.docs.infraestructure.model.DocumentEntity;

@Repository
public interface JpaDocumentRepository extends JpaRepository<DocumentEntity, Long> {

	/**
	 * Activa un documento y le asigna una URL a partir de su identificador.
	 * @param id Identificador único del documento.
	 * @param url URL que será asignada al documento.
	 */
	@Modifying
	@Query("UPDATE DocumentEntity d SET d.active = TRUE, d.url =:url WHERE d.id = :id")
	void activeDocument(@Param("id") long id, @Param("url") String url);

	/**
	 * Devuelve la entidad del documento encontrado a partir de su código externo único.
	 * @param code Código externo único.
	 * @return Documento encontrado o NULL en caso contrario.
	 */
	DocumentEntity findByCode(String code);
}
