package docs.api.messaging.commands;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import net.sf.beanrunner.BeanRunner;

public class CommandsTest {

	/** Getters y setters. */
	@Test
    public void testGettersAndSetters() throws Exception {
		BeanRunner beanRunner = new BeanRunner();
		beanRunner.addTestValue(CreateDocCommand.class, new CreateDocCommand());
		beanRunner.testBean(new CreateDocCommand());
		
		beanRunner = new BeanRunner();
		beanRunner.addTestValue(UUID.class, UUID.randomUUID());
		beanRunner.addTestValue(DeleteDocCommand.class, new DeleteDocCommand());
		beanRunner.testBean(new DeleteDocCommand());
		
		beanRunner = new BeanRunner();
		beanRunner.addTestValue(UUID.class, UUID.randomUUID());
		beanRunner.addTestValue(DeleteDocCommand.class, new DeleteDocCommand(UUID.randomUUID()));
		beanRunner.testBean(new DeleteDocCommand());
	}
}
