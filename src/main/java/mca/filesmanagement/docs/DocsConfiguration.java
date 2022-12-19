package mca.filesmanagement.docs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocsConfiguration {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}
