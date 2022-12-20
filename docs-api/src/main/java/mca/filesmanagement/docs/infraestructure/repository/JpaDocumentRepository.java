package mca.filesmanagement.docs.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mca.filesmanagement.docs.infraestructure.model.DocumentEntity;

@Repository
public interface JpaDocumentRepository extends JpaRepository<DocumentEntity, Long> {

	@Modifying
	@Query("UPDATE DocumentEntity d SET d.active = TRUE, d.url =:url WHERE d.id = :id")
	void activeDocument(@Param("id") long id, @Param("url") String URL);
	
	DocumentEntity findByCode(String code);
}
