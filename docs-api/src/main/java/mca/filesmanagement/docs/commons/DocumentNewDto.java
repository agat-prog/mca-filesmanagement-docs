package mca.filesmanagement.docs.commons;

public class DocumentNewDto {

	private String name;
	private String contentBase64;
	private String creationUser;

	/** Constructor por defecto. */
	public DocumentNewDto() {
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

	/**
	 * @return the creationUser
	 */
	public String getCreationUser() {
		return creationUser;
	}

	/**
	 * @param creationUser the creationUser to set
	 */
	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}
}
