package docs.api.messaging.commands;

import java.util.UUID;

import io.eventuate.tram.commands.common.Command;

public class DeleteDocCommand implements Command {

	private UUID uuid;

	/** Constructor por defecto. */
	public DeleteDocCommand() {
		super();
	}

	/**
	 * Constructo con el identificador.
	 * @param uuid Identificador único.
	 */
	public DeleteDocCommand(UUID uuid) {
		super();
		this.uuid = uuid;
	}

	/**
	 * @return the uuid
	 */
	public UUID getUuid() {
		return uuid;
	}

	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
}
