package docs.api.messaging.replies;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import net.sf.beanrunner.BeanRunner;

public class ReplyTest {

	/** Getters y setters. */
	@Test
    public void testGettersAndSetters() throws Exception {
		BeanRunner beanRunner = new BeanRunner();
		beanRunner.addTestValue(UUID.class, UUID.randomUUID());
		beanRunner.addTestValue(DocCreatedEvent.class, new DocCreatedEvent());
		beanRunner.testBean(new DocCreatedEvent());
	}
}
