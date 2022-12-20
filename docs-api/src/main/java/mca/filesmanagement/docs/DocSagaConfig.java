package mca.filesmanagement.docs;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.eventuate.common.spring.jdbc.EventuateCommonJdbcOperationsConfiguration;
import io.eventuate.tram.commands.consumer.CommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcherFactory;
import mca.filesmanagement.docs.port.in.IDocumentUseCase;
import mca.filesmanagement.docs.service.DocCommandHandler;

@Configuration
@Import(EventuateCommonJdbcOperationsConfiguration.class)
@EnableAutoConfiguration
@EnableJpaRepositories
public class DocSagaConfig {
	
	@Bean
	public DocCommandHandler bpmCommandHandler(IDocumentUseCase documentUseCase) {
		return new DocCommandHandler(documentUseCase);
	}

	@Bean
	public CommandDispatcher consumerCommandDispatcher(DocCommandHandler target,
			SagaCommandDispatcherFactory sagaCommandDispatcherFactory) {
		return sagaCommandDispatcherFactory.make("docsCommandDispatcher", target.commandHandlerDefinitions());
	}
}
