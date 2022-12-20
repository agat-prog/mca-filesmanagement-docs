package docs.api.messaging.commands;

import io.eventuate.tram.commands.common.Command;

public class CreateDocCommand implements Command {
	
	private String name;
	private String contentBase64;
	private String user;
	
	public CreateDocCommand() {
		super();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the contentBase64
	 */
	public String getContentBase64() {
		return contentBase64;
	}

	/**
	 * @param contentBase64 the contentBase64 to set
	 */
	public void setContentBase64(String contentBase64) {
		this.contentBase64 = contentBase64;
	}
}
