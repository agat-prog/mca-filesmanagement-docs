package mca.filesmanagement.docs.service;

import static docs.api.messaging.commands.DocsChannels.CHANNEL_DOCS_SERVICE;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withFailure;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import docs.api.messaging.commands.CreateDocCommand;
import docs.api.messaging.commands.DeleteDocCommand;
import docs.api.messaging.replies.DocCreatedEvent;
import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;
import mca.filesmanagement.docs.commons.DocumentDto;
import mca.filesmanagement.docs.commons.DocumentNewDto;
import mca.filesmanagement.docs.port.in.IDocumentUseCase;

/**
 * Manager que contiene la información de los pasos a generar en la SAGA en la que
 * se subscribe.
 *
 * @author agat
 */
public class DocCommandHandler {

	private static Logger LOGGER = LoggerFactory.getLogger(DocCommandHandler.class);

	private IDocumentUseCase documentUseCase;

	/**
	 * Crea un manager para la creación de la SAGA asociada.
	 * @param documentUseCase Acceso a los casos de usos de los documentos.
	 */
	public DocCommandHandler(IDocumentUseCase documentUseCase) {
		super();

		this.documentUseCase = documentUseCase;
	}

	/**
	 * Define los pasos a seguir al obtener los eventos que involucran la SAGA
	 * a la que pertenece.
	 * @return CommandHandlers creado con los pasos a ejecutar.
	 */
	public CommandHandlers commandHandlerDefinitions() {
		return SagaCommandHandlersBuilder.fromChannel(CHANNEL_DOCS_SERVICE)
				.onMessage(CreateDocCommand.class, this::createDoc)
				.onMessage(DeleteDocCommand.class, this::deleteDoc)
				.build();
	}

	/**
	 * Crea un documento en base al comando de entrada procedente
	 * de la SAGA a la que está subscrito.
	 * @param cmd Comando de entrada.
	 * @return Mensaje de ok o failure si procede.
	 */
	public Message createDoc(CommandMessage<CreateDocCommand> cmd) {
		try {
			CreateDocCommand command = cmd.getCommand();

			LOGGER.info(String.format("createDoc -->  %s", command.getName()));

			DocumentNewDto documentDto = new DocumentNewDto();
			documentDto.setContentBase64(command.getContentBase64());
			documentDto.setCreationUser(command.getUser());
			documentDto.setName(command.getName());

			long id = this.documentUseCase.createDocument(documentDto);
			DocumentDto document = this.documentUseCase.findById(id);

			DocCreatedEvent event = new DocCreatedEvent();
			event.setState("CREATED");
			event.setUuid(UUID.fromString(document.getCode()));
			return withSuccess(event);
		} catch (Exception e) {
			LOGGER.error("Error on creating document", e);
			return withFailure();
		}
	}

	/**
	 * Elimina un documento en base al comando procedente
	 * de la SAGA a la que pertenece.
	 * @param cmd Comando entrante de la SAGA.
	 * @return Mensaje de ok o failure si procede.
	 */
	public Message deleteDoc(CommandMessage<DeleteDocCommand> cmd) {
		try {
			DeleteDocCommand command = cmd.getCommand();

			LOGGER.info(String.format("deleteDoc -->  %s", command.getUuid().toString()));

			this.documentUseCase.deleteByCode(command.getUuid().toString());
			return withSuccess();
		} catch (Exception e) {
			return withFailure();
		}
	}
}
