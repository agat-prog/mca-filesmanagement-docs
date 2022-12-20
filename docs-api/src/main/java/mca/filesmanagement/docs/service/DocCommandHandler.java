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

public class DocCommandHandler {
	
	private static Logger LOGGER = LoggerFactory.getLogger(DocCommandHandler.class);

	private IDocumentUseCase documentUseCase;

	public DocCommandHandler(IDocumentUseCase documentUseCase) {
		super();
		
		this.documentUseCase = documentUseCase;
	}
	
	public CommandHandlers commandHandlerDefinitions() {
		return SagaCommandHandlersBuilder.fromChannel(CHANNEL_DOCS_SERVICE)
				.onMessage(CreateDocCommand.class, this::createDoc)
				.onMessage(DeleteDocCommand.class, this::deleteDoc)
				.build();
	}
	
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
		} 
		catch (Exception e) {
			LOGGER.error("Error on creating document", e);
			return withFailure();
		}
	}
	
	public Message deleteDoc(CommandMessage<DeleteDocCommand> cmd) {
		try {
			DeleteDocCommand command = cmd.getCommand();
			
			LOGGER.info(String.format("deleteDoc -->  %s", command.getUuid().toString()));
			
			this.documentUseCase.deleteByCode(command.getUuid().toString());
			return withSuccess();
		} 
		catch (Exception e) {
			return withFailure();
		}
	}
}
