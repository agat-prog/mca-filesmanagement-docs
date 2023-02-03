package mca.filesmanagement.docs;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
	 * Devuelve el mapeador de objetos a utilizar.
	 * @return ModelMapper
	 */
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}
