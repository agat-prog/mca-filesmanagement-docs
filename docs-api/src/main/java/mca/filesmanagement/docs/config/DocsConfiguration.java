package mca.filesmanagement.docs.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocsConfiguration {

	/**
	 * Devuelve el mapeador de objetos a utilizar.
	 * @return ModelMapper
	 */
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}
