package mca.filesmanagement.docs.config;

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

	/**
	 * Devuelve el command handler necesario para vincular el micro con una SAGA.
	 * @param documentUseCase
	 * @return Manejador de comandos para el patrón SAGA.
	 */
	@Bean
	public DocCommandHandler bpmCommandHandler(IDocumentUseCase documentUseCase) {
		return new DocCommandHandler(documentUseCase);
	}

	/**
	 * Devuelve la asociación existente entre el nombre del "topic" y la definición de la SAGA asociada.
	 * @param target
	 * @param sagaCommandDispatcherFactory
	 * @return Objeto de Eventuate que define los pasos de una SAGA.
	 */
	@Bean
	public CommandDispatcher consumerCommandDispatcher(DocCommandHandler target,
			SagaCommandDispatcherFactory sagaCommandDispatcherFactory) {
		return sagaCommandDispatcherFactory.make("docsCommandDispatcher", target.commandHandlerDefinitions());
	}
}
