package mca.filesmanagement.docs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal para el inicio y configuración de la aplicación de documentos.
 * @author agat
 */
@SpringBootApplication
public class DocsApp {

	/**
	 * Entrada principal de la aplicación de documentos.
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(DocsApp.class, args);
	}

	/**
	 * PostConstruct.
	 */
	public void postConstruct() {
	}
}
